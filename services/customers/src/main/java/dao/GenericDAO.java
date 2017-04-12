package dao;

import java.util.concurrent.ConcurrentMap;

import org.mapdb.DB;

public class GenericDAO<T> {

    public static String MAP_NAME;

    private Class<T> clazz;
    private DB db;
    protected ConcurrentMap<Integer, T> map;

    public GenericDAO(Class<T> clazz, DB db) {

	this.setClazz(clazz);
	this.db = db;
	MAP_NAME = clazz.getCanonicalName();
	db.hashMap(clazz.getCanonicalName()).createOrOpen();

    }

    public void addElement(Integer id, T t) {

	map.put(id, t);
    }

    public T retreiveElement(Integer id) {

	return map.get(id);

    }

    public void removeElement(Integer id) {

	map.remove(id);
    }

    public Class<T> getClazz() {

	return clazz;
    }

    public void setClazz(Class<T> clazz) {

	this.clazz = clazz;
    }

    public DB getDb() {

	return db;
    }

    public void setDb(DB db) {

	this.db = db;
    }

    public ConcurrentMap<Integer, T> getList() {

	return map;
    }

    public void setList(ConcurrentMap<Integer, T> list) {

	this.map = list;
    }

}
