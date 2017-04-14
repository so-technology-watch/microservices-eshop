package main

import (
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/carts/handlers"
	"github.com/carts/services"
)

func main() {

	clientRedis := &services.RedisClient{}
	clientRedis.CreateClient()
	router := handlers.Router(clientRedis)

	clientRabbitMQ := &services.RabbitMQClient{}
	clientRabbitMQ.Connect("amqp://pi:pi@10.226.159.191:5672//pi")
	clientRabbitMQ.GetChannel()
	clientRabbitMQ.DeclareExchange("productUpdate", "topic")
	clientRabbitMQ.DeclareQueue("productUpdate")
	clientRabbitMQ.BindQueue("productUpdate", "productUpdateKey", "productUpdate")
	messages := clientRabbitMQ.Consume("productUpdate")
	go func() {
		clientRabbitMQ.HandleMessages(clientRedis, messages)
	}()

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
