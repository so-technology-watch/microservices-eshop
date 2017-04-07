package services

import (
	"encoding/json"
	"strconv"

	"github.com/carts/models"
)

//AddCart adds a cart to redis KV store
func (c *RedisClient) AddCart(cart *models.Cart) {

	key := strconv.Itoa(cart.CustomerID)
	value, err := json.Marshal(cart)
	failOnError(err)
	err = c.Client.Set(key, value, 0).Err()
	failOnError(err)

}

//RemoveCart removes a car from redis KV store
func (c *RedisClient) RemoveCart(clientID int) {

	key := strconv.Itoa(clientID)
	c.Client.Del(key)
}

//GetCart retrieves the cart of a client in the redis KV store
func (c *RedisClient) GetCart(clientID int) string {

	key := strconv.Itoa(clientID)
	value, err := c.Client.Get(key).Result()
	failOnError(err)

	return value
}

//failOnError checks for errors and panics if it's the case
func failOnError(err error) {

	if err != nil {

		panic(err)
	}
}
