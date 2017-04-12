package fr.sogeti.services

import fr.sogeti.dao.ProductDao
import fr.sogeti.dao.common.{GenericDAO, ManagerFactory}
import fr.sogeti.entities.Product

/**
 * Service : products
 */
class ProductService extends IEntityService[Product] {
  
  private val dao : GenericDAO[Product, Integer] = new GenericDAO[Product, Integer]( classOf[Product], new ManagerFactory().createEntityManager );
  
  override def getAll(begin : Int, end : Int) : List[Product] = {
    return dao.getAll(begin, end);
  }
  
  override def find(id : Int) : Product = {
    return dao.find(id)
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