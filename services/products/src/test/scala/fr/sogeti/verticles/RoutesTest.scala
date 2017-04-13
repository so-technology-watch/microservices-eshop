package fr.sogeti.verticles;

import io.vertx.scala.ext.web.Router
import io.vertx.scala.core.Vertx
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.ext.unit.{TestSuite, TestCompletion}
import io.vertx.core.{AsyncResult, Handler}
import io.vertx.ext.unit.{TestContext, Async}
import io.vertx.scala.core.http.{HttpClient, HttpClientRequest, HttpClientResponse}
import io.vertx.core.buffer.Buffer
import fr.sogeti.rest.common.RequestHelper
import fr.sogeti.verticles.common.RequestsHelper
import io.vertx.core.http.HttpMethod

class RoutesTest {
  val routes : List[String] = List(
    "/api/v1/suppliers",
    "/api/v1/products",
    "/api/v1/categories"
  )
  
	def runTests() : Unit = {
	  val vertx = Vertx.vertx
    val router : Router = Router.router(vertx)
    
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubSupplierVerticle] )
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubProductVerticle] )
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubCategoryVerticle] )
    
    val suiteName = "routes test suite"
    val suite : TestSuite = TestSuite.create(suiteName)
    suite.test("POST deployde test", testCreate(vertx))
    suite.test("PUT deployed test", testPut(vertx))
    suite.test("DELETE deployed test", testDelete(vertx))
    
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
	
	def testCreate(vertx : Vertx) : Handler[TestContext] = {
    createTest(vertx, HttpMethod.POST)
	}
	
	def testPut(vertx : Vertx) : Handler[TestContext] = {
    createTest(vertx, HttpMethod.PUT)
	}
	
	def testDelete(vertx : Vertx) : Handler[TestContext] = {
	   createTest(vertx, HttpMethod.DELETE) 
	}
	
	
	private def createTest(vertx : Vertx, method : HttpMethod) : Handler[TestContext] = {
	  return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        
        for( url <- routes ){
          val req : HttpClientRequest = client.request(method, 8080, "localhost", if(method != HttpMethod.DELETE) url else url + "/4");
          println("test %s on %s".format(method, url))
          val async : Async = context.async
          
          req.handler(new Handler[HttpClientResponse]{
            override def handle(response : HttpClientResponse) : Unit = {
              RequestsHelper.onResponse(response, (resp) => {
                context.assertFalse(resp.isEmpty)
                
                context.assertEquals(200, response.statusCode)
                context.assertEquals("OK", resp)
                context.assertTrue(response.getHeader("content-type").isDefined)
                context.assertEquals("application/json", response.getHeader("content-type").get)
                
                println("%S test completed on %s".format(method, url))
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
	
}
