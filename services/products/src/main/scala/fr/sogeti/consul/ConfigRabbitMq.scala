package fr.sogeti.consul

import scala.beans.BeanProperty

class ConfigRabbitMq {
  /**
   * the host for rabbitMQ
   */
  @BeanProperty
  var host : String = _
  /**
   * the user for rabbitMQ
   */
  @BeanProperty
  var user : String = _
  /**
   * the password for rabbitMQ
   */
  @BeanProperty
  var password : String = _
  
  /**
   * the exchange
   */
  @BeanProperty
  var exchange : String = _
  /**
   * the exchange type
   */
  @BeanProperty
  var exchangeType : String = _
  /**
   * the routing key
   */
  @BeanProperty
  var routingKey : String = _
  /**
   * the virtualhost
   */
  @BeanProperty
  var virtualHost : String = _
  
  override def toString = "host : %s, user : %s, password : %s, exchange : %s, exchangeType : %s, routingKey : %s, virtualHost : %s".format(host, user, password, exchange, exchangeType, routingKey, virtualHost)
}
