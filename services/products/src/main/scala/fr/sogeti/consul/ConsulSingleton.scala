package fr.sogeti.consul

import com.ecwid.consul.v1.ConsulClient

object ConsulSingleton {
  val client : ConsulClient = new ConsulClient("localhost")
  val configResolver : ConfigResolver = new ConfigResolver(client)
  val serviceDiscovery : ServiceDiscovery = new ServiceDiscovery(client)
  
  /**
   * @return the config resolver's instance
   */
  def getConfigResolverInstance : ConfigResolver = configResolver
  
  /**
   * @return the service discovery's instance
   */
  def getServiceDiscoveryInstance : ServiceDiscovery = serviceDiscovery
  
  /**
   * @return the consul client instance
   */
  def getClientInstance : ConsulClient = client
}

