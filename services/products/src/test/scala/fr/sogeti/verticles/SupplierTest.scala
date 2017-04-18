package fr.sogeti.verticles

import io.vertx.core.{Handler, AsyncResult}
import io.vertx.core.buffer.Buffer
import io.vertx.ext.unit.{TestContext, Async, TestSuite, TestCompletion}
import io.vertx.lang.scala.ScalaVerticle
import io.vertx.scala.core.http.{HttpClientRequest, HttpClientResponse, HttpClient, HttpServerRequest}
import io.vertx.scala.core.Vertx
import io.vertx.scala.ext.web.Router
import fr.sogeti.entities.Supplier
import fr.sogeti.services.SupplierServiceMock
import fr.sogeti.rest.common.JsonHelperTesting
import fr.sogeti.verticles.common.RequestsHelper

class SupplierTest {
  
  def testGetSupplier() : Unit = {
    val vertx = Vertx.vertx
    val router : Router = Router.router(vertx)
    
    vertx.deployVerticle( ScalaVerticle.nameForVerticle[StubSupplierVerticle] )
    val suiteName = "supplier test suite"
    val suite : TestSuite = TestSuite.create(suiteName)
    suite.test("get_supplier_test", getSupplierTest(vertx))
    suite.test("get_all_suppliers_test", getAllSupplierTest(vertx))
    
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

  def getSupplierTest(vertx : Vertx) : Handler[TestContext] = {
    println("get supplier test running")
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/suppliers/4");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            RequestsHelper.onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val supplier : Supplier = SupplierServiceMock.getSupplier
              val gsonHelper = new JsonHelperTesting
              val optObtained = gsonHelper.fromJson(resp, classOf[Supplier], true)
              if(!optObtained.isDefined){
                context.fail("received invalid response %s".format(resp))
                async.complete
                return
              }
              val obtained : Supplier = optObtained.get
              context.assertEquals(200, response.statusCode)
              context.assertEquals( obtained.getCompanyName, supplier.getCompanyName )
              context.assertEquals( obtained.getEmail, supplier.getEmail )
              context.assertEquals( obtained.getPhoneNumber , supplier.getPhoneNumber )
              context.assertEquals( obtained.getId , supplier.getId )
              println("get supplier test completed")
              async.complete
            })
          }
        })
        .exceptionHandler( RequestsHelper.onError(context, async) )
        .end
      }
    }
  }
  
  def getAllSupplierTest(vertx : Vertx) : Handler[TestContext] = {
    println("get all supplier test running")
    return new Handler[TestContext] {
      
      override def handle(context : TestContext) : Unit = {
        val client : HttpClient = vertx.createHttpClient
        val req : HttpClientRequest = client.get(8080, "localhost", "/api/v1/suppliers");
        val async : Async = context.async
        
        req.handler(new Handler[HttpClientResponse]{
          override def handle(response : HttpClientResponse) : Unit = {
            RequestsHelper.onResponse(response, (resp) => {
              context.assertFalse(resp.isEmpty)
            
              val suppliers : List[Supplier] = SupplierServiceMock.getSuppliers
              val obtained : Option[List[Supplier]] = new JsonHelperTesting().listFromJsonSupplier(resp, true)
              
              if(!obtained.isDefined){
                context.fail("not product obtained")
                async.complete
                return
              }
              
              context.assertEquals( 200, response.statusCode )
              for( i : Int <- 0 to suppliers.size-1 ) {
                val got = obtained.get(i)
                val supplier = suppliers(i)
                
                context.assertEquals(200, response.statusCode)
                context.assertEquals( got.getCompanyName, supplier.getCompanyName )
                context.assertEquals( got.getEmail, supplier.getEmail )
                context.assertEquals( got.getPhoneNumber , supplier.getPhoneNumber )
                context.assertEquals( got.getId , supplier.getId )
              }
              println("get all supplier test completed")
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