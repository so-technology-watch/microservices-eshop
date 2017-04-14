package fr.sogeti.consul

import com.ecwid.consul.v1.ConsulClient

object ConsulSingleton {
  val client : ConsulClient = new ConsulClient("localhost")
  val configResolver : ConfigResolver = new ConfigResolver(client)
  val serviceDiscovery : ServiceDiscovery = new ServiceDiscovery(client)
  
  def getConfigResolverInstance : ConfigResolver = configResolver
  def getServiceDiscoveryInstance : ServiceDiscovery = serviceDiscovery
  def getClientInstance : ConsulClient = client
}

