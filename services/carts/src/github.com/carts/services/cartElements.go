package services

import (
	"encoding/json"
	"strconv"

	"github.com/carts/models"
)

//GetCartElement retrieves the CartElement corresponding to the given elementID from the cart of the customer with the given customerID.
func (c *RedisClient) GetCartElement(customerID int, elementID int) (*models.CartElement, bool) {

	element := new(models.CartElement)
	cartJSON, found := c.GetCart(customerID)

	if found {
		cart := jsonToCart(cartJSON)
		element, found, _ = cart.GetElement(elementID)
	}

	return element, found

}

//AddCartElement adds a new cartElement to the cart of the customer corresponding to the given CustomerID.
func (c *RedisClient) AddCartElement(customerID int, element *models.CartElement, gateWayClient *GateWayClient) bool {

	key := strconv.Itoa(customerID)
	cartJSON, found := c.GetCart(customerID)

	if found && gateWayClient.CheckProductExists(element) && gateWayClient.CheckProductPrice(element) {
		cart := jsonToCart(cartJSON)
		cart.AddElement(element)
		updatedCartJSON := cartToJSON(cart)
		c.Client.Set(key, updatedCartJSON, 0)
	}

	return found
}

//ModifyCartElement modifies an element from the cart of the customer corresponding to the given CustomerID
func (c *RedisClient) ModifyCartElement(customerID int, elementID int, element *models.CartElement, gateWayClient *GateWayClient) bool {

	key := strconv.Itoa(customerID)
	cartJSON, found := c.GetCart(customerID)

	if found && gateWayClient.CheckProductExists(element) && gateWayClient.CheckProductPrice(element) {
		cart := jsonToCart(cartJSON)
		e, _, _ := cart.GetElement(elementID)
		*e = *element
		updatedCartJSON := cartToJSON(cart)
		c.Client.Set(key, updatedCartJSON, 0)
	}

	return found
}

//RemoveCartElement removes a CartElement from the cart of the customer corresponding to the given CustomerID
func (c *RedisClient) RemoveCartElement(customerID int, elementID int) bool {

	cartJSON, found := c.GetCart(customerID)

	if found {
		cart := jsonToCart(cartJSON)
		cart.RemoveElement(elementID)
		key := strconv.Itoa(customerID)
		updatedCartJSON := cartToJSON(cart)
		c.Client.Set(key, updatedCartJSON, 0)
	}

	return found
}

func jsonToCart(cartJSON string) *models.Cart {

	cart := new(models.Cart)
	json.Unmarshal([]byte(cartJSON), &cart)
	return cart
}

func cartToJSON(cart *models.Cart) []byte {

	cartJSON, err := json.Marshal(cart)
	failOnError(err)

	return cartJSON
}
