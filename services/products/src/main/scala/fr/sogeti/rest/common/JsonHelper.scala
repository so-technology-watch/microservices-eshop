package fr.sogeti.rest.common

import com.google.gson.Gson
import scala.collection.JavaConverters._
import com.google.gson.GsonBuilder
import java.{util => ju}
import collection.JavaConversions._

class JsonHelper {
  
  /**
   * gson variable to produce gson
   */
  protected val gson : Gson = new Gson
  protected val gsonWithoutExpose : Gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
  
  /**
   * @param obj the object we want to serialize as json
   * @return the object as a json string
   */
  def toJson(obj : Any) : String = {
    return toJson(obj, false)
  }
  
  /**
   * @param obj the object we want to serialize as json
   * @param expose a boolean that indicates if we want to use the @expose notation
   * @return the object as a json string
   */
  def toJson(obj : Any, expose : Boolean) : String = {
    if(expose) {
      return toJson(obj, gsonWithoutExpose)
    }else{
      return toJson(obj, gson)
    }
  }
  
  private def toJson(obj : Any, gson : Gson) : String = {
    if(obj.isInstanceOf[List[_]]) {
      val list : List[Product] = obj.asInstanceOf[List[Product]]
      return gson.toJson(list.asJava)
    }
    else {
      return gson.toJson( obj )
    }
  }
  
  /**
   * @return the object corresponding to the given json
   * @param text the json
   * @param clazz the type of the wanted class
   * @param expose true if we want to serialize taking account of the gson expose annotation
   */
  def fromJson[Type](text : String, clazz : Class[Type], expose : Boolean) : Option[Type] = {
    try {
      if(expose){
        return Some(fromJson(text, clazz, gsonWithoutExpose))
      }else{
        return Some(fromJson(text, clazz, gson))
      }
    } catch {
      case e : Exception => return None
    }
  }
  
  /**
   * @param text the json
   * @param clazz the type of the wanted class
   * @return the option containing the object corresponding to the given json
   */
  def fromJson[Type](text : String, clazz : Class[Type]) : Option[Type] = {
    fromJson(text, clazz, false)
  }
    
  private def fromJson[Type](text : String, clazz : Class[Type], gson : Gson) : Type = {
     return gson.fromJson(text, clazz)   
  }
  
}