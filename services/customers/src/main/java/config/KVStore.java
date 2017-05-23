package config;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.google.gson.Gson;

public class KVStore {

    private ConsulClient consulClient;

    public KVStore() {

	this.consulClient = new ConsulClient(System.getenv("CONSUL_CLIENT"));
    }

    public Configuration retrieveConfig() {

	try {
	   Response<GetValue> configJSON = consulClient.getKVValue("config/services/customers");
	    Gson gson = new Gson();
	    return gson.fromJson(configJSON.getValue().getDecodedValue(), Configuration.class);

	} catch (Exception e) {

	    e.printStackTrace();
	    return null;
	}
    }

}
