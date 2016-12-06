package com.cuit.boke.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.cuit.boke.dao.GenericDao;

/**
 * 
 * @author kanyuxia
 * 该类是GenericDao接口的实现类。
 * @param <T> 泛型
 * @param <PK> 主键
 */
public class GenericDaoImpl<T,PK extends Serializable> implements GenericDao<T, PK>{
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public T queryById(Class<T> clazz, PK id) {
		return (T)sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> queryAll(Class<T> clazz) {
		String sql = "select * from "+clazz.getSimpleName();
		return sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(clazz).list();
	}

	@SuppressWarnings("unchecked")
	public PK insert(T t) {
		return (PK) sessionFactory.getCurrentSession().save(t);
	}

	public void update(T t) {
		sessionFactory.getCurrentSession().update(t);
		
	}

	public void deleteById(Class<T> clazz,PK id) {
		String sql = "delete from "+clazz.getSimpleName()+" where id="+id;
		sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
	}

	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}
	
}
