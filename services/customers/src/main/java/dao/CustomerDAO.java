package dao;

import java.util.Map;

import domain.Customer;

public class CustomerDAO extends GenericDAO<Customer> {

    public CustomerDAO(DAO dao) {
	super(Customer.class, dao);
    }

    public Customer retreiveElementByEmail(String email) {

	return map.entrySet().stream().filter(e -> e.getValue().getCredentials().getEmail().equals(email))
		.map(Map.Entry::getValue).findFirst().get();
    }

}
