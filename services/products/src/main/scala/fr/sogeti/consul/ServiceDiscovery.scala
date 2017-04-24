package fr.sogeti.consul

import com.ecwid.consul.v1.ConsulClient
import java.util.Arrays
import com.ecwid.consul.v1.agent.model.NewService.Check
import com.ecwid.consul.v1.agent.model.NewService
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.Enumeration
import java.net.Inet4Address

class ServiceDiscovery(client : ConsulClient) {
  
  val id = "products-service"
  val name = "products-service"
  var newService : NewService = _
  
  /**
   * register the service to the consul server
   */
  def register(address : String, port : Int) : Unit = {
    newService = new NewService()
    newService.setId(id)
    newService.setName(name)
    
    newService.setAddress(address)
    newService.setTags(Arrays.asList("service","products"))
    newService.setPort(port)
    client.agentServiceRegister(newService)
  }
  
  /**
   * set a check on consul
   */
  def setCheck(newService : NewService, check : Check) : Unit = {
    newService.setCheck(check)
  }
    
  /**
   * unregister the service from the consul server
   */
  def unregister() : Unit = {
    client.agentServiceDeregister(id)
  }
  
  /**
   * @return the service's id 
   */
  def getId() : String = id
}
object ServiceDiscovery {
  /**
   * Retrieve the ip for a given interface
   * @param interface the interface to look
   * @return the ipv4 ip for this interface, or localhost if not found
   */
  def getLocalAddress(interface : String) : String = {
    val nets : Enumeration[NetworkInterface] = NetworkInterface.getNetworkInterfaces
    while( nets.hasMoreElements ) {
      val interf : NetworkInterface = nets.nextElement
      if(interf.getName.equalsIgnoreCase(interface)){
        val addresses : Enumeration[InetAddress] = interf.getInetAddresses
        while( addresses.hasMoreElements ) {
          val next : InetAddress = addresses.nextElement
          if(next.isInstanceOf[Inet4Address]){
            return next.getHostAddress
          }
        }
      }
    }
    return "localhost";
  }
}