package fr.sogeti.rest

import fr.sogeti.services.IEntityService
import io.vertx.scala.ext.web.{Router, RoutingContext}
import fr.sogeti.rest.common.{RequestHelper, JsonHelper}

/**
 * Generic Service to manager http request
 * @param router the vert.x router
 * @param service the service adapted your entity
 * @param clazz the entity's class
 */
abstract class GenericService[Type](router : Router, service : IEntityService[Type], clazz : Class[Type]) {
  protected val requestHelper : RequestHelper = new RequestHelper
  protected val jsonHelper: JsonHelper = new JsonHelper
  
  /**
   * find an entity by id
   * @param context the context with contains the id
   */
  protected def findById(context : RoutingContext) : Unit = {
    val request = context.request
    val response = context.response
    
    val id = requestHelper.getParameterAsInt(request, "id")
    
    if( !id.isDefined ) {
      response.setStatusCode(404).end("product not found")
      return
    }
      
    val entity = service.find(id.get)
    response.end( jsonHelper.toJson( entity , true ) )
  }
  
  /**
   * get all the entities
   * @param context the request's context
   */
  protected def getAll(context : RoutingContext) : Unit = {
    val response = context.response
    val entities = service.getAll()
    
    response.end( jsonHelper.toJson( entities , true ) )
  }
  
  /**
   * create a new entity
   * retrieve data from the request's body
   * @param context the request's context
   */
  def create(context : RoutingContext) : Unit = {
    val request = context.request
    val response = context.response
    val data : Option[String] = context.getBodyAsString
    
    if( data.isDefined ) {
      val entity = jsonHelper.fromJson( data.get, clazz, true )
      service.create(entity)
    }
    response.end("OK")
  }
  
  /**
   * update a product
   * retrieve data from the request's body
   * @param context the request's context
   */
  def update(context : RoutingContext) : Unit = {
    val request = context.request
    val response = context.response
    val data : Option[String] = context.getBodyAsString
    
    if( data.isDefined ) {
      val entity = jsonHelper.fromJson( data.get, clazz, true )
      service.update(entity)
    }
    response.end("OK")
  }
  
  /**
   * delete a product
   * retrieve data from request's parameters
   * @param context the request's context
   */
  def delete(context : RoutingContext) : Unit = {
    val request = context.request
    val response = context.response
    val id = requestHelper.getParameterAsInt(request, "id")
    
    if( !id.isDefined ) {
      response.setStatusCode(404).end("product not found")
      return
    }
    val product = service.find(id.get)
    service.deleteById( id.get )
    response.end("OK")
  }  
}