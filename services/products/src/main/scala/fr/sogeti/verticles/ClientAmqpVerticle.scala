package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.services.ProductService
import fr.sogeti.services.IEntityService
import fr.sogeti.entities.Product
import fr.sogeti.amqp.ClientRabbitMQ
import fr.sogeti.amqp.ClientRabbitMQ
import fr.sogeti.amqp.ClientRabbitMQ
import fr.sogeti.amqp.ClientRabbitMQ

class ClientAmqpVerticle extends ScalaVerticle {
  
  override def start() : Unit = {
    val productService : ProductService = new ProductService
    // host, user, password, virtualhost
    val clientAmqp : ClientRabbitMQ = new ClientRabbitMQ("10.226.159.191","pi","pi","/pi", productService)
    // exchange, exchange type, routing key
    clientAmqp.subscribe("test", "topic", "greeting")
  }
  
}