package fr.sogeti.amqp

import fr.sogeti.rest.common.JsonHelper
import fr.sogeti.entities.Product
import fr.sogeti.services.{ProductService}
import com.rabbitmq.client.{Connection, Consumer, Channel, ConnectionFactory}

class ClientRabbitMQ(host : String, user : String, password : String, virtualHost : String, service : ProductService) {
  
  
  val connectionFactory : ConnectionFactory = new ConnectionFactory
  connectionFactory.setHost(host)
  connectionFactory.setUsername(user)
  connectionFactory.setPassword(password)
  connectionFactory.setVirtualHost(virtualHost)
  
  val client : Connection = connectionFactory.newConnection
  val json : JsonHelper = new JsonHelper
  val channel : Channel = client.createChannel
  
  def consume(exchange : String, exchangeType : String, routingKey : String) : Unit = {
    val consumer : Consumer = new MessageCallback(onMessageReceived, channel)
    channel.exchangeDeclare(exchange, exchangeType)
    val queueName = channel.queueDeclare.getQueue
    
    channel.queueBind(queueName, exchange, routingKey)
    channel.basicConsume(queueName, true, consumer)
  }
  
  def onMessageReceived(message : String) : Unit = {
    val opt : Option[Product] = json.fromJson(message, classOf[Product])
    if(opt.isDefined){
      val product = opt.get
      val productfound = service.find(product.getId)
      if(productfound != null){
        productfound.setPrice(product.getPrice)
        service.update(productfound)
      }
    }
  }
  
}