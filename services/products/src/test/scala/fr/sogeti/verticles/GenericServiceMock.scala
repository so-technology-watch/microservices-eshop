package fr.sogeti.verticles

import fr.sogeti.services.IEntityService
import org.mockito.Mockito
import org.mockito.Matchers

abstract class GenericServiceMock[Entity, Type <: IEntityService[Entity]](clazz : Class[Type]) extends IGenericServiceMock {
  var service : IEntityService[Entity] = _
  
  initServiceMock
  
  private def initServiceMock() : Unit = {
    service = Mockito.mock(clazz)
    
    mockGet
    mockGetAll
    mockCreate
    mockUpdate
    mockDelete
  }
  
  override def mockCreate : Unit = {
    Mockito.doNothing().when(service).create(Matchers.any())
  }
  
  override def mockUpdate : Unit = {
    Mockito.doNothing().when(service).update(Matchers.any())
  }
  
  override def mockDelete : Unit = {
    Mockito.doNothing().when(service).deleteById(Matchers.any())
  }
  
  def get() : IEntityService[Entity] = service
}