package dao;

import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;

public class AuthDAO {
    
    
    public static final String MAP_NAME = "Auth";

    private DB db;
    protected ConcurrentMap<Integer, String> map;

    public AuthDAO(DB db) {

	this.setDb(db);
	map = (ConcurrentMap<Integer, String>) db.hashMap(MAP_NAME).createOrOpen();
	db.commit();

    }

    public void addElement(Integer CusomerID, String token) {

	map.put(CusomerID, token);
	db.commit();
    }

    public String retreiveElement(Integer customerID) {

	return map.get(customerID);

    }
    

    public void removeElement(Integer customerID) {

	map.remove(customerID);
	db.commit();
    }

    public DB getDb() {

	return db;
    }

    public void setDb(DB db) {

	this.db = db;
    }


}
