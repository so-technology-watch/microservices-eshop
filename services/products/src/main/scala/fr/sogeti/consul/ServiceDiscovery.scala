package fr.sogeti.consul

import com.ecwid.consul.v1.ConsulClient
import java.util.Arrays
import com.ecwid.consul.v1.agent.model.NewService.Check
import com.ecwid.consul.v1.agent.model.NewService

class ServiceDiscovery(client : ConsulClient) {
  
  val id = "products-service"
  var newService : NewService = _
  
  /**
   * register the service to the consul server
   */
  def register(port : Int) : Unit = {
    newService = new NewService()
    newService.setId(id)
    newService.setName("products service")
    newService.setAddress("localhost")
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