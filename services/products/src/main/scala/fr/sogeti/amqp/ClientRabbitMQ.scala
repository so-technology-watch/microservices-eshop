package fr.sogeti.amqp

import fr.sogeti.rest.common.JsonHelper
import fr.sogeti.entities.Product
import fr.sogeti.services.{ProductService, SupplierService}
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Connection
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Consumer

class ClientMqtt(brokerUrl : String, service : ProductService) {
  
  
  val connectionFactory : ConnectionFactory = new ConnectionFactory
  connectionFactory.setHost(brokerUrl)
  
  val client : Connection = connectionFactory.newConnection
  val json : JsonHelper = new JsonHelper
  val channel : Channel = client.createChannel
  
  def subscribe(queueName : String) : Unit = {
    channel.queueDeclare(queueName, false, false, false, null)
    val consumer : Consumer = new MessageCallback(onMessageReceived, channel)
    channel.basicConsume(queueName, true, consumer)
  }
  
  def onMessageReceived(message : String) : Unit = {
    
    val product : Product = json.fromJson(message, classOf[Product])
    val productfound = service.find(product.getId)
    
    if(productfound != null){
      productfound.setPrice(product.getPrice)
      service.update(productfound)
    }
    
  }
  
}