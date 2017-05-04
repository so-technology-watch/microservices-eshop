package handlers

import (
	"io/ioutil"
	"net/http"
	"strconv"

	"encoding/json"

	"github.com/carts/models"
	"github.com/carts/services"
	"github.com/gorilla/mux"
)

//HandleCartElementGet handles GET request by retrieving and retruning the cart of the given customer
func HandleCartElementGet(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {
		parameters := mux.Vars(r)
		customerID := parameters["customerID"]
		elementID := parameters["elementID"]
		id, err := strconv.Atoi(elementID)
		failOnError(err)
		element, found := client.GetCartElement(customerID, id)
		var elementJSON []byte
		if found {
			elementJSON, err = json.Marshal(element)
			failOnError(err)
		} else {

			elementJSON = []byte("{\"Error\" : \"No element corresponding to the given parameters.\"}")
			w.WriteHeader(http.StatusNotFound)
		}
		w.Header().Set("Content-Type", "application/json")
		w.Write([]byte(elementJSON))

	}
}

//HandleCartElementPost handles POST request by storing the given custmer cart
func HandleCartElementPost(client *services.RedisClient, gateWayClient *services.GateWayClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {

		payloadJSON, err := ioutil.ReadAll(r.Body)
		failOnError(err)
		payload := &models.ElementPayload{}
		json.Unmarshal([]byte(payloadJSON), payload)
		client.AddCartElement(payload.CustomerID, payload.CartElement, gateWayClient)
		message := "{\"message\" : \"OK\"}"
		w.Header().Set("Content-Type", "application/json")
		w.WriteHeader(http.StatusCreated)
		w.Write([]byte(message))

	}

}

//HandleCartElementPut handles PUT request by updating the customer with the given cart
func HandleCartElementPut(client *services.RedisClient, gateWayClient *services.GateWayClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {

		payloadJSON, err := ioutil.ReadAll(r.Body)
		failOnError(err)
		payload := &models.ElementPayload{}
		json.Unmarshal([]byte(payloadJSON), payload)
		client.ModifyCartElement(payload.CustomerID, payload.CartElement.ElementID, payload.CartElement, gateWayClient)
		message := "{\"message\" : \"OK\"}"
		w.Header().Set("Content-Type", "application/json")
		w.Write([]byte(message))

	}
}

//HandleCartElementDelete handdles DELETE request by removing the cart of the given customer
func HandleCartElementDelete(client *services.RedisClient) http.HandlerFunc {
	return func(w http.ResponseWriter, r *http.Request) {

		parameters := mux.Vars(r)
		customerID := parameters["customerID"]
		elementID := parameters["elementID"]
		e, err := strconv.Atoi(elementID)
		failOnError(err)
		client.RemoveCartElement(customerID, e)
		message := "{\"message\" : \"OK\"}"
		w.Header().Set("Content-Type", "apllication/json")
		w.Write([]byte(message))
	}
}
