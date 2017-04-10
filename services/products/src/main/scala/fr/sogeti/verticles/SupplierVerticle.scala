package fr.sogeti.verticles

import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.rest.SupplierResource
import fr.sogeti.services.SupplierService
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.core.Handler

class SupplierVerticle extends ScalaVerticle {
  override def start() = {
      val router : Router = Router.router(vertx)
      
      router.route.handler( BodyHandler.create )
      
      val productResource : SupplierResource = new SupplierResource( router, new SupplierService )
      
      vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
        override def handle( request : HttpServerRequest ) : Unit = {
          router.accept(request)
        }
      } ).listen( 8080 )
  }
}