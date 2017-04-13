package fr.sogeti.dao.common

import javax.persistence.{EntityManagerFactory, EntityManager, Persistence}

object ManagerFactory {
  val INSTANCE : EntityManagerFactory = Persistence.createEntityManagerFactory("persistence")
  
  def createEntityManager : EntityManager = {
    INSTANCE.createEntityManager
  }
  
  def getInstance : EntityManagerFactory = {
    ManagerFactory.INSTANCE
  }
}