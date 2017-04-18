package resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import domain.Customer;
import services.CustomerServices;

@Path("/api/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
/**
 * Resource dealing with the customers.
 * 
 * @author guillaume
 *
 */
public class CustomerResource {

    /**
     * Service dealing with the customers.
     */
    private CustomerServices customerServices;

    /**
     * 
     * @param customerServices
     */
    public CustomerResource(CustomerServices customerServices) {
	this.customerServices = customerServices;
    }

    @GET
    @Path("/{customerID}")
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Handles the GET method.
     * 
     * @param customerID
     * @return customer in JSON format.
     */
    public Customer getCustomer(@PathParam("customerID") int customerID) {

	return customerServices.getCustomer(customerID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    /**
     * Handles the GET method.
     * 
     * @return a list of customers.
     */
    public ArrayList<Customer> getCustomers() {

	System.out.println("yo");
	return customerServices.getCustomers();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    /**
     * Handles the POST method.
     * 
     * @param customer
     * @return customerID of type int.
     */
    public int postCustomer(Customer customer) {

	customerServices.addCustomer(customer);

	return customer.getId();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    /**
     * Handles the PUT method.
     * 
     * @param customer
     * @return customerID of type int
     */
    public int putCustomer(Customer customer) {

	customerServices.modifyCustomer(customer);
	return customer.getId();
    }

    @DELETE
    @Path("/{customersID}")
    /**
     * Handles the DELETE method.
     * 
     * @param customerID
     * @return a message of type string.
     */
    public String deleteCustomer(@PathParam("customerID") int customerID) {

	customerServices.removeCustomer(customerID);
	return "Client supprimé avec succès.";
    }

    /**
     * 
     * @return a customerService.
     */
    public CustomerServices getCustomerServices() {

	return customerServices;
    }

    /**
     * 
     * @param customerServices
     */
    public void setCustomerServices(CustomerServices customerServices) {

	this.customerServices = customerServices;
    }

}
