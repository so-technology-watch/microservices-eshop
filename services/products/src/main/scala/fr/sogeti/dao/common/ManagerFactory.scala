package fr.sogeti.dao.common

import javax.persistence.{EntityManagerFactory, EntityManager, Persistence}

class ManagerFactory {
  
  
  def createEntityManager : EntityManager = {
    return ManagerFactory.INSTANCE.createEntityManager
  }
  
  def getInstance : EntityManagerFactory = {
    return ManagerFactory.INSTANCE
  }
  
}

object ManagerFactory {
  val INSTANCE : EntityManagerFactory = Persistence.createEntityManagerFactory("persistence")
}