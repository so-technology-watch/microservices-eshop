package fr.sogeti.consul

import scala.beans.BeanProperty

class ConfigRabbitMq {
  @BeanProperty
  var host : String = _
  @BeanProperty
  var user : String = _
  @BeanProperty
  var password : String = _

  @BeanProperty
  var exchange : String = _
  @BeanProperty
  var exchangeType : String = _
  @BeanProperty
  var routingKey : String = _
  @BeanProperty
  var virtualHost : String = _
  
  override def toString = "host : %s, user : %s, password : %s, exchange : %s, exchangeType : %s, routingKey : %s, virtualHost : %s".format(host, user, password, exchange, exchangeType, routingKey, virtualHost)
}
