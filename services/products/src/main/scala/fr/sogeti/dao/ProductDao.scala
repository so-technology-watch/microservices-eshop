package fr.sogeti.dao

import fr.sogeti.dao.common.{GenericDAO, ManagerFactory}

class ProductDao extends GenericDAO[Product, Integer]( classOf[Product], new ManagerFactory().createEntityManager ) {
}