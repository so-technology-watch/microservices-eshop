package fr.sogeti.rest.common

import io.vertx.core.Handler
import io.vertx.scala.ext.web.RoutingContext
import com.google.gson.Gson

abstract class BaseHandler extends Handler[RoutingContext] {
  
  protected val requestHelper : RequestHelper = new RequestHelper()
  protected val jsonHelper: JsonHelper = new JsonHelper()
  
}