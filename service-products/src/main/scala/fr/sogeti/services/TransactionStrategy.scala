package fr.sogeti.services

import javax.persistence.EntityManager

/**
 * Construct a transaction strategy
 * @param manager the entity manager to make the transactions 
 * @param strategy the strategy to execute
 */
class TransactionStrategy(manager : EntityManager, strategy : ITransactionStrategy) {
  
  /**
   * executes the given strategy
   */
  def execute() : Unit = {
    val transaction = manager.getTransaction
    try{
      transaction.begin
      
      strategy.execute()
      
      transaction.commit
    }catch{
      case unknow : Throwable => transaction.rollback
    }
  }
}