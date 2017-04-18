package fr.sogeti.verticles

import fr.sogeti.services.IEntityService
import fr.sogeti.entities.Category
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.rest.CategoryResource
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import fr.sogeti.services.CategoryServiceMock

class StubCategoryVerticle extends HttpServerVerticle {
  val categoryService : IEntityService[Category] = new CategoryServiceMock().get()
  
  override def start() = {
      val router : Router = Router.router(vertx)
      
      router.route.handler( BodyHandler.create )
      
      val categoryResource : CategoryResource = new CategoryResource( router, categoryService )
      
      vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
        override def handle( request : HttpServerRequest ) : Unit = {
          router.accept(request)
        }
      } ).listen( 8080 )
  }
}