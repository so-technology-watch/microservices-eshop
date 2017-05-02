package main;

import config.CustomersConfiguration;
import config.KVStore;
import config.Register;
import dao.CustomerDAO;
import dao.DAO;
import dao.GenericDAO;
import domain.Customer;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resources.AuthResource;
import resources.CustomerResource;
import services.AuthService;
import services.CustomerServices;

/**
 * Main class registering the different resources.
 * 
 * @author guillaume
 *
 */
public class CustomersApplication extends Application<CustomersConfiguration> {

    public static void main(String[] args) {

	try {
	    new CustomersApplication().run(args);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * This method is called to launch the application.
     */
    @Override
    public void run(CustomersConfiguration configuration, Environment environment) throws Exception {

	KVStore kvStore = new KVStore();
	
	
	DAO dao = new DAO();
	CustomerDAO customerDAO = new CustomerDAO(dao);
	customerDAO.getList().clear();
	
	AuthService authService = new AuthService(dao);
	CustomerServices customerServices = new CustomerServices(customerDAO);
	

	final AuthResource authResource = new AuthResource(authService);
	final CustomerResource customerResource = new CustomerResource(customerServices);

	environment.jersey().register(authResource);
	environment.jersey().register(customerResource);

	Register register = new Register(kvStore.retrieveConfig());
	register.register();

    }

}
