package dao;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class DAO {

    private DB db;

    public DAO() {

	this.db = DBMaker.fileDB("customers.db").make();
    }
    
    
    public void commit(){
	
	db.commit();
    }
    		
    public DB getDb() {

	return db;
    }

    public void setDb(DB db) {

	this.db = db;
    }
}
