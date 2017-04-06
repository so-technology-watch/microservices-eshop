package fr.sogeti.rest.common

import io.vertx.core.Handler
import com.google.gson.Gson

class JsonHelper {
  
  /**
   * gson variable to produce gson
   */
  protected val gson : Gson = new Gson
  
  /**
   * @return a json object of the found product, null otherwise
   */
  def toJson(obj : Any) : String = {
    
    if(obj.isInstanceOf[List[_]]) {
      //val values : List[_] = (List[_]) obj
      //return values.toJson(values.asJava)
      return "not implemented"
    }
    else {
      return gson.toJson( obj )
    }
  }
  
}