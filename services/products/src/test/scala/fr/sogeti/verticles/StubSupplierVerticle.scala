package fr.sogeti.verticles

import fr.sogeti.entities.Supplier
import fr.sogeti.services.SupplierServiceMock
import io.vertx.scala.ext.web.Router
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.rest.SupplierResource
import io.vertx.scala.core.http.HttpServerRequest
import fr.sogeti.services.IEntityService
import io.vertx.core.Handler

class StubSupplierVerticle extends SupplierVerticle {
  val supplierService : IEntityService[Supplier] = new SupplierServiceMock().get()
  
  override def start() = {
      val router : Router = Router.router(vertx)
      
      router.route.handler( BodyHandler.create )
      
      val supplierResource : SupplierResource = new SupplierResource( router, supplierService )
      
      vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
        override def handle( request : HttpServerRequest ) : Unit = {
          router.accept(request)
        }
      } ).listen( 8080 )
  }
}