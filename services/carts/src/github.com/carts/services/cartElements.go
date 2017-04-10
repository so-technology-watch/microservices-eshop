package services

import (
	"encoding/json"
	"strconv"

	"github.com/carts/models"
)

//GetCartElement retrieves the CartElement corresponding to the given elementID from the cart of the customer with the given customerID.
func (c *RedisClient) GetCartElement(customerID int, elementID int) *models.CartElement {

	cartJSON := c.GetCart(customerID)
	cart := jsonToCart(cartJSON)
	element := cart.GetElement(elementID)

	return element

}

//AddCartElement adds a new cartElement to the cart of the customer corresponding to the given CustomerID.
func (c *RedisClient) AddCartElement(customerID int, element *models.CartElement) {

	key := strconv.Itoa(customerID)
	cartJSON := c.GetCart(customerID)
	cart := jsonToCart(cartJSON)
	cart.AddElement(element)
	updatedCartJSON := cartToJSON(cart)
	c.Client.Set(key, updatedCartJSON, 0)

}

//ModifyCartElement modifies an element from the cart of the customer corresponding to the given CustomerID
func (c *RedisClient) ModifyCartElement(customerID int, elementID int, element *models.CartElement) {

	key := strconv.Itoa(customerID)
	cartJSON := c.GetCart(customerID)
	cart := jsonToCart(cartJSON)
	e := cart.GetElement(elementID)
	*e = *element
	updatedCartJSON := cartToJSON(cart)
	c.Client.Set(key, updatedCartJSON, 0)
}

//RemoveCartElement removes a CartElement from the cart of the customer corresponding to the given CustomerID
func (c *RedisClient) RemoveCartElement(customerID int, elementID int) {

	cartJSON := c.GetCart(customerID)
	cart := jsonToCart(cartJSON)
	cart.RemoveElement(elementID)
	key := strconv.Itoa(customerID)
	updatedCartJSON := cartToJSON(cart)
	c.Client.Set(key, updatedCartJSON, 0)
}

func jsonToCart(cartJSON string) *models.Cart {

	cart := &models.Cart{}
	json.Unmarshal([]byte(cartJSON), cart)

	return cart
}

func cartToJSON(cart *models.Cart) []byte {

	cartJSON, err := json.Marshal(cart)
	failOnError(err)

	return cartJSON
}
