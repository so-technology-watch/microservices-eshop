package fr.sogeti.verticles

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.ext.web.Router
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.scala.ext.web.handler.BodyHandler
import fr.sogeti.rest.{ProductResource, SupplierResource, CategoryResource}
import fr.sogeti.services.{SupplierService, ProductService, CategoryService}
import fr.sogeti.consul.Config
import fr.sogeti.consul.ConsulSingleton
import fr.sogeti.consul.ServiceDiscovery

class HttpServerVerticle extends ScalaVerticle {
  override def start() : Unit = {
    val router : Router = Router.router(vertx)
    
    router.route.handler( BodyHandler.create )
    
    val productResource : ProductResource = new ProductResource( router, new ProductService )
    val categoryResource : CategoryResource = new CategoryResource( router, new CategoryService )
    val supplierResource : SupplierResource = new SupplierResource( router, new SupplierService )    
    
    val config : Config = ConsulSingleton.getConfigResolverInstance.getConfig
    
    vertx.createHttpServer.requestHandler( new Handler[HttpServerRequest]() {
      override def handle( request : HttpServerRequest ) : Unit = {
        println("Request on : %s".format(request.uri))
        router.accept(request)
      }
    } ).listen( config.getPort, ServiceDiscovery.getLocalAddress )
  }
}