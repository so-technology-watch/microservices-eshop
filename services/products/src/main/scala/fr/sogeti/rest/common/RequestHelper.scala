package fr.sogeti.rest.common

import io.vertx.scala.core.http.HttpServerRequest

object RequestHelper {
  
  /**
   * @param request the request containing the integer parameter we want to extract 
   * @param name the name of the paramater
   * @return an option containing the id, or None if the parameter for the given name isn't an integer
   */
  def getParameterAsInt(request : HttpServerRequest, name : String) : Option[Int] = {
    val opts : Option[String] = request.getParam(name)
    
    if( !opts.isDefined ) {
      return None
    }
    
    val idStr = opts.get
    
    if( !(idStr matches """\d+""") ){
      return None
    }
    val value = idStr.toInt
    
    return Some(value)
  }
  
}