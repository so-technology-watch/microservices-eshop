package fr.sogeti

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.verticles.{HttpServerVerticle, ClientAmqpVerticle}
import fr.sogeti.consul.{ServiceDiscovery, ConsulSingleton}
import io.vertx.core.{Handler, AsyncResult}

object Main extends App {
  
  val serviceDiscovery : ServiceDiscovery = ConsulSingleton.getServiceDiscoveryInstance
  
  serviceDiscovery.unregister
  serviceDiscovery.register
  
  val vertx = Vertx.vertx
  
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[HttpServerVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ClientAmqpVerticle] )
  
}
