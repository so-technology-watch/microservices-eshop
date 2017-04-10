package fr.sogeti

import javax.persistence.{Persistence,EntityManager,EntityManagerFactory,Query}
import scala.collection.JavaConversions._
import fr.sogeti.entities._
import io.vertx.scala.core.Vertx
import scala.concurrent.ExecutionContext
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.scala.ext.web.Router
import fr.sogeti.dao.common.GenericDAO
import fr.sogeti.verticles.ProductVerticle
import fr.sogeti.services.ProductService
import fr.sogeti.verticles.CategoryVerticle
import fr.sogeti.verticles.SupplierVerticle
import fr.sogeti.verticles.ClientAmqpVerticle

object Main extends App {
  
  val vertx = Vertx.vertx
  
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ProductVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[CategoryVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[SupplierVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ClientAmqpVerticle] )
  
}
