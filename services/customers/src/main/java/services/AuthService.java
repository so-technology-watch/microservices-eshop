package services;

import dao.CustomerDAO;
import dao.DAO;
import dao.GenericDAO;
import domain.Credentials;
import domain.Customer;
import elements.AuthStatus;
import elements.AuthToken;
import elements.Error;

public class AuthService {

    private DAO dao;
    CustomerDAO customerDAO;

    public AuthService(DAO dao) {
	this.dao = dao;
	this.customerDAO = new CustomerDAO(dao.getDb());
    }

    public String authentification(Credentials credentials) {

	Customer customer = customerDAO.retreiveElementByEmail(credentials.getEmail());

	if (customer == null) {

	    return new Error(Error.CODE_NOT_FOUND, Error.MSG_NOT_FOUND).toJson();

	} else {

	    if (customer.getCredentials().equals(credentials)) {

		return new AuthToken(customer.getId()).encodeToJWT();
	    }

	}

    }

    public AuthStatus retreiveAuthStatus(String token) {

	// TODO : retourne si l'utilisateur est connecré ou pas.

	return null;
    }

    public void deleteAuthToken(String token) {

	// TODO : récupère l'id utilisateur contenue dans le token et l'utilise
	// comme clé pour supprimer le token.
    }

    public GenericDAO<Credentials> getDao() {

	return dao;
    }

    public void setDao(GenericDAO<Credentials> dao) {

	this.dao = dao;
    }

}
