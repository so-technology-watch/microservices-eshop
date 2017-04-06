package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.rest.ProductResource
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest

class ProductVerticle extends ScalaVerticle {
  
  override def start() = {
      val router : Router = Router.router(vertx)
      
      router.route.handler( BodyHandler.create )
      
      val productResource : ProductResource = new ProductResource( router )
      
      vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
        override def handle( request : HttpServerRequest ) : Unit = {
          router.accept(request)
        }
      } ).listen( 8080 )
  }
  
}