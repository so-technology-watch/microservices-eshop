package config;

import java.util.Arrays;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.NewService;

public class Register {

    private ConsulClient consulClient;
    private NewService newService;

    public Register() {
	this.consulClient = new ConsulClient("10.226.159.191");
	this.newService = new NewService();

    }

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

    public ConsulClient getConsulClient() {

	return consulClient;
    }

    public void setConsulClient(ConsulClient consulClient) {

	this.consulClient = consulClient;
    }

    public NewService getNewService() {

	return newService;
    }

    public void setNewService(NewService newService) {

	this.newService = newService;
    }

}
