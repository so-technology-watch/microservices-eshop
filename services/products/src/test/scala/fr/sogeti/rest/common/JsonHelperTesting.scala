package fr.sogeti.rest.common

import com.google.gson.reflect.TypeToken
import scala.collection.mutable.ListBuffer
import com.google.gson.Gson
import fr.sogeti.entities.{Supplier, Product, Category}
import java.{util => ju}
import collection.JavaConversions._

class JsonHelperTesting extends JsonHelper {
  def listFromJsonProduct(text : String, expose : Boolean) : Option[List[Product]] = {
    try {
      val converter : Gson = if(expose) gsonWithoutExpose else gson
      val tokenType = new TypeToken[ju.List[Product]](){}.getType
      
      val list : ju.List[Product] = converter.fromJson(text, tokenType)
      val result = ListBuffer[Product]()
      for( e <- list ) result += e
      return Some(result.toList)
    } catch {
      case e : Exception => return None
    }
  }
  
  def listFromJsonSupplier(text : String, expose : Boolean) : Option[List[Supplier]] = {
    try {
      val converter : Gson = if(expose) gsonWithoutExpose else gson
      val tokenType = new TypeToken[ju.List[Supplier]](){}.getType
      
      val list : ju.List[Supplier] = converter.fromJson(text, tokenType)
      val result = ListBuffer[Supplier]()
      for( e <- list ) result += e
      return Some(result.toList)
    } catch {
      case e : Exception => return None
    }
  }
  
  def listFromJsonCategory(text : String, expose : Boolean) : Option[List[Category]] = {
    try {
      val converter : Gson = if(expose) gsonWithoutExpose else gson
      val tokenType = new TypeToken[ju.List[Category]](){}.getType
      
      val list : ju.List[Category] = converter.fromJson(text, tokenType)
      val result = ListBuffer[Category]()
      for( e <- list ) result += e
      return Some(result.toList)
    } catch {
      case e : Exception => return None
    }
  }  
}