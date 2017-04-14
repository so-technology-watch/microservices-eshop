package services

import (
	"encoding/json"
	"log"

	"github.com/streadway/amqp"
)

//RabbitMQClient defines a RabbitMQ client
type RabbitMQClient struct {
	Connection *amqp.Connection
	Channel    *amqp.Channel
}

//Connect connects to RabbitMQ broker
func (r *RabbitMQClient) Connect(url string) {

	connection, err := amqp.Dial(url)
	failOnError(err)
	r.Connection = connection
	log.Println("Connection with RabbitMQ successfully established.")

}

//GetChannel retrieves the RabbitMQ channel
func (r *RabbitMQClient) GetChannel() {

	var err error
	r.Channel, err = r.Connection.Channel()
	failOnError(err)
	log.Println("Channel successfully retreived.")
}

//DeclareExchange declares a new RabbitMQ exchange
func (r *RabbitMQClient) DeclareExchange(name string, exhangeMode string) {

	err := r.Channel.ExchangeDeclare(name, exhangeMode, true, false, false, false, nil)
	failOnError(err)
	log.Println("Exchange successfully declared.")
}

//DeclareQueue declares a new RabbitMQ queue
func (r *RabbitMQClient) DeclareQueue(name string) {

	_, err := r.Channel.QueueDeclare(name, false, false, true, false, nil)
	failOnError(err)
	log.Println("Queue successfully declared.")
}

//BindQueue binds a RabbitMQ queue to a routingKey ('topic')
func (r *RabbitMQClient) BindQueue(queueName string, routingKey string, exchangeName string) {

	err := r.Channel.QueueBind(queueName, routingKey, exchangeName, false, nil)
	failOnError(err)
	log.Println("Queue successfully binded.")

}

//Consume consumes all the messages from a queue
func (r *RabbitMQClient) Consume(queueName string) *<-chan amqp.Delivery {

	var err error
	messages, err := r.Channel.Consume(queueName, "", true, false, false, false, nil)
	failOnError(err)
	log.Println("Consuming.")
	return &messages

}

//HandleMessages handles the reeived messages.
func (r *RabbitMQClient) HandleMessages(redisClient *RedisClient, messages *<-chan amqp.Delivery) {

	log.Println("Handling messages.")

	for message := range *messages {

		productUpdate := jsonToProductUpdate(message.Body)
		redisClient.ChangeProductPrice(productUpdate.ProductID, productUpdate.UnitPrice)
		log.Println("Message has been handled.")

	}

}

func jsonToProductUpdate(productUpdateJSON []byte) *ProductUpdate {

	productUpdate := &ProductUpdate{}
	json.Unmarshal([]byte(productUpdateJSON), productUpdate)

	return productUpdate
}
