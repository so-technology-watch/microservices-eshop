package fr.sogeti.rest

import fr.sogeti.rest.common.{JsonHelper, RequestHelper}
import fr.sogeti.services.ProductService
import io.vertx.scala.ext.web.{Router, RoutingContext}
import fr.sogeti.rest.common.BaseHandler
import io.vertx.core.Handler
import io.vertx.scala.core.http.HttpServerRequest

class ProductResource(router : Router) {
  
  val requestHelper : RequestHelper = new RequestHelper
  val jsonHelper: JsonHelper = new JsonHelper
  val productService : ProductService = new ProductService
  
  /**
   * manage a get request on products to find a specific product
   * get the id parameter
   */
  router.get("/products/:id").handler(new BaseHandler {
    override def handle( context : RoutingContext ) = findById(context)
  } )
  
  /**
   * manage a get request on products to get all the products
   */
  router.get("/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = getAll(context)
  } )
  
  /**
   * manage a post request on products to create a new one
   */
  router.post("/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = create(context)
  } )
  
  /**
   * manage a put request on products to update a product 
   */
  router.put("/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = update(context)
  } )
  
  /**
   * manage a delete request on products to update a product
   */
  router.delete("/products").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = delete(context)
  } )
  
  /**
   * find a product by id
   * @param context the context with contains the id
   */
  def findById(context : RoutingContext) : Unit = {
    val request = context.request
    val response = context.response
    
    val id = requestHelper.getParameterAsInt(request, "id")
    
    if( !id.isDefined ) {
      response.setStatusCode(404).end("product not found")
      return
    }
      
    val product = productService.find(id.get)
    response.end( jsonHelper.toJson( product ) )
  }
  
  /**
   * get all the products
   * @param context the request's context
   */
  def getAll(context : RoutingContext) : Unit = {
    context.response().end("not yet implemented")
  }
  
  /**
   * create a new product
   * retrieve data from the request's body
   * @param context the request's context
   */
  def create(context : RoutingContext) : Unit = {
    context.response().end("not yet implemented")
  }
  
  /**
   * update a product
   * retrieve data from the request's body
   * @param context the request's context
   */
  def update(context : RoutingContext) : Unit = {
    context.response().end("not yet implemented")
  }
  
  /**
   * delete a product
   * retrieve data from request's parameters
   * @param context the request's context
   */
  def delete(context : RoutingContext) : Unit = {
    context.response().end("not yet implemented")
  }
}