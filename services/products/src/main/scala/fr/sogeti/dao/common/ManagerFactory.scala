package fr.sogeti.dao.common

import javax.persistence.{EntityManagerFactory, EntityManager, Persistence}
import java.util.{Map, HashMap}
import fr.sogeti.consul.{ConfigDatabase, ConsulSingleton}

object ManagerFactory {
  val configDatabase : ConfigDatabase = ConsulSingleton.getConfigResolverInstance.getConfig.getConfigDatabase
  private val informations : Map[String, String] = new HashMap
  informations.put("javax.persistence.jdbc.url", configDatabase.getUrl)
  informations.put("javax.persistence.jdbc.user", configDatabase.getUser)
  informations.put("javax.persistence.jdbc.password", configDatabase.getPassword)
  informations.put("javax.persistence.jdbc.driver", configDatabase.getDriver)
  informations.put("hibernate.show_sql", configDatabase.getShowSql.toString)
  
  private val INSTANCE : EntityManagerFactory = Persistence.createEntityManagerFactory("persistence", informations)
  
  /**
   * create an entity manager
   * @return an EntityManager 
   */
  def createEntityManager : EntityManager = {
    INSTANCE.createEntityManager
  }
  
  /**
   * @return the object's instance
   */
  def getInstance : EntityManagerFactory = {
    ManagerFactory.INSTANCE
  }
}