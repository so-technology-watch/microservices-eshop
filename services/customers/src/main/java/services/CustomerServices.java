package services;

import java.util.ArrayList;
import java.util.Map;

import dao.GenericDAO;
import domain.Customer;
import groovyjarjarantlr.collections.List;

public class CustomerServices {

    private GenericDAO<Customer> dao;

    public CustomerServices(GenericDAO<Customer> dao) {

	this.dao = dao;
    }
    
    
    public Customer getCustomer(int customerID){
	
	return dao.retreiveElement(customerID);
    }
    
    public ArrayList<Customer> getCustomers(){
	
	return dao.retreiveAllElements();
    }

    public void addCustomer(Customer customer) {

	dao.addElement(customer.getId(), customer);
    }

    public void modifyCustomer(Customer customer) {

	dao.addElement(customer.getId(), customer);
    }

    public void removeCustomer(int id) {

	dao.removeElement(id);
    }

    public GenericDAO<Customer> getDao() {

	return dao;
    }

    public void setDao(GenericDAO<Customer> dao) {

	this.dao = dao;
    }

}
