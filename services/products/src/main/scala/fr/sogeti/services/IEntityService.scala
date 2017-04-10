package fr.sogeti.services

trait IEntityService[Type] {
  
  def getAll() : List[Type]
  
  def find(id : Int) : Type
  
  def create(product : Type)
  
  def update(product : Type)
  
  def deleteById(id : Integer)
  
}