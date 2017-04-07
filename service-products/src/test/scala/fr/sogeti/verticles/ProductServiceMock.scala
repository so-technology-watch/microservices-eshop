package fr.sogeti.verticles

import fr.sogeti.services.ProductService
import org.mockito.Mockito
import org.mockito.Matchers
import fr.sogeti.entities.Product
import org.mockito.stubbing.Answer
import org.mockito.invocation.InvocationOnMock

class ProductServiceMock {
  var productService : ProductService = _
  
  initProductServiceMock
  
  private def initProductServiceMock() : Unit = {
    productService = Mockito.mock(classOf[ProductService])
    
    mockGet
    mockGetAll
    mockCreate
    mockUpdate
    mockDelete
  }
  
  private def mockGet() : Unit = {
    val product : Product = ProductServiceMock.getProduct
    
    Mockito.when(productService.find(4)).thenReturn(product)
  }
  
  private def mockGetAll() : Unit = {
    val products : List[Product] = ProductServiceMock.getProducts 
    
    Mockito.when(productService.getAll()).thenReturn(products)
  }
  
  private def mockCreate : Unit = {
    Mockito.doNothing().when(productService).create(Matchers.any())
  }
  
  private def mockUpdate : Unit = {
    Mockito.doNothing().when(productService).update(Matchers.any())
  }
  
  private def mockDelete : Unit = {
    Mockito.doNothing().when(productService).deleteById(Matchers.any())
  }
  
  
  
  def get() : ProductService = productService
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