package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import fr.sogeti.rest.CategoryResource
import fr.sogeti.services.CategoryService
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.rest.ProductResource
import fr.sogeti.rest.SupplierResource
import fr.sogeti.services.SupplierService
import fr.sogeti.services.ProductService

class HttpServerVerticle extends ScalaVerticle {
  override def start() : Unit = {
    val router : Router = Router.router(vertx)
    
    router.route.handler( BodyHandler.create )
    
    val productResource : ProductResource = new ProductResource( router, new ProductService )
    val categoryResource : CategoryResource = new CategoryResource( router, new CategoryService )
    val supplierResource : SupplierResource = new SupplierResource( router, new SupplierService )
    
    vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
      override def handle( request : HttpServerRequest ) : Unit = {
        router.accept(request)
      }
    } ).listen( 8080 )
  }
}