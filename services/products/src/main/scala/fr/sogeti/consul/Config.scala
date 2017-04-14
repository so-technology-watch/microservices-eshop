package fr.sogeti.consul

import scala.beans.BeanProperty

class Config{
  /**
   * the address where we want to deploy our server
   */
  @BeanProperty
  var address : String = _
  /**
   * the port on which we want to deploy our server
   */
  @BeanProperty
  var port : Int = _
  
  /**
   * the config for rabbitMQ
   */
  @BeanProperty
  var configRabbitMq : ConfigRabbitMq = _
  
  override def toString : String = "address : %s, port : %d, configRabbitMQ : [ %s ]".format(address, port, configRabbitMq)
}
