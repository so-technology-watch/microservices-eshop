package services;

import dao.GenericDAO;
import domain.AuthStatus;
import domain.Credentials;

public class AuthService {

    private GenericDAO<Credentials> dao;

    public AuthService(GenericDAO<Credentials> dao) {
	this.dao = dao;
    }

    public void authentification(Credentials credentials) {

	// TODO : authentification + création du token -> stokage en BDD
    }

    public AuthStatus retreiveAuthStatus(String token) {

	// TODO : retourne si l'utilisateur est connecré ou pas.

	return null;
    }
    
    public void deleteAuthToken(String token){
	
	//TODO : récupère l'id utilisateur contenue dans le token et l'utilise comme clé pour supprimer le token.
    }

    public GenericDAO<Credentials> getDao() {

	return dao;
    }

    public void setDao(GenericDAO<Credentials> dao) {

	this.dao = dao;
    }

}
