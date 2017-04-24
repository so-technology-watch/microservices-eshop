package fr.sogeti.consul

import scala.beans.BeanProperty
import scala.annotation.meta.field

class Config{
  /**
   * the network interface to look for the deployment
   */
   @BeanProperty
   var interface : String = _
  
  /**
   * the port on which we want to deploy our server
   */
  @BeanProperty
  var port : Int = _
  
  /**
   * the configuration for rabbitMQ
   */
  @BeanProperty
  var configRabbitMq : ConfigRabbitMq = _
  
  /**
   * the configuration for the database
   */
  @BeanProperty
  var configDatabase : ConfigDatabase = _
  
  override def toString : String = "port : %d, configRabbitMQ : [ %s ], configDatabase : [ %s ]".format(port, configRabbitMq, configDatabase)
}
