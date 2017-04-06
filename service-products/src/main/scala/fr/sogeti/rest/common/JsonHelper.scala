package fr.sogeti.rest.common

import io.vertx.core.Handler
import com.google.gson.Gson
import scala.collection.JavaConverters._
import com.google.gson.GsonBuilder

class JsonHelper {
  
  /**
   * gson variable to produce gson
   */
  protected val gson : Gson = new Gson
  protected val gsonWithoutExpose : Gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
  
  /**
   * @return a json object of the found product, null otherwise
   */
  def toJson(obj : Any) : String = {
    return toJson(obj, false)
  }
  
  def toJson(obj : Any, expose : Boolean) : String = {
    if(expose) {
      return toJson(obj, gsonWithoutExpose)
    }else{
      return toJson(obj, gson)
    }
  }
  
  private def toJson(obj : Any, gson : Gson) : String = {
    if(obj.isInstanceOf[List[_]]) {
      val list : List[Any] = obj.asInstanceOf[List[Any]]
      return gson.toJson(list.asJava)
    }
    else {
      return gson.toJson( obj )
    }
  }
  
  /**
   * @return the object corresponding to the given json
   * @param textthe json
   * @param clazz the type of the wanted class
   * @param expose true if we want to serialize taking account of the gson expose annotation
   */
  def fromJson[Type](text : String, clazz : Class[Type], expose : Boolean) : Type = {
    if(expose){
      return fromJson(text, clazz, gsonWithoutExpose)
    }else{
      return fromJson(text, clazz, gson)
    }
  }
  
  def fromJson[Type](text : String, clazz : Class[Type]) : Type = {
    fromJson(text, clazz, false)
  }
  
  private def fromJson[Type](text : String, clazz : Class[Type], gson : Gson) : Type = {
    if(clazz.isInstanceOf[List[_]]) {
      return gson.fromJson(text, classOf[java.util.Collection[_]])
    }else{
       return gson.fromJson(text, clazz)
    }    
  }
}