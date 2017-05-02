package dao;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

/**
 * Generic DAO class for the different maps of the mapDB database.
 * 
 * @author guillaume
 *
 * @param <T>
 */
public class GenericDAO<T> {

    /**
     * Name of the map
     */
    public static String MAP_NAME;

    /**
     * Class of the objects that are going to be stored in the map
     */
    private Class<T> clazz;
    /**
     * DAO Class which interacts with the database itself.
     */
    private DAO dao;
    /**
     * The map containing the values of type T.
     */
    protected ConcurrentMap<String, T> map;

    /**
     * Creates a Generic DAO from the given class using the given DAO object.
     * 
     * @param clazz
     * @param dao
     */
    @SuppressWarnings("unchecked")
    public GenericDAO(Class<T> clazz, DAO dao) {

	this.setClazz(clazz);
	this.setDao(dao);
	MAP_NAME = clazz.getCanonicalName();
	map = (ConcurrentMap<String, T>) dao.getDb().hashMap(clazz.getCanonicalName()).createOrOpen();

    }

    /**
     * Adds an element to the map.
     * 
     * @param id
     * @param t
     */
    public void addElement(String id, T t) {

	map.put(id, t);
	dao.commit();

    }

    /**
     * Retrieves the value corresponding to the given key from the map.
     * 
     * @param id
     * @return Object of type T
     */
    public T retrieveElement(String id) {

	return map.get(id);

    }

    /**
     * Retrieves all the elements from the map.
     * 
     * @return
     */
    public ArrayList<T> retrieveAllElements() {

	return new ArrayList<T>(map.values());

    }

    /**
     * Removes an element from the map at the given id used as a key.
     * 
     * @param id
     */
    public void removeElement(String id) {

	map.remove(id);
	dao.commit();
    }

    /**
     * 
     * @return The type of the DAO.
     */
    public Class<T> getClazz() {

	return clazz;
    }

    /**
     * 
     * @param clazz
     */
    public void setClazz(Class<T> clazz) {

	this.clazz = clazz;
    }

    /**
     * 
     * @return The map.
     */
    public ConcurrentMap<String, T> getList() {

	return map;
    }

    /**
     * 
     * @param list
     */
    public void setList(ConcurrentMap<String, T> list) {

	this.map = list;
    }

    /**
     * 
     * @return The DAO.
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
