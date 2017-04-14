package fr.sogeti.consul

import com.google.gson.Gson
import com.ecwid.consul.v1.{Response, ConsulClient}
import com.ecwid.consul.v1.kv.model.GetValue

class ConfigResolver(client : ConsulClient) {
  
  val gson : Gson = new Gson
  val configKey = "config/services/products"
  
  /**
   * @return the service's configuration stored on the consul's server for the key {@code configKey}
   */
  def getConfig : Config = {
    val json = getValue(configKey)
    gson.fromJson(json, classOf[Config])
  }
  
  /**
   * @param the service's configuration to store on the consul's server for the key {@code configKey}
   */
  def putConfig(config : Config) : Unit = {
    val json = gson.toJson(config)
    client.setKVValue(configKey, json)
  }
  
  /**
   * @param key
   * @return the value for the given key on the consul's server
   */
  private def getValue(key : String) : String = {
    val response : Response[GetValue] = client.getKVValue(key)
    response.getValue.getDecodedValue
  }
}