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

	newService.setId("customers");
	newService.setName("Customers service");
	newService.setAddress("127.0.0.1");
	newService.setTags(Arrays.asList("service", "customers", "customer", "auth", "authentication"));
	newService.setPort(8080);
	NewService.Check check = new NewService.Check();
	check.setHttp("http://127.0.0.1:8080");
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

}
