package fr.sogeti.services

import fr.sogeti.entities.Supplier
import fr.sogeti.dao.common.{GenericDAO, ManagerFactory}

class SupplierService extends IEntityService[Supplier] {
  private val dao : GenericDAO[Supplier, Integer] = new GenericDAO[Supplier, Integer]( classOf[Supplier], ManagerFactory.createEntityManager );
  
  override def getAll(begin : Int, end : Int) : List[Supplier] = {
    return dao.getAll(begin, end);
  }
  
  override def find(id : Int) : Supplier = {
    return dao.find(id)
  }
  
  override def create(supplier : Supplier) : Unit = {
    dao.create(supplier)
  }
  
  override def update(supplier : Supplier) : Unit = {
    dao.update(supplier)
  }
  
  override def deleteById(id : Integer) : Unit = {
    dao.deleteById(id)
  }
  
  override def getCount = dao.getCount
}