package handlers

import (
	"io/ioutil"
	"net/http"
	"strconv"

	"encoding/json"

	"github.com/carts/models"
	"github.com/carts/services"
)

//HandleCartGet handles GET request by retrieving and retruning the cart of the given customer
func HandleCartGet(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		customerID := r.URL.Query().Get("customerID")
		key, err := strconv.Atoi(customerID)
		failOnError(err)
		json := client.GetCart(key)
		w.Header().Set("Content-Type", "application/json,")
		w.Write([]byte(json))

	}
}

//HandleCartPost handles POST request by storing the given custmer cart
func HandleCartPost(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		body, err := ioutil.ReadAll(r.Body)
		failOnError(err)
		theCart := &models.Cart{}
		json.Unmarshal([]byte(body), theCart)
		client.AddCart(theCart)
		message := "{\"message\" : \"OK\"}"
		w.Header().Set("Content-Type", "application/json")
		w.Write([]byte(message))
	}
}

//HandleCartPut handles PUT request by updating the customer with the given cart
func HandleCartPut(client *services.RedisClient) http.HandlerFunc {
	return HandleCartPost(client)

}

//HandleCartDelete handdles DELETE request by removing the cart of the given customer
func HandleCartDelete(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		customerID := r.URL.Query().Get("customerID")
		key, err := strconv.Atoi(customerID)
		failOnError(err)
		client.RemoveCart(key)
		message := "{\"message\" : \"OK\"}"
		w.Header().Set("Content-Type", "application/json")
		w.Write([]byte(message))
	}
}

//failOnError checks for errors and panics to them if it's the case
func failOnError(err error) {
	if err != nil {
		panic(err)
	}
}
