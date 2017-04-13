package fr.sogeti.services

import fr.sogeti.entities.Category
import fr.sogeti.dao.common.GenericDAO
import fr.sogeti.dao.common.ManagerFactory

/**
 * Category 
 */
class CategoryService extends IEntityService[Category] {
  private val dao : GenericDAO[Category, Integer] = new GenericDAO[Category, Integer]( classOf[Category], ManagerFactory.createEntityManager );
  
  override def getAll(begin : Int, end : Int) : List[Category] = {
    return dao.getAll(begin, end);
  }
  
  override def find(id : Int) : Category = {
    return dao.find(id)
  }
  
  override def create(category : Category) : Unit = {
    dao.create(category)
  }
  
  override def update(category : Category) : Unit = {
    dao.update(category)
  }
  
  override def deleteById(id : Integer) : Unit = {
    dao.deleteById(id)
  }
}