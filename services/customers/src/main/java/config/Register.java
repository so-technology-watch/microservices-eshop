package config;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
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
	this.consulClient = new ConsulClient(System.getenv("consul"));
	this.newService = new NewService();
	this.configuration = configuration;
    }

    /**
     * 
     */
    public Register() {
	this.consulClient = new ConsulClient(System.getenv("consul"));
	this.newService = new NewService();

    }

    /**
     * Registers the new service to consul.
     */
    public void register() {

	newService.setId("customers-service");
	newService.setName("customers-service");
	try {
	    newService.setAddress(getLocalAddress("eth0"));
	} catch (SocketException e) {
	    e.printStackTrace();
	}
	newService.setTags(Arrays.asList("service", "customers", "customer", "auth", "authentication"));
	newService.setPort(Integer.parseInt(configuration.getPort()));
	NewService.Check check = new NewService.Check();
	try {
	    check.setHttp("http://" + getLocalAddress("ethO") + ":" + configuration.getPort() + "/api/v1/customers/1");
	} catch (SocketException e) {
	    e.printStackTrace();
	}
	check.setInterval("30s");
	newService.setCheck(check);
	System.out.println(newService.getAddress());
	consulClient.agentServiceRegister(newService);

    }

    /**
     * Takes an interface name as parameter and returns it's ipv4 address.
     * 
     * @param netInt
     * @return host IP address
     * @throws SocketException
     */
    private String getLocalAddress(String netInt) throws SocketException {

	Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
	while (nets.hasMoreElements()) {
	    NetworkInterface interf = nets.nextElement();
	    if (interf.getName().equalsIgnoreCase(netInt)) {
		Enumeration<InetAddress> addresses = interf.getInetAddresses();
		while (addresses.hasMoreElements()) {
		    InetAddress next = addresses.nextElement();
		    if (next instanceof Inet4Address) {
			return next.getHostAddress();
		    }
		}
	    }
	}
	return "localhost";
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
