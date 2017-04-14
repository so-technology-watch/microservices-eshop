package fr.sogeti.consul

import com.ecwid.consul.v1.ConsulClient
import com.ecwid.consul.v1.agent.model.NewService
import java.util.Arrays

class ServiceDiscovery(client : ConsulClient) {
  
  val id = "products service"
  
  def register() : Unit = {
    val newService : NewService = new NewService()
    newService.setId(id)
    newService.setName("products service")
    newService.setAddress("localhost")
    newService.setTags(Arrays.asList("service","products"))
    newService.setPort(8080)
   
    val check : NewService.Check = new NewService.Check()
    check.setHttp("http://localhost:8080")
    check.setInterval("30s")
    newService.setCheck(check)
    
    client.agentServiceRegister(newService)  
  }
  
  def unregister() : Unit = {
    client.agentServiceDeregister(id)
  }
  
  def getId() : String = id
}