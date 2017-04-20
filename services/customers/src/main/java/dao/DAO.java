package dao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.mapdb.DB;
import org.mapdb.DBMaker;

/**
 * Class that handles the database. Is accessed by other DAO classes to handle
 * the different maps.
 * 
 * @author guillaume
 *
 */
public class DAO {

    /**
     * MapDB data base.
     */
    private DB db;

    /**
     * Creates a new instance of the class an creates or retrieves the database
     * from file.
     */
    public DAO() {

	
	this.db = DBMaker.fileDB("customers.db").fileMmapEnable().closeOnJvmShutdown().make();
    }

    /**
     * Commits the transation.
     */
    public void commit() {

	db.commit();
    }

    /**
     * 
     * @return MapDB data base.
     */
    public DB getDb() {

	return db;
    }

    /**
     * 
     * @param db
     */
    public void setDb(DB db) {

	this.db = db;
    }
}
