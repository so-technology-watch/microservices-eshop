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
	subRouter.HandleFunc("/cartElement/{customerID}/{elementID}", HandleCartElementGet(clientRedis)).Methods("GET")
	subRouter.HandleFunc("/cartElement", HandleCartElementPost(clientRedis, gateWayClient)).Methods("POST")
	subRouter.HandleFunc("/cartElement", HandleCartElementPut(clientRedis, gateWayClient)).Methods("PUT")
	subRouter.HandleFunc("/cartElement/{customerID}/{elementID}", HandleCartElementDelete(clientRedis)).Methods("DELETE")

	return router

}
