package dao;

import org.mapdb.DB;
import org.mapdb.DBMaker;

public class DAO {

    private DB db;

    public DAO() {
	
	this.setDb(DBMaker.memoryDB().make());
    }

    public DB getDb() {

	return db;
    }

    public void setDb(DB db) {

	this.db = db;
    }
}
