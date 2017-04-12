package fr.sogeti.services

import fr.sogeti.entities.Category
import fr.sogeti.dao.common.GenericDAO
import fr.sogeti.dao.common.ManagerFactory

/**
 * Category 
 */
class CategoryService extends IEntityService[Category] {
  private val dao : GenericDAO[Category, Integer] = new GenericDAO[Category, Integer]( classOf[Category], new ManagerFactory().createEntityManager );
  
  override def getAll(begin : Int, end : Int) : List[Category] = {
    return dao.getAll(begin, end);
  }
  
  override def find(id : Int) : Category = {
    return dao.find(id)
  }
  
  override def create(product : Category) : Unit = {
    dao.create(product)
  }
  
  override def update(product : Category) : Unit = {
    dao.update(product)
  }
  
  override def deleteById(id : Integer) : Unit = {
    dao.deleteById(id)
  }
}