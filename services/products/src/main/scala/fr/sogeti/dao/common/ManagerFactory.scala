package fr.sogeti.dao.common

import javax.persistence.{EntityManagerFactory, EntityManager, Persistence}

object ManagerFactory {
  private val INSTANCE : EntityManagerFactory = Persistence.createEntityManagerFactory("persistence")
  
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