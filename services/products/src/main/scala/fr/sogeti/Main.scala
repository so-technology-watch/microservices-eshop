package fr.sogeti

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle
import com.ecwid.consul.v1.ConsulClient
import fr.sogeti.consul.Config
import fr.sogeti.consul.ConfigRabbitMq
import fr.sogeti.consul.ConfigResolver
import fr.sogeti.verticles.{HttpServerVerticle, ClientAmqpVerticle}
import fr.sogeti.consul.ServiceDiscovery
import fr.sogeti.consul.ConsulSingleton
import io.vertx.core.Handler
import io.vertx.core.AsyncResult

object Main extends App {
  
  val serviceDiscovery : ServiceDiscovery = ConsulSingleton.getServiceDiscoveryInstance
  
  serviceDiscovery.register
  
  val vertx = Vertx.vertx
  
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[HttpServerVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ClientAmqpVerticle] )
  
  vertx.close(new Handler[AsyncResult[Unit]]{
    override def handle(result : AsyncResult[Unit]) : Unit = {
      serviceDiscovery.unregister      
    }
  })
  
}
