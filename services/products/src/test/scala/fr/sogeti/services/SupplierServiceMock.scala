package fr.sogeti.services;

import org.mockito.Mockito
import fr.sogeti.entities.Supplier
import org.mockito.Matchers

class SupplierServiceMock extends GenericServiceMock[Supplier, SupplierService](classOf[SupplierService]) {
  
  override def mockGet() : Unit = {
    val supplier : Supplier = SupplierServiceMock.getSupplier
    
    Mockito.when(service.find(4)).thenReturn(supplier)
  }
  
  override def mockGetAll() : Unit = {
    val supplier : List[Supplier] = SupplierServiceMock.getSuppliers
    
    Mockito.when(service.getAll(Matchers.any(), Matchers.any())).thenReturn(supplier)
  }
  
}

object SupplierServiceMock {
  def getSupplier : Supplier = {
    val supplier = new Supplier("company","mail@mail.fr","0645650345")
    supplier.setId(4)
    return supplier
  }  
  
  def getSuppliers : List[Supplier] = {
    List(
      new Supplier("company2","mail2@mail.fr","0645650342"),
      new Supplier("company3","mail3@mail.fr","0645650343")
    )
  }
}