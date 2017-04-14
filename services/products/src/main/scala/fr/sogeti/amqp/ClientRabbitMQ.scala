package fr.sogeti.amqp

import fr.sogeti.rest.common.JsonHelper
import fr.sogeti.entities.Product
import fr.sogeti.services.{ProductService}
import com.rabbitmq.client.{Connection, Consumer, Channel, ConnectionFactory}

/**
 * Construct a RabbitMQ client for the given parameters
 * @param host the host
 * @param password the password
 * @param virtualHost the virtual host
 * @param service the product service, for CRUD access
 */
class ClientRabbitMQ(host : String, user : String, password : String, virtualHost : String, service : ProductService) {
  
  private val connectionFactory : ConnectionFactory = new ConnectionFactory
  connectionFactory.setHost(host)
  connectionFactory.setUsername(user)
  connectionFactory.setPassword(password)
  connectionFactory.setVirtualHost(virtualHost)
  
  private val client : Connection = connectionFactory.newConnection
  private val json : JsonHelper = new JsonHelper
  private val channel : Channel = client.createChannel
  
  /**
   * @param exchange the exchange
   * @param routingKey the routing key
   */
  def consume(exchange : String, exchangeType : String, routingKey : String) : Unit = {
    val consumer : Consumer = new MessageCallback(onMessageReceived, channel)
    channel.exchangeDeclare(exchange, exchangeType)
    val queueName = channel.queueDeclare.getQueue
    
    channel.queueBind(queueName, exchange, routingKey)
    channel.basicConsume(queueName, true, consumer)
  }
  
  /**
   * callback function when receiving a message
   * @param message the received message
   */
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