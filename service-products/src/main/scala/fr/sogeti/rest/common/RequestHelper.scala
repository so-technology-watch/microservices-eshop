package fr.sogeti.rest.common

import io.vertx.scala.core.http.HttpServerRequest

class RequestHelper {
  
  def getParameterAsInt(request : HttpServerRequest, name : String) : Option[Int] = {
    val opts : Option[String] = request.getParam("id")
    
    if( opts == null ) {
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