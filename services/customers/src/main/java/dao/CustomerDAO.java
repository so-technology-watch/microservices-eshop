package dao;

import java.util.Map;

import org.mapdb.DB;

import domain.Customer;

public class CustomerDAO extends GenericDAO<Customer> {

    public CustomerDAO(DB db) {
	super(Customer.class, db);
    }

    public Customer retreiveElementByEmail(String email) {

	return map.entrySet().stream().filter(e -> e.getValue().getCredentials().getEmail().equals(email))
		.map(Map.Entry::getValue).findFirst().get();
    }

}
