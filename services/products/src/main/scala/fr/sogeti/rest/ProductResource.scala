package fr.sogeti.rest

import fr.sogeti.rest.common.{JsonHelper, RequestHelper}
import fr.sogeti.services.ProductService
import io.vertx.scala.ext.web.{Router, RoutingContext}
import fr.sogeti.rest.common.BaseHandler
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest
import io.vertx.scala.ext.web.handler.BodyHandler
import scala.collection.mutable.Buffer
import com.google.gson.Gson
import fr.sogeti.entities.Product

class ProductResource(router : Router, productService : ProductService) extends GenericService[Product](router, productService, classOf[Product]){
  
  /**
   * manage a get request on products to find a specific product
   * get the id parameter
   */
  router.get("/api/v1/products/:id").handler(new BaseHandler {
    override def handle( context : RoutingContext ) = findById(context)
  } )
  
  /**
   * manage a get request on products to get all the products
   */
  router.get("/api/v1/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = getAll(context)
  } )
  
  /**
   * manage a post request on products to create a new one
   */
  router.post("/api/v1/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = create(context)
  } )
  
  /**
   * manage a put request on products to update a product 
   */
  router.put("/api/v1/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = update(context)
  } )
  
  /**
   * manage a delete request on products to update a product
   */
  router.delete("/api/v1/products/:id").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = delete(context)
  } )
  
}