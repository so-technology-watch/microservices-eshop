package fr.sogeti.verticles

import io.vertx.scala.ext.web.Router
import fr.sogeti.rest.ProductResource
import fr.sogeti.services.ProductService
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.services.IEntityService
import fr.sogeti.entities.Product

class StubProductVerticle extends ProductVerticle {
  val productService : IEntityService[Product] = new ProductServiceMock().get()
  
  override def start() = {
      val router : Router = Router.router(vertx)
      
      router.route.handler( BodyHandler.create )
      
      val productResource : ProductResource = new ProductResource( router, productService )
      
      vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
        override def handle( request : HttpServerRequest ) : Unit = {
          router.accept(request)
        }
      } ).listen( 8080 )
  }
  
}