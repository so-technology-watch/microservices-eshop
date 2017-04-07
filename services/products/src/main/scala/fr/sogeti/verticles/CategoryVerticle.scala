package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.services.CategoryService
import fr.sogeti.rest.CategoryResource

class CategoryVerticle extends ScalaVerticle {
  override def start() = {
      val router : Router = Router.router(vertx)
      
      router.route.handler( BodyHandler.create )
      
      val productResource : CategoryResource = new CategoryResource( router, new CategoryService )
      
      vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
        override def handle( request : HttpServerRequest ) : Unit = {
          router.accept(request)
        }
      } ).listen( 8080 )
  }
}