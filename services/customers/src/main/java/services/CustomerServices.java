package services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.crypto.keygen.KeyGenerators;

import dao.CustomerDAO;
import dao.GenericDAO;
import domain.Credentials;
import domain.Customer;

/**
 * Class containing all the services dealing with customers.
 * 
 * @author guillaume
 *
 */
public class CustomerServices {

    /**
     * DAO class dealing with the customer map.
     */
    private CustomerDAO dao;

    /**
     * Creates a new customerServices object from a given dao class.
     * 
     * @param dao
     */
    public CustomerServices(CustomerDAO dao) {

	this.dao = dao;
    }

    /**
     * Finds a customer with it's given id.
     * 
     * @param customerID
     * @return customer
     */
    public Customer getCustomer(String customerID) {

	return dao.retrieveElement(customerID);
    }

    /**
     * returns all the customers.
     * 
     * @return list of customers
     */
    public ArrayList<Customer> getCustomers() {

	return dao.retrieveAllElements();
    }

    /**
     * Adds a new customer.
     * 
     * @param customer
     */
    public Customer addCustomer(Customer customer) {

	customer.setId(generateID());
	customer.getCredentials().setSalt(KeyGenerators.string().generateKey());
	customer.getCredentials().setPassword(hashPasssword(customer.getCredentials()));

	if (!dao.retreiveElementByEmail(customer.getEmail()).isPresent()) {

	    dao.addElement(customer.getId(), customer);

	} else {
	    customer.setId(dao.retreiveElementByEmail(customer.getEmail()).get().getId());
	}
	return customer;
    }

    /**
     * Modifies a customer.
     * 
     * @param customer
     */
    public Customer modifyCustomer(Customer customer) {

	dao.addElement(customer.getId(), customer);
	return customer;
    }

    /**
     * Removes a customer.
     * 
     * @param id
     */
    public void removeCustomer(String id) {

	dao.removeElement(id);
    }

    private String generateID() {

	return UUID.randomUUID().toString();

    }

    public static String hashPasssword(Credentials credentials) {

	MessageDigest digest;
	try {
	    String saltedPass = credentials.getPassword() + credentials.getSalt();
	    digest = MessageDigest.getInstance("SHA-1");
	    byte[] hash = digest.digest(saltedPass.getBytes(StandardCharsets.UTF_8));
	    return new String(hash);

	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	    return "";
	}

    }

    /**
     * 
     * @return dao of customers
     */
    public GenericDAO<Customer> getDao() {

	return dao;
    }

    /**
     * 
     * @param dao
     */
    public void setDao(CustomerDAO dao) {

	this.dao = dao;
    }

}
