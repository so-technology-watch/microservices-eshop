package fr.sogeti.amqp

import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Envelope
import com.rabbitmq.client.AMQP.BasicProperties

class MessageCallback( callbackReceived : (String => Unit), channel : Channel ) extends DefaultConsumer(channel) {
  
  override def handleDelivery(consumerTag : String, envelope : Envelope, properties : BasicProperties, playload : Array[Byte] ) : Unit = {
    val msg : String = new String(playload)
    callbackReceived.apply(msg)
  }
  
}