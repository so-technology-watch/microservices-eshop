package fr.sogeti.verticles

object MainTestVertx extends App{
  val productTest = new ProductTest
  val supplierTest = new SupplierTest
  val categoryTest = new CategoryTest
  val routesTest = new RoutesTest
  
  productTest.testGetProduct
  supplierTest.testGetSupplier
  categoryTest.testGetCategory
  routesTest.runTests
}