package fr.sogeti.consul

import scala.beans.BeanProperty

class Config{
  @BeanProperty
  var address : String = _
  @BeanProperty
  var port : Int = _
  
  @BeanProperty
  var configRabbitMq : ConfigRabbitMq = _
  
  override def toString : String = "address : %s, port : %d, configRabbitMQ : [ %s ]".format(address, port, configRabbitMq)
}
