package fr.sogeti.verticles

import io.vertx.scala.core.http.HttpClient
import io.vertx.scala.core.http.HttpClientRequest
import io.vertx.ext.unit.TestContext
import io.vertx.core.Handler
import io.vertx.scala.core.Vertx
import io.vertx.scala.core.http.HttpClientResponse
import fr.sogeti.verticles.common.RequestsHelper
import io.vertx.ext.unit.Async
import fr.sogeti.services.CategoryServiceMock
import fr.sogeti.rest.common.JsonHelperTesting
import fr.sogeti.entities.Category
import io.vertx.ext.unit.TestCompletion
import io.vertx.ext.unit.TestSuite
import io.vertx.scala.ext.web.Router
import io.vertx.core.AsyncResult
import io.vertx.lang.scala.ScalaVerticle

class CategoryTest {
  def testGetCategory() : Unit = {
    val vertx = Vertx.vertx
    val router : Router = Router.router(vertx)
    
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubCategoryVerticle] )
    val suiteName = "category test suite"
    val suite : TestSuite = TestSuite.create(suiteName)
    suite.test("get_category_test", getCategoryTest(vertx))
    suite.test("get_all_category_test", getAllCategoriesTest(vertx))
    
    val completion : TestCompletion = suite.run
    completion.await
    
    completion.handler( new Handler[AsyncResult[Void]] {
      override def handle(context : AsyncResult[Void]) : Unit = {
        if(context.succeeded()){
          println("The test suite is successfull : %s".format(suiteName))
        }else{
          println("The test suite failed : %s".format(suiteName))
          context.cause.printStackTrace
        }
        vertx.close
      }
    })
  }

  def getCategoryTest(vertx : Vertx) : Handler[TestContext] = {
    println("get category test running")
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/categories/4");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            RequestsHelper.onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val category : Category = CategoryServiceMock.getCategory
              val gsonHelper = new JsonHelperTesting
              val optObtained = gsonHelper.fromJson(resp, classOf[Category], true)
              if(!optObtained.isDefined){
                context.fail("received invalid response %s".format(resp))
                async.complete
                return
              }
              val obtained : Category = optObtained.get 
              context.assertEquals(200, response.statusCode)
              context.assertEquals( obtained.getDescription, category.getDescription )
              context.assertEquals( obtained.getId, category.getId )
              context.assertEquals( obtained.getName, category.getName )
              context.assertEquals(category.getProducts.size, obtained.getProducts.size)
              for( i <- 0 to category.getProducts.size-1 ){
                val products = category.getProducts().get(i)
                val obt = obtained.getProducts().get(i)
                context.assertEquals( products.getCategory, obt.getCategory )
                context.assertEquals( products.getDescription, obt.getDescription )
                context.assertEquals( products.getDesignation, obt.getDesignation )
                context.assertEquals( products.getId, obt.getId )
                context.assertEquals( products.getPrice, obt.getPrice )
                context.assertEquals( products.getReference, obt.getReference )
              }
              println("get category test completed")
              async.complete
            })
          }
        })
        .exceptionHandler( RequestsHelper.onError(context, async) )
        .end
      }
    }
  }
  
  def getAllCategoriesTest(vertx : Vertx) : Handler[TestContext] = {
    println("get all category test running")
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/categories");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            RequestsHelper.onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val categories : List[Category] = CategoryServiceMock.getCategories
              val obtained : Option[List[Category]] = new JsonHelperTesting().listFromJsonCategory(resp, true)
              
              if(!obtained.isDefined){
                context.fail("not category obtained")
                async.complete
                return
              }
              context.assertEquals( 200, response.statusCode )
              for( i : Int <- 0 to categories.size-1 ) {
                val got = obtained.get(i)
                val category = categories(i)
                
                context.assertEquals(200, response.statusCode)
                context.assertEquals( got.getDescription, category.getDescription )
                context.assertEquals( got.getId, category.getId )
                context.assertEquals( got.getName, category.getName )
                context.assertEquals(category.getProducts.size, got.getProducts.size)
                for( i <- 0 to category.getProducts.size ){
                  val product = category.getProducts().get(i)
                  val obt = got.getProducts().get(i)
                  context.assertEquals( product.getCategory, obt.getCategory )
                  context.assertEquals( product.getDescription, obt.getDescription )
                  context.assertEquals( product.getDesignation, obt.getDesignation )
                  context.assertEquals( product.getId, obt.getId )
                  context.assertEquals( product.getPrice, obt.getPrice )
                  context.assertEquals( product.getReference, obt.getReference )
                }
              }
              println("get all category test completed")
              async.complete
            })
          }
        })
        .exceptionHandler( RequestsHelper.onError(context, async) )
        .end
      }
    }
  }
}