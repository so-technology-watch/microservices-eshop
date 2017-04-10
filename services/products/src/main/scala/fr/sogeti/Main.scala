package fr.sogeti

import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.verticles.{ProductVerticle, CategoryVerticle, SupplierVerticle, ClientAmqpVerticle}

object Main extends App {
  
  val vertx = Vertx.vertx
  
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ProductVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[CategoryVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[SupplierVerticle] )
  vertx.deployVerticle( ScalaVerticle.nameForVerticle[ClientAmqpVerticle] )
  
}
