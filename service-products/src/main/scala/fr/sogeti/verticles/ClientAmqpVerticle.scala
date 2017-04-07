package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import fr.sogeti.services.ProductService
import fr.sogeti.services.IEntityService
import fr.sogeti.entities.Product

class ClientAmqpVerticle extends ScalaVerticle {
  
  override def start() : Unit = {
    val productService : ProductService = new ProductService  
    
  }
  
}