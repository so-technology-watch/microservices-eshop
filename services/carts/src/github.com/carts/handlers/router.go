package handlers

import (
	"github.com/carts/services"
	"github.com/gorilla/mux"
)

//Router creates a new router.
func Router(clientRedis *services.RedisClient, gateWayClient *services.GateWayClient) *mux.Router {

	//Routing
	router := mux.NewRouter()
	subRouter := router.PathPrefix("/api/v1/").Subrouter()
	//Cart routes
	subRouter.HandleFunc("/carts/{ID}", HandleCartGet(clientRedis)).Methods("GET")
	subRouter.HandleFunc("/carts", HandleCartPost(clientRedis, gateWayClient)).Methods("POST")
	subRouter.HandleFunc("/carts", HandleCartPut(clientRedis, gateWayClient)).Methods("PUT")
	subRouter.HandleFunc("/carts/{ID}", HandleCartDelete(clientRedis)).Methods("DELETE")
	//CartElment routes
	subRouter.HandleFunc("/carts/{customerID}/elements/{elementID}", HandleCartElementGet(clientRedis)).Methods("GET")
	subRouter.HandleFunc("/carts/elements", HandleCartElementPost(clientRedis, gateWayClient)).Methods("POST")
	subRouter.HandleFunc("/carts/elements", HandleCartElementPut(clientRedis, gateWayClient)).Methods("PUT")
	subRouter.HandleFunc("/carts/{customerID}/elements/{elementID}", HandleCartElementDelete(clientRedis)).Methods("DELETE")

	return router

}
