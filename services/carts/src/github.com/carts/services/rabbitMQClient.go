package services

import (
	"encoding/json"

	"github.com/streadway/amqp"
)

//RabbitMQClient defines a RabbitMQ client
type RabbitMQClient struct {
	Connection *amqp.Connection
	Channel    *amqp.Channel
}

//ProductUpdate defines the data needed for a product price change
type ProductUpdate struct {
	ProductID int
	UnitPrice float32
}

//Connect connects to RabbitMQ broker
func (r *RabbitMQClient) Connect(url string) {

	connection, err := amqp.Dial(url)
	failOnError(err)
	r.Connection = connection

}

//GetChannel retrieves the RabbitMQ channel
func (r *RabbitMQClient) GetChannel() {

	var err error
	r.Channel, err = r.Connection.Channel()
	failOnError(err)
}

//DeclareExchange declares a new RabbitMQ exchange
func (r *RabbitMQClient) DeclareExchange(name string, exhangeMode string) {

	err := r.Channel.ExchangeDeclare(name, exhangeMode, true, false, false, false, nil)
	failOnError(err)
}

//DeclareQueue declares a new RabbitMQ queue
func (r *RabbitMQClient) DeclareQueue(name string) {

	_, err := r.Channel.QueueDeclare(name, false, false, true, false, nil)
	failOnError(err)
}

//BindQueue binds a RabbitMQ queue to a routingKey ('topic')
func (r *RabbitMQClient) BindQueue(queueName string, routingKey string, exchangeName string) {

	err := r.Channel.QueueBind(queueName, routingKey, exchangeName, false, nil)
	failOnError(err)
}

//Consume consumes all the messages from a queue
func (r *RabbitMQClient) Consume(queueName string) *<-chan amqp.Delivery {

	var err error
	messages, err := r.Channel.Consume(queueName, "", true, false, false, false, nil)
	failOnError(err)

	return &messages

}

//HandleMessages handles the reeived messages.
func (r *RabbitMQClient) HandleMessages(redisClient *RedisClient, messages *<-chan amqp.Delivery) {

	for message := range *messages {

		productUpdate := jsonToProductUpdate(message.Body)
		redisClient.ChangeProductPrice(productUpdate.ProductID, productUpdate.UnitPrice)

	}
}

func jsonToProductUpdate(productUpdateJSON []byte) *ProductUpdate {

	productUpdate := &ProductUpdate{}
	json.Unmarshal([]byte(productUpdateJSON), productUpdate)

	return productUpdate
}
