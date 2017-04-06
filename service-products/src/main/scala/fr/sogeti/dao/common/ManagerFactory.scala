package fr.sogeti.dao.common

import javax.persistence.{EntityManagerFactory, EntityManager, Persistence}

class ManagerFactory {
  private val instance : EntityManagerFactory = Persistence.createEntityManagerFactory("persistence")
  
  def createEntityManager : EntityManager = {
    return instance.createEntityManager
  }
  
  def getInstance : EntityManagerFactory = {
    return instance
  }
}