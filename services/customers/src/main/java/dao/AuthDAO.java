package dao;

import java.util.concurrent.ConcurrentMap;

public class AuthDAO {
    
    
    public static final String MAP_NAME = "Auth";

    private DAO dao;
    protected ConcurrentMap<Integer, String> map;

    public AuthDAO(DAO dao) {

	this.dao = dao;
	map = (ConcurrentMap<Integer, String>) dao.getDb().hashMap(MAP_NAME).createOrOpen();
	dao.commit();

    }

    public void addElement(Integer CusomerID, String token) {

	map.put(CusomerID, token);
	dao.commit();
    }

    public String retreiveElement(Integer customerID) {

	return map.get(customerID);

    }
    

    public void removeElement(Integer customerID) {

	map.remove(customerID);
	dao.commit();
    }

    
    public DAO getDao() {
    
        return dao;
    }

    
    public void setDao(DAO dao) {
    
        this.dao = dao;
    }




}
