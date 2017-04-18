package fr.sogeti.services

import fr.sogeti.entities.Category
import org.mockito.{Mockito, Matchers}
import fr.sogeti.entities.Product
import scala.collection.JavaConverters._

class CategoryServiceMock extends GenericServiceMock[Category, CategoryService](classOf[CategoryService]) {
  
  override def mockGet() : Unit = {
    val category : Category = CategoryServiceMock.getCategory
    
    Mockito.when(service.find(4)).thenReturn(category)
  }
  
  override def mockGetAll() : Unit = {
    val categories : List[Category] = CategoryServiceMock.getCategories
    
    Mockito.when(service.getAll(Matchers.any(), Matchers.any())).thenReturn(categories)
  }
  
}

object CategoryServiceMock {
  def getCategory : Category = {
    val category = new Category("potatoes","here are the potatoes")
    category.setId(4)
    return category
  }  
  
  def getCategories : List[Category] = {
    val cat = new Category("potatoes","here are the potatoes")
    cat.setProducts(List(
      new Product("reference","montre","une montre", 10.5, "image.png"),
      new Product("reference2","montre2","une montre2", 10.6, "image2.png")    
    ).asJava)
    List(
      cat,
      new Category("potatoes2","here are the potatoes2")
    )
  }
}