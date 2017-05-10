package fr.sogeti.services

trait IEntityService[Type] {
  
  /**
   * @param begin the begining index
   * @param end the ending index
   * @return a list of products delimited by the given range
   */
  def getAll(begin : Int, end : Int) : List[Type]
  
  /**
   * @param id the id of the entity to find
   * @return the entity found
   */
  def find(id : Int) : Type
  
  /**
   * @param the entity to create
   */
  def create(entity : Type) : Unit
  
  /**
   * @param entity the entity to update
   */
  def update(entity : Type) : Unit
  
  /**
   * @param id the id of the entity to delete 
   */
  def deleteById(id : Integer) : Unit
  
  /**
   * @return the count the entities available in the database
   */
  def getCount() : Int
  
}