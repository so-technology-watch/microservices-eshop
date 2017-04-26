package fr.sogeti.rest

import fr.sogeti.services.IEntityService
import io.vertx.scala.ext.web.{Router, RoutingContext}
import fr.sogeti.rest.common.{RequestHelper, JsonHelper}
import io.vertx.scala.core.http.HttpServerRequest

/**
 * Generic Service to manager http request
 * @param router the vert.x router
 * @param service the service adapted your entity
 * @param clazz the entity's class
 */
abstract class GenericService[Type](router : Router, service : IEntityService[Type], clazz : Class[Type]) {
  protected val jsonHelper: JsonHelper = new JsonHelper
  protected val contentType : String = "application/json"
  
  /**
   * find an entity by id
   * @param context the context with contains the id
   */
  protected def findById(context : RoutingContext) : Unit = {
    val request = context.request
    val response = context.response
    
    val id = RequestHelper.getParameterAsInt(request, "id")
    
    if( !id.isDefined ) {
      response.setStatusCode(404).end("entity not found")
      return
    }
      
    val entity = service.find(id.get)
    
    response.headers().add("content-type", contentType)
    response.end( jsonHelper.toJson( entity , true ) )
  }
  
  /**
   * get all the entities
   * @param context the request's context
   */
  protected def getAll(context : RoutingContext) : Unit = {
    val response = context.response
    val request = context.request
    val (begin, end) = getRange(request)
    val entities = service.getAll(begin, end)
    
    response.headers().add("Content-Range", "%d-%d".format(begin, end))
    response.headers().add("Accept-Range", "products 100")
    response.headers().add("content-type", contentType)
    
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
    
    response.headers().add("content-type", contentType)
    if( data.isDefined ) {
      val entity = jsonHelper.fromJson( data.get, clazz, true )
      if(entity.isDefined){
        service.create(entity.get)
        response.setStatusCode(201)
        response.end(jsonHelper.toJson(entity.get, true))
        return
      }
    }
    response.setStatusCode(200)
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
      if(entity.isDefined){
        service.update(entity.get)
      }
    }
    
    response.headers().add("content-type", contentType)
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
    val id = RequestHelper.getParameterAsInt(request, "id")
    
    if( !id.isDefined ) {
      response.setStatusCode(404).end("entity not found")
      return
    }
    val product = service.find(id.get)
    service.deleteById( id.get )
    
    response.headers().add("content-type", contentType)
    response.end("OK")
  }
  
  private def getRange(request : HttpServerRequest) : (Int, Int) = {
    var (begin, end) = (0, 100)
    val opt : Option[String] = request.getParam("range")
    if(opt.isDefined){
      val range : String = opt.get
      val spl = range.split("-")
      var (beginTmp, endTmp) = (Some(spl(0).toInt), Some(spl(1).toInt))
      if(beginTmp.isDefined && endTmp.isDefined){
        begin = beginTmp.get
        end = endTmp.get
        
        if(begin > end){
          var tmp = begin
          begin = end
          end = tmp
        }
        if( (end - begin) > 100 ){
          end = begin + 100
        }
      }
    }
    return (begin, end)
  }
}