package config;

import java.util.Arrays;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;

/**
 * Class dealing with consul registration.
 * 
 * @author guilluame
 *
 */
public class Register {

    /**
     * The consul client
     */
    private ConsulClient consulClient;
    /**
     * The new service
     */
    private NewService newService;

    private Configuration configuration;

    public Register(Configuration configuration) {
	this.consulClient = new ConsulClient("10.226.159.191");
	this.newService = new NewService();
	this.configuration = configuration;
    }

    /**
     * 
     */
    public Register() {
	this.consulClient = new ConsulClient("10.226.159.191");
	this.newService = new NewService();

    }

    /**
     * Registers the new service to consul.
     */
    public void register() {

//	consulClient.setKVValue("config/services/customers", "{\"host\": \"127.0.0.1\", \"port\": \"8080\" }");
//	consulClient.setKVValue("config/services/carts",
//		"{\"host\": \"127.0.0.1\", \"port\": \"8080\", \"rabbitmq\": {\"host\":\"amqp\"://pi:pi@10.226.159.191:5672//pi\", \"key\": \"productUpdate\"}, \"redis\": {\"address\": \"10.226.159.191:6379\", \"pwd\": \"\", \"db\": 0}}");
	newService.setId("customers");
	newService.setName("Customers service");
	newService.setAddress(configuration.getHost());
	newService.setTags(Arrays.asList("service", "customers", "customer", "auth", "authentication"));
	newService.setPort(Integer.parseInt(configuration.getPort()));
	NewService.Check check = new NewService.Check();
	check.setHttp(configuration.getHost() + ":" + configuration.getPort());
	check.setInterval("30s");
	newService.setCheck(check);
	consulClient.agentServiceRegister(newService);

    }

    /**
     * 
     * @return consulClient
     */
    public ConsulClient getConsulClient() {

	return consulClient;
    }

    /**
     * 
     * @param consulClient
     */
    public void setConsulClient(ConsulClient consulClient) {

	this.consulClient = consulClient;
    }

    /**
     * newClient
     * 
     * @return
     */
    public NewService getNewService() {

	return newService;
    }

    /**
     * 
     * @param newService
     */
    public void setNewService(NewService newService) {

	this.newService = newService;
    }

    public Configuration getConfiguration() {

	return configuration;
    }

    public void setConfiguration(Configuration configuration) {

	this.configuration = configuration;
    }

}
