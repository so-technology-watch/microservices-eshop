package services

import "github.com/streadway/amqp"

//RabbitMQClient defines a RabbitMQ client
type RabbitMQClient struct {
	Connection *amqp.Connection
	Queue      *amqp.Queue
	Channel    *amqp.Channel
	Messages   *<-chan amqp.Delivery
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

	queue, err := r.Channel.QueueDeclare(name, false, false, true, false, nil)
	failOnError(err)
	*r.Queue = queue

}

//BindQueue binds a RabbitMQ queue to a routingKey ('topic')
func (r *RabbitMQClient) BindQueue(queueName string, routingKey string, exchangeName string) {

	err := r.Channel.QueueBind(queueName, routingKey, exchangeName, false, nil)
	failOnError(err)
}

//Consume consumes all the messages from a queue
func (r *RabbitMQClient) Consume() {

	var err error
	*r.Messages, err = r.Channel.Consume(r.Queue.Name, "", true, false, false, false, nil)
	failOnError(err)

}

func (r *RabbitMQClient) handleMessages() {

}
