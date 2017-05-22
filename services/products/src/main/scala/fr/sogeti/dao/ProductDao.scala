package fr.sogeti.dao

import fr.sogeti.dao.common.{GenericDAO, ManagerFactory}
import fr.sogeti.entities.Product
import fr.sogeti.dao.common.{TransactionStrategy, ITransactionStrategy}
import javax.persistence.Query
import scala.collection.JavaConverters._

class ProductDao extends GenericDAO[Product, Integer]( classOf[Product], ManagerFactory.createEntityManager ) {
  
  def findByCriterias(criterias : String) : Array[Product] = {
    var found : Array[Product] = null
    val strategy : ITransactionStrategy = new ITransactionStrategy {
      override def execute() : Unit = {
        val className : String = classOf[Product].getName
        var queryStr : String = "SELECT id, reference, designation, description, price, id_category, id_supplier, image from Product WHERE "
        val search : String = criterias.replace(" ", "_")
        val spl : Array[String] = criterias.split(" ")
        
        queryStr += "designation LIKE '%"+search+"%'"
        queryStr += " OR reference LIKE '%"+search+"%'"
        
        for( i <- 0 until spl.length ){
          val criteria : String = spl(i)
          queryStr += " OR designation LIKE '%"+criteria+"%'"
          queryStr += " OR reference LIKE '%"+criteria+"%'"
        }
        
        val query : Query = manager.createNativeQuery(queryStr, classOf[Product])
        val result = query.getResultList
        found = result.toArray( new Array[Product](result.size) )
      }
    }
    new TransactionStrategy(manager, strategy).execute
    return found
  }
  
  def findBySupplier(idSupplier : Int) : Array[Product] = {
    var found : Array[Product] = null
    val strategy : ITransactionStrategy = new ITransactionStrategy {
      override def execute() : Unit = {
        val className : String = classOf[Product].getName
        var queryStr : String = "SELECT id, reference, designation, description, price, id_category, id_supplier, image from Product WHERE id_supplier=:idSupplier"
        
        val query : Query = manager.createNativeQuery(queryStr, classOf[Product])
        query.setParameter("idSupplier", idSupplier)
        val result = query.getResultList
        found = result.toArray( new Array[Product](result.size) )
      }
    }
    new TransactionStrategy(manager, strategy).execute
    return found    
  }
}