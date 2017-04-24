package fr.sogeti

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.verticles.{HttpServerVerticle, ClientAmqpVerticle}
import fr.sogeti.consul.{ServiceDiscovery, ConsulSingleton}
import io.vertx.core.{Handler, AsyncResult}
import com.ecwid.consul.v1.agent.model.NewService
import fr.sogeti.consul.Config
import org.slf4j.LoggerFactory
import fr.sogeti.dao.common.ManagerFactory
import fr.sogeti.entities.Product
import fr.sogeti.verticles.ClientAmqpVerticle
import fr.sogeti.verticles.ClientAmqpVerticle

object Main extends App {
  try{
    val consulClient = sys.env("CONSUL_CLIENT")
  }catch{
    case e : NoSuchElementException => {
      println("CONSUL_CLIENT environment variable not set properly")
      sys.exit(1)  
    }
  }
  
  if(!initTestDatabase){
    println("unable to log to the database")
  }
  
  val serviceDiscovery : ServiceDiscovery = ConsulSingleton.getServiceDiscoveryInstance
  
  val config : Config = ConsulSingleton.getConfigResolverInstance.getConfig
  println("found configuration : %s".format(config))
  
  serviceDiscovery.unregister
  serviceDiscovery.register(config.getAddress, config.getPort)
  
  val check : NewService.Check = new NewService.Check()
  check.setHttp("http://%s:%d".format(config.getAddress, config.getPort))
  check.setInterval("30s")
  
  val vertx = Vertx.vertx
  
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[HttpServerVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ClientAmqpVerticle] )
  
  /**
   * test the connection to the database in order to not be killed by vert.x
   * raspberry pi takes to long time to init the database and cause a timeout
   */
  private def initTestDatabase() : Boolean = {
    try {
      val manager = ManagerFactory.getInstance.createEntityManager
      manager.find(classOf[Product], -1)
      return true
    }catch{
      case e : Exception => {
        e.printStackTrace
        return false
      }
    }
  }
}
