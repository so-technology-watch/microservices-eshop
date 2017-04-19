package main

import (
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/carts/consul"
	"github.com/carts/handlers"
	"github.com/carts/services"
)

func main() {

	//Creating consul client
	consulClient := &consul.Client{}
	consulClient.CreateClient()
	//Retrieving configuration
	config := consulClient.RetrieveConfig()
	//Creating the redis client
	clientRedis := &services.RedisClient{}
	clientRedis.CreateClient(config.RedisConf.Address, config.RedisConf.PWD, config.RedisConf.DB)
	//Creating the HTTP router
	router := handlers.Router(clientRedis)
	//Creating and initializing the rabitMQ client
	clientRabbitMQ := &services.RabbitMQClient{}
	clientRabbitMQ.Connect(config.RabbitMQConf.Host)
	clientRabbitMQ.GetChannel()
	clientRabbitMQ.DeclareExchange("productUpdate", "topic")
	clientRabbitMQ.DeclareQueue("productUpdate")
	clientRabbitMQ.BindQueue("productUpdate", config.RabbitMQConf.RoutingKey, "productUpdate")
	messages := clientRabbitMQ.Consume("productUpdate")
	go func() {
		clientRabbitMQ.HandleMessages(clientRedis, messages)
	}()
	//Http server creation
	server := &http.Server{

		Handler:           router,
		Addr:              config.Host + ":" + config.Port,
		WriteTimeout:      15 * time.Second,
		ReadHeaderTimeout: 15 * time.Second,
	}
	//Registering the service
	consulClient.Register(config)
	//starting the http server
	fmt.Printf("Listenig on : %s", server.Addr)
	log.Fatal(server.ListenAndServe())

}

//failOnError checks for errors and panics to them if it's the case
func failOnError(err error) {
	if err != nil {
		panic(err)
	}
}
