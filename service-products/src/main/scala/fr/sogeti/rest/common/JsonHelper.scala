package fr.sogeti.rest.common

import io.vertx.core.Handler
import com.google.gson.Gson
import scala.collection.JavaConverters._
import com.google.gson.GsonBuilder
import java.{util => ju}
import scala.collection.mutable.ArraySeq
import scala.collection.mutable.ListBuffer
import collection.JavaConversions._
import java.lang.reflect.Type
import com.google.gson.reflect.TypeToken
import fr.sogeti.entities.Product

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
      val list : List[Product] = obj.asInstanceOf[List[Product]]
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
  
  def listFromJson(text : String, expose : Boolean) : List[Product] = {
    val converter : Gson = if(expose) gson else gson
    val tokenType = new TypeToken[ju.List[Product]](){}.getType
    println(tokenType)
    
    val list : ju.List[Product] = converter.fromJson(text, tokenType)
    val result = ListBuffer[Product]()
    for( e <- list ) result += e
    return result.toList
  }
  
  private def fromJson[Type](text : String, clazz : Class[Type], gson : Gson) : Type = {
     return gson.fromJson(text, clazz)   
  }
}