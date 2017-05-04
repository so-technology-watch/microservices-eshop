package dao;

import java.util.concurrent.ConcurrentMap;

/**
 * DAO class for the auth map.
 * 
 * @author guillaume
 *
 */
public class AuthDAO {

    /**
     * Name of the map.
     */
    public static final String MAP_NAME = "Auth";
    /**
     * DAO object interacting with the database itself.
     */
    private DAO dao;
    /**
     * Auth map.
     */
    protected ConcurrentMap<String, String> map;

    /**
     * Creates a new instance of the class and creates the auth map.
     * 
     * @param dao
     */
    public AuthDAO(DAO dao) {

	this.dao = dao;
	map = (ConcurrentMap<String, String>) dao.getDb().hashMap(MAP_NAME).createOrOpen();
	dao.commit();

    }

    /**
     * Adds a new customerID, token couple to the map.
     * 
     * @param CusomerID
     * @param token
     */
    public void addElement(String CusomerID, String token) {

	map.put(CusomerID, token);
	dao.commit();
    }

    /**
     * Retrieves a customerID, token couple from the map.
     * 
     * @param customerID
     * @return JWT Token.
     */
    public String retrieveElement(String customerID) {

	return map.get(customerID);

    }

    /**
     * Removes a CustomerID, token couple from the map.
     * 
     * @param customerID
     */
    public void removeElement(String customerID) {

	map.remove(customerID);
	dao.commit();
    }

    /**
     * 
     * @return dao object of type DAO.
     */
    public DAO getDao() {

	return dao;
    }

    /**
     * 
     * @param dao
     */
    public void setDao(DAO dao) {

	this.dao = dao;
    }

}
