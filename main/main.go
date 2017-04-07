package main

import (
	"log"
	"net/http"
	"time"

	"fmt"

	"github.com/carts/handlers"
	"github.com/carts/services"
	"github.com/gorilla/mux"
)

func main() {

	//Creation of the redis Client
	clientRedis := &services.RedisClient{}
	clientRedis.CreateClient()
	router := mux.NewRouter()
	//Cart routes
	router.HandleFunc("/carts", handlers.HandleCartGet(clientRedis)).Methods("GET")
	router.HandleFunc("/carts", handlers.HandleCartPost(clientRedis)).Methods("POST")
	router.HandleFunc("/carts", handlers.HandleCartPut(clientRedis)).Methods("PUT")
	router.HandleFunc("/carts", handlers.HandleCartDelete(clientRedis)).Methods("DELETE")
	//CartElment routes
	router.HandleFunc("/cartElement", handlers.HandleCartElementGet(clientRedis)).Methods("GET")
	router.HandleFunc("/cartElement", handlers.HandleCartElementPost(clientRedis)).Methods("POST")
	router.HandleFunc("/cartElement", handlers.HandleCartElementPut(clientRedis)).Methods("PUT")
	router.HandleFunc("/cartElement", handlers.HandleCartElementDelete(clientRedis)).Methods("DELETE")
	//Http server creation
	server := &http.Server{

		Handler:           router,
		Addr:              "localhost:8080",
		WriteTimeout:      15 * time.Second,
		ReadHeaderTimeout: 15 * time.Second,
	}

	//Launchment of the http server
	fmt.Printf("Listenig on : %s", server.Addr)
	log.Fatal(server.ListenAndServe())

}

//failOnError checks for errors and panics to them if it's the case
func failOnError(err error) {
	if err != nil {
		panic(err)
	}
}
