package handlers

import (
	"io/ioutil"
	"log"
	"net/http"

	"encoding/json"

	"github.com/carts/models"
	"github.com/carts/services"
	"github.com/gorilla/mux"
)

//HandleCartGet handles GET request by retrieving and retruning the cart of the given customer
func HandleCartGet(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		parameters := mux.Vars(r)
		customerID := parameters["ID"]
		json, found := client.GetCart(customerID)

		if !found {
			json = ("{\"Error\" : \"No element corresponding to the given parameters.\"}")
			w.WriteHeader(http.StatusNotFound)
		}

		w.Header().Set("Content-Type", "application/json,")
		w.Write([]byte(json))

	}
}

//HandleCartPost handles POST request by storing the given custmer cart
func HandleCartPost(client *services.RedisClient, gateWayClient *services.GateWayClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		body, err := ioutil.ReadAll(r.Body)
		failOnError(err)
		theCart := &models.Cart{}
		log.Println(string(body))
		json.Unmarshal([]byte(body), theCart)
		log.Println(theCart)
		client.AddCart(theCart, gateWayClient)
		message := "{\"message\" : \"OK\"}"
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusCreated)
		w.Write([]byte(message))
	}
}

//HandleCartPut handles PUT request by updating the customer with the given cart
func HandleCartPut(client *services.RedisClient, gateWayClient *services.GateWayClient) http.HandlerFunc {
	return HandleCartPost(client, gateWayClient)

}

//HandleCartDelete handdles DELETE request by removing the cart of the given customer
func HandleCartDelete(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		parameters := mux.Vars(r)
		customerID := parameters["ID"]
		client.RemoveCart(customerID)
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
