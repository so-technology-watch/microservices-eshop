package fr.sogeti.verticles.common

import io.vertx.scala.core.http.HttpClientResponse
import io.vertx.core.Handler
import io.vertx.core.buffer.Buffer
import io.vertx.ext.unit.TestContext
import io.vertx.ext.unit.Async

object RequestsHelper {
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