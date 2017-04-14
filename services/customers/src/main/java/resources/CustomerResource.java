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
public class CustomerResource {

    private CustomerServices customerServices;

    public CustomerResource(CustomerServices customerServices) {
	this.customerServices = customerServices;
    }

    @GET
    @Path("/{customerID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("customerID") int customerID) {

	return customerServices.getCustomer(customerID);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Customer> getCustomers() {

	System.out.println("yo");
	return customerServices.getCustomers();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int postCustomer(Customer customer) {

	customerServices.addCustomer(customer);

	return customer.getId();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public int putCustomer(Customer customer) {

	customerServices.modifyCustomer(customer);
	return customer.getId();
    }

    @DELETE
    @Path("/{customersID}")
    public String deleteCustomer(@PathParam("customerID") int customerID) {

	customerServices.removeCustomer(customerID);
	return "Client supprimé avec succès.";
    }

    public CustomerServices getCustomerServices() {

	return customerServices;
    }

    public void setCustomerServices(CustomerServices customerServices) {

	this.customerServices = customerServices;
    }

}
