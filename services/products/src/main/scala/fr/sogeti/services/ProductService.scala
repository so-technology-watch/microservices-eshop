package fr.sogeti.services

import fr.sogeti.dao.common.{GenericDAO, ManagerFactory}
import fr.sogeti.entities.Product
import fr.sogeti.dao.ProductDao

/**
 * Service : products
 */
class ProductService extends IEntityService[Product] {
  
  private val dao : ProductDao = new ProductDao;
  
  override def getAll(begin : Int, end : Int) : List[Product] = {
    return dao.getAll(begin, end)
  }
  
  override def find(id : Int) : Product = {
    return dao.find(id)
  }
  
  def findByCriterias(criterias : String) : Array[Product] = {
    return dao.findByCriterias(criterias)
  }
  
  override def create(product : Product) : Unit = {
    dao.create(product)
  }
  
  override def update(product : Product) : Unit = {
    dao.update(product)
  }
  
  override def deleteById(id : Integer) : Unit = {
    dao.deleteById(id)
  }
  
}