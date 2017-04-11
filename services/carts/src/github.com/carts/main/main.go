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
	//RabbitMQ
	clientRabbitMQ := &services.RabbitMQClient{}
	clientRabbitMQ.Connect("amqp://pi:pi@10.226.159.191:5672//pi")
	clientRabbitMQ.GetChannel()
	clientRabbitMQ.DeclareExchange("productUpdate", "topic")
	clientRabbitMQ.DeclareQueue("productUpdateQueue")
	clientRabbitMQ.BindQueue("productUpdateQueue", "productUpdateKey", "productUpdate")
	messages := clientRabbitMQ.Consume("productUpdateQueue")
	go func() {
		clientRabbitMQ.HandleMessages(clientRedis, messages)
	}()
	//Routing
	router := mux.NewRouter()
	subRouter := router.PathPrefix("/api/v1/").Subrouter()
	//Cart routes
	subRouter.HandleFunc("/carts/{ID}", handlers.HandleCartGet(clientRedis)).Methods("GET")
	subRouter.HandleFunc("/carts", handlers.HandleCartPost(clientRedis)).Methods("POST")
	subRouter.HandleFunc("/carts", handlers.HandleCartPut(clientRedis)).Methods("PUT")
	subRouter.HandleFunc("/carts/{ID}", handlers.HandleCartDelete(clientRedis)).Methods("DELETE")
	//CartElment routes
	subRouter.HandleFunc("/cartElement/{customerID}/{elementID}", handlers.HandleCartElementGet(clientRedis)).Methods("GET")
	subRouter.HandleFunc("/cartElement", handlers.HandleCartElementPost(clientRedis)).Methods("POST")
	subRouter.HandleFunc("/cartElement", handlers.HandleCartElementPut(clientRedis)).Methods("PUT")
	subRouter.HandleFunc("/cartElement/{customerID}/{elementID}", handlers.HandleCartElementDelete(clientRedis)).Methods("DELETE")
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
