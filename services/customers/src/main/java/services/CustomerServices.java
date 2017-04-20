package services;

import java.util.ArrayList;

import dao.GenericDAO;
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
    private GenericDAO<Customer> dao;

    /**
     * Creates a new customerServices object from a given dao class.
     * 
     * @param dao
     */
    public CustomerServices(GenericDAO<Customer> dao) {

	this.dao = dao;
    }

    /**
     * Finds a customer with it's given id.
     * 
     * @param customerID
     * @return customer
     */
    public Customer getCustomer(int customerID) {

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
    public void addCustomer(Customer customer) {

	dao.addElement(customer.getId(), customer);
    }

    /**
     * Modifies a customer.
     * 
     * @param customer
     */
    public void modifyCustomer(Customer customer) {

	dao.addElement(customer.getId(), customer);
    }

    /**
     * Removes a customer.
     * 
     * @param id
     */
    public void removeCustomer(int id) {

	dao.removeElement(id);
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
    public void setDao(GenericDAO<Customer> dao) {

	this.dao = dao;
    }

}
