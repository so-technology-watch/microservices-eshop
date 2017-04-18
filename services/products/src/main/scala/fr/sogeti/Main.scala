package fr.sogeti

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.verticles.{HttpServerVerticle, ClientAmqpVerticle}
import fr.sogeti.consul.{ServiceDiscovery, ConsulSingleton}
import io.vertx.core.{Handler, AsyncResult}
import com.ecwid.consul.v1.agent.model.NewService
import fr.sogeti.consul.Config

object Main extends App {
  try{
    val consulClient = sys.env("CONSUL_CLIENT")
  }catch{
    case e : NoSuchElementException => {
      println("CONSUL_CLIENT environment variable not set properly")
      sys.exit(1)  
    }
  }
  
  val serviceDiscovery : ServiceDiscovery = ConsulSingleton.getServiceDiscoveryInstance
  
  val config : Config = ConsulSingleton.getConfigResolverInstance.getConfig
  
  
  serviceDiscovery.unregister
  serviceDiscovery.register(config.getPort)
  
  val check : NewService.Check = new NewService.Check()
  check.setHttp("http://localhost:%d".format(config.getPort))
  check.setInterval("30s")
  
  val vertx = Vertx.vertx
  
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[HttpServerVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ClientAmqpVerticle] )
  
}
