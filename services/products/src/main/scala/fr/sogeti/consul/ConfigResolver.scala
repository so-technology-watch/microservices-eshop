package fr.sogeti.consul

import com.ecwid.consul.v1.ConsulClient
import com.google.gson.Gson
import com.ecwid.consul.v1.Response
import com.ecwid.consul.v1.kv.model.GetValue

class ConfigResolver(client : ConsulClient) {
  
  val gson : Gson = new Gson
  val configKey = "config/services/products"
  
  def getConfig : Config = {
    val json = getValue(configKey)
    gson.fromJson(json, classOf[Config])
  }
  
  def putConfig(config : Config) : Unit = {
    val json = gson.toJson(config)
    client.setKVValue(configKey, json)
  }
  
  private def getValue(key : String) : String = {
    val response : Response[GetValue] = client.getKVValue(key)
    response.getValue.getDecodedValue
  }
}