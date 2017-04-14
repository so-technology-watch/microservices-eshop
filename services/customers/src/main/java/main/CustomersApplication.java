package main;

import config.CustomersConfiguration;
import dao.AuthDAO;
import dao.DAO;
import dao.GenericDAO;
import domain.Customer;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resources.AuthResource;
import resources.CustomerResource;
import services.AuthService;
import services.CustomerServices;

public class CustomersApplication extends Application<CustomersConfiguration> {

    public static void main(String[] args) {

	try {
	    new CustomersApplication().run(args);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    @Override
    public void run(CustomersConfiguration configuration, Environment environment) throws Exception {

	DAO dao = new DAO();
	GenericDAO<Customer> customerDAO = new GenericDAO<>(Customer.class, dao);
	AuthDAO authDAO = new AuthDAO(dao);

	AuthService authService = new AuthService(dao);
	CustomerServices customerServices = new CustomerServices(customerDAO);

	final AuthResource authResource = new AuthResource(authService);
	final CustomerResource customerResource = new CustomerResource(customerServices);

	environment.jersey().register(authResource);
	environment.jersey().register(customerResource);

    }

}
