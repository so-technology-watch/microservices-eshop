package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.services.ProductService
import fr.sogeti.amqp.ClientRabbitMQ
import fr.sogeti.consul.Config
import fr.sogeti.consul.ConsulSingleton
import fr.sogeti.consul.ConfigRabbitMq

class ClientAmqpVerticle extends ScalaVerticle {
  
  override def start() : Unit = {
    val productService : ProductService = new ProductService
    val config : Config = ConsulSingleton.getConfigResolverInstance.getConfig
    val configRabbitMq : ConfigRabbitMq = config.getConfigRabbitMq()
    
    // host, user, password, virtualhost
    val clientAmqp : ClientRabbitMQ = new ClientRabbitMQ(configRabbitMq.getHost, configRabbitMq.getUser, configRabbitMq.getPassword, configRabbitMq.getVirtualHost, productService)
    // exchange, exchange type, routing key
    clientAmqp.consume(configRabbitMq.getExchange, configRabbitMq.getExchangeType, configRabbitMq.getRoutingKey)
  }
  
}