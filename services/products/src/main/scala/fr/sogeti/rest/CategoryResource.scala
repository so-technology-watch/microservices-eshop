package fr.sogeti.rest

import fr.sogeti.entities.Category
import io.vertx.scala.ext.web.RoutingContext
import fr.sogeti.rest.common.BaseHandler
import io.vertx.scala.ext.web.Router
import fr.sogeti.services.CategoryService

class CategoryResource(router : Router, categoryService : CategoryService) extends GenericService[Category](router, categoryService, classOf[Category]){
  /**
   * manage a get request on products to find a specific product
   * get the id parameter
   */
  router.get("/category/:id").handler(new BaseHandler {
    override def handle( context : RoutingContext ) = findById(context)
  } )
  
  /**
   * manage a get request on products to get all the products
   */
  router.get("/category").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = getAll(context)
  } )
  
  /**
   * manage a post request on products to create a new one
   */
  router.post("/category").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = create(context)
  } )
  
  /**
   * manage a put request on products to update a product 
   */
  router.put("/category").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = update(context)
  } )
  
  /**
   * manage a delete request on products to update a product
   */
  router.delete("/category/:id").handler( new BaseHandler {
    override def handle( context : RoutingContext ) = delete(context)
  } )
}