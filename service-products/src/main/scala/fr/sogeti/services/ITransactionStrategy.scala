package fr.sogeti.services

/**
 * This class allows use to design a strategy pattern 
 */
trait ITransactionStrategy {
  /**
   * executes a code in a strategy context 
   */
  def execute() : Unit
}