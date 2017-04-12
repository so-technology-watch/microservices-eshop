package fr.sogeti.verticles

import org.junit.Test
import io.vertx.core.{Handler, AsyncResult}
import io.vertx.core.buffer.Buffer
import io.vertx.ext.unit.{TestContext, Async, TestSuite, TestCompletion}
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.http.{HttpClientRequest, HttpClientResponse}
import io.vertx.scala.core.Vertx
import io.vertx.scala.core.http.{HttpClient, HttpServerRequest}
import io.vertx.scala.ext.web.Router
import fr.sogeti.entities.Product
import fr.sogeti.rest.common.JsonHelper

class ProductTest {
  
     
  
  @Test
  def testGetProduct() : Unit = {
    val vertx = Vertx.vertx
    val router : Router = Router.router(vertx)
    
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubProductVerticle] )
    
    val suite : TestSuite = TestSuite.create("test_get_product")
    suite.test("get_product_test", getProductTest(vertx))
    suite.test("get_all_product_test", getAllProductTest(vertx))
    
    val completion : TestCompletion = suite.run
    completion.await
    
    completion.handler( new Handler[AsyncResult[Void]] {
      override def handle(context : AsyncResult[Void]) : Unit = {
        if(context.succeeded()){
          println("The test suite is successfull")
          vertx.close
        }else{
          println("The test suite failed")
          context.cause.printStackTrace
        }
      }
    })
  }

  def getProductTest(vertx : Vertx) : Handler[TestContext] = {
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/products/4");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val product : Product = ProductServiceMock.getProduct
              val gsonHelper = new JsonHelper
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
              
              async.complete
            })
          }
        })
        .exceptionHandler( onError(context, async) )
        .end
      }
    }
  }
  
  def getAllProductTest(vertx : Vertx) : Handler[TestContext] = {
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/products");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val products : List[Product] = ProductServiceMock.getProducts
              val obtained : Option[List[Product]] = new JsonHelper().listFromJson(resp, true)
              
              if(!obtained.isDefined){
                context.fail("not product obtained")
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
              async.complete
            })
          }
        })
        .exceptionHandler( onError(context, async) )
        .end
      }
    }
  }
  
  def onResponse(response : HttpClientResponse, testOnResponse : (String) => Unit) : Unit = {
    response.bodyHandler(new Handler[Buffer] {
      override def handle(buff : Buffer) : Unit = {
          val result = new String(buff.getBytes)
          testOnResponse(result)
      }
    })
  }
  
  def onError(context : TestContext, async : Async) : Handler[Throwable] = {
    new Handler[Throwable] {
      override def handle(error : Throwable) : Unit = {
        context.fail("get product failed")
        async.complete
      }
    }
  }
}