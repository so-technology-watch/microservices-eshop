package dao;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentMap;

public class GenericDAO<T> {

    public static String MAP_NAME;

    private Class<T> clazz;
    private DAO dao;
    protected ConcurrentMap<Integer, T> map;

    public GenericDAO(Class<T> clazz, DAO dao) {

	this.setClazz(clazz);
	this.setDao(dao);
	MAP_NAME = clazz.getCanonicalName();
	map = (ConcurrentMap<Integer, T>) dao.getDb().hashMap(clazz.getCanonicalName()).createOrOpen();

    }

    public void addElement(Integer id, T t) {

	map.put(id, t);
	dao.commit();
	
    }

    public T retreiveElement(Integer id) {

	
	return map.get(id);

    }
    
    public ArrayList<T> retreiveAllElements(){
	
	

	return new ArrayList<T>(map.values());
	
	
    }

    public void removeElement(Integer id) {

	map.remove(id);
	dao.commit();
    }

    public Class<T> getClazz() {

	return clazz;
    }

    public void setClazz(Class<T> clazz) {

	this.clazz = clazz;
    }

    public ConcurrentMap<Integer, T> getList() {

	return map;
    }

    public void setList(ConcurrentMap<Integer, T> list) {

	this.map = list;
    }

    public DAO getDao() {

	return dao;
    }

    public void setDao(DAO dao) {

	this.dao = dao;
    }

}
