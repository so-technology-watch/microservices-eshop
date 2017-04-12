package fr.sogeti.verticles

import fr.sogeti.services.ProductService
import org.mockito.Mockito
import org.mockito.Matchers
import fr.sogeti.entities.Product
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock
import fr.sogeti.services.IEntityService

class ProductServiceMock extends GenericServiceMock[Product, ProductService](classOf[ProductService]) {
  
  override def mockGet() : Unit = {
    val product : Product = ProductServiceMock.getProduct
    
    Mockito.when(service.find(4)).thenReturn(product)
  }
  
  override def mockGetAll() : Unit = {
    val products : List[Product] = ProductServiceMock.getProducts 
    
    Mockito.when(service.getAll(Matchers.any(), Matchers.any())).thenReturn(products)
  }
  
}

object ProductServiceMock {
  def getProduct : Product = {
    val product = new Product("reference", "montre", "une montre", 10.5, "image.png")
    product.setId(4)
    return product
  }  
  
  def getProducts : List[Product] = {
    List(
      new Product("reference","montre","une montre", 10.5, "image.png"),
      new Product("reference2","montre2","une montre2", 10.6, "image2.png")
    )
  }
}