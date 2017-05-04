package dao;

import java.util.Map;
import java.util.Optional;

import domain.Customer;

/**
 * DAO class for the customer map, extends from GenericDAO<Customer>.
 * 
 * @author guillaume
 *
 */
public class CustomerDAO extends GenericDAO<Customer> {

    /**
     * Creates a new instance of the class, implements the parent constructor.
     * 
     * @param dao
     */
    public CustomerDAO(DAO dao) {
	super(Customer.class, dao);
    }

    /**
     * Retrieves the customer from the customer map who'se email corresponds to
     * the given email.
     * 
     * @param email
     * @return A customer.
     */
    public Optional<Customer> retreiveElementByEmail(String email) {

	return map.entrySet().stream().filter(e -> e.getValue().getCredentials().getEmail().equals(email))
		.map(Map.Entry::getValue).findFirst();
    }

}
