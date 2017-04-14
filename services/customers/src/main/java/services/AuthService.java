package services;

import dao.AuthDAO;
import dao.CustomerDAO;
import dao.DAO;
import domain.Credentials;
import domain.Customer;
import elements.AuthStatus;
import elements.AuthToken;
import elements.Error;

public class AuthService {

    private DAO dao;
    private CustomerDAO customerDAO;
    private AuthDAO authDAO;

    public AuthService(DAO dao) {
	this.setDao(dao);
	this.customerDAO = new CustomerDAO(dao);
	this.authDAO = new AuthDAO(dao);

    }

    public String authentification(Credentials credentials) {

	Customer customer = customerDAO.retreiveElementByEmail(credentials.getEmail());
	System.out.println(customer.getCredentials().getPassWord());
	System.out.println(credentials.getPassWord());

	if (customer == null) {

	    return new Error(Error.CODE_NOT_FOUND, Error.MSG_NOT_FOUND).toJson();

	} else {

	    if (customer.getCredentials().getPassWord().equals(credentials.getPassWord())) {

		String token = new AuthToken(customer.getId()).encodeToJWT();
		authDAO.addElement(customer.getId(), token);

		return token;
	    }

	    else {

		return new Error(Error.CODE_PWD_MISMATCH, Error.MSG_PWD_MISMATCH).toJson();
	    }

	}

    }

    public AuthStatus retreiveAuthStatus(String token) {

	AuthToken authToken = new AuthToken(token);
	String storedToken = authDAO.retreiveElement(authToken.getCustomerID());

	if (storedToken != null && storedToken.equals(token) ) {

	    return new AuthStatus(AuthStatus.CODE_AUTH, AuthStatus.MSG_AUTH);

	} else {

	    return new AuthStatus(AuthStatus.CODE_NOT_AUTH, AuthStatus.MSG_NOT_AUTH);
	}

    }

    public String deleteAuthToken(String token) {

	AuthToken authToken = new AuthToken(token);
	authDAO.removeElement(authToken.getCustomerID());
	return "Authentification token as successfully been deleted";

    }

    public DAO getDao() {

	return dao;
    }

    public void setDao(DAO dao) {

	this.dao = dao;
    }

}
