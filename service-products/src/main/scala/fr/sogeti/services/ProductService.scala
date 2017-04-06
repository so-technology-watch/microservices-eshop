package fr.sogeti.services

import fr.sogeti.dao.ProductDao
import fr.sogeti.dao.common.{GenericDAO, ManagerFactory}
import fr.sogeti.entities.Product

/**
 * Service : products
 */
class ProductService {
  
  private val dao : GenericDAO[Product, Integer] = new GenericDAO[Product, Integer]( classOf[Product], new ManagerFactory().createEntityManager );
  
  def getAll() : List[Product] = {
    return dao.getAll();
  }
  
  def find(id : Int) : Product = {
    return dao.find(id)
  }
  
  def create(product : Product) : Unit = {
    dao.create(product)
  }
  
  def update(product : Product) : Unit = {
    dao.update(product)
  }
  
  def deleteById(id : Integer) : Unit = {
    dao.deleteById(id)
  }
  
}