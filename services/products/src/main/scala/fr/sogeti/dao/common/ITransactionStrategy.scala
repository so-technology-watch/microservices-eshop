package fr.sogeti.dao.common

/**
 * This class allows use to design a strategy pattern 
 */
trait ITransactionStrategy {
  /**
   * executes a code in a strategy context 
   */
  def execute() : Unit
}