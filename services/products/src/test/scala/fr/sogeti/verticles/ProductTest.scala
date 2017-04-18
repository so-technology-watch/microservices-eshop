package fr.sogeti.verticles

import io.vertx.core.{Handler, AsyncResult}
import io.vertx.core.buffer.Buffer
import io.vertx.ext.unit.{TestContext, Async, TestSuite, TestCompletion}
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.Vertx
import io.vertx.scala.core.http.{HttpClient, HttpServerRequest, HttpClientRequest, HttpClientResponse}
import io.vertx.scala.ext.web.Router
import fr.sogeti.entities.Product
import fr.sogeti.rest.common.JsonHelperTesting
import fr.sogeti.services.ProductServiceMock
import fr.sogeti.rest.common.JsonHelperTesting
import fr.sogeti.verticles.common.RequestsHelper

class ProductTest {
  
  def testGetProduct() : Unit = {
    val vertx = Vertx.vertx
    val router : Router = Router.router(vertx)
    
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubProductVerticle] )
    val suiteName = "product test suite"
    val suite : TestSuite = TestSuite.create(suiteName)
    suite.test("get_product_test", getProductTest(vertx))
    suite.test("get_all_product_test", getAllProductTest(vertx))
    
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

  def getProductTest(vertx : Vertx) : Handler[TestContext] = {
    println("get product test running")
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/products/4");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            RequestsHelper.onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val product : Product = ProductServiceMock.getProduct
              val gsonHelper = new JsonHelperTesting
              val optObtained = gsonHelper.fromJson(resp, classOf[Product], true)
              if(!optObtained.isDefined){
                context.fail("received invalid response %s".format(resp))
                async.complete
                return
              }
              val obtained : Product = optObtained.get 
              context.assertEquals(200, response.statusCode)
              context.assertEquals( obtained.getCategory, product.getCategory )
              context.assertEquals( obtained.getDescription, product.getDescription )
              context.assertEquals( obtained.getDesignation , product.getDesignation )
              context.assertEquals( obtained.getId , product.getId )
              context.assertTrue( obtained.getPrice == product.getPrice )
              println("get product test completed")
              async.complete
            })
          }
        })
        .exceptionHandler( RequestsHelper.onError(context, async) )
        .end
      }
    }
  }
  
  def getAllProductTest(vertx : Vertx) : Handler[TestContext] = {
    println("get all product test running")
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/products");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            RequestsHelper.onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val products : List[Product] = ProductServiceMock.getProducts
              val obtained : Option[List[Product]] = new JsonHelperTesting().listFromJsonProduct(resp, true)
              
              if(!obtained.isDefined){
                context.fail("not product obtained")
                async.complete
                return
              }
              context.assertEquals( 200, response.statusCode )
              for( i : Int <- 0 to products.size-1 ) {
                val got = obtained.get(i)
                val product = products(i)
                
                context.assertEquals( got.getCategory, product.getCategory )
                context.assertEquals( got.getDescription, product.getDescription )
                context.assertEquals( got.getDesignation , product.getDesignation )
                context.assertEquals( got.getId , product.getId )
                context.assertTrue( got.getPrice == product.getPrice )
              }
              println("get all product test completed")
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