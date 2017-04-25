package fr.sogeti.consul

import scala.beans.BeanProperty

class ConfigDatabase {
  
  /**
   * the url to access the database
   */
  @BeanProperty
  var url : String = _
  
  /**
   * the user for the database
   */
  @BeanProperty
  var user : String = _
  
  /**
   * the password for the database
   */
  @BeanProperty
  var password : String = _
  
  /**
   * the driver for the database
   */
  @BeanProperty
  var driver : String = _
  
  /**
   * set true if we want to the the sql requests
   */
  @BeanProperty
  var showSql : Boolean = _
  
  override def toString : String = "url : %s, user : %s, password : %s, driver : %s, showSql : %s".format(url, user, password, driver, showSql)
}