package services

import (
	"encoding/json"

	"log"

	"github.com/carts/models"
)

//Position denfines a two dimensional array containing the ID of a client and an element.
type Position struct {
	CustomerID int
	ElementID  int
}

//FindProducts returns the postions of every CartElments corresponding to a a given product.
func (c *RedisClient) FindProducts(productID int) *[]Position {

	var res []Position
	keys, err := c.Client.Keys("*").Result()
	failOnError(err)
	var elements []int
	cart := &models.Cart{}

	for _, key := range keys {

		cartJSON, err := c.Client.Get(key).Result()
		failOnError(err)
		json.Unmarshal([]byte(cartJSON), cart)
		elements = *cart.FindProduct(productID)
		if len(elements) > 0 {
			for _, element := range elements {

				pos := &Position{CustomerID: cart.CustomerID, ElementID: element}
				res = append(res, *pos)
			}

		}

	}
	log.Println("Found ", len(res), " elements containing this product.")
	return &res
}

//ChangeProductPrice changes the unit price of a gven product to the new given price
func (c *RedisClient) ChangeProductPrice(productID int, price float32) {

	positions := *c.FindProducts(productID)

	for _, position := range positions {

		element := c.GetCartElement(position.CustomerID, position.ElementID)
		element.UnitPrice = price
		c.ModifyCartElement(position.CustomerID, position.ElementID, element)

	}
}
