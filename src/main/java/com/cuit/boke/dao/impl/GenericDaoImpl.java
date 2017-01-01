package com.cuit.boke.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
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
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public T queryById(Class<T> clazz, PK id) {
		return (T)sessionFactory.getCurrentSession().get(clazz, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> queryAll(Class<T> clazz) {
		String sql = "select * from "+clazz.getSimpleName();
		return sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(clazz).list();
	}
	
	//分页查询
	@SuppressWarnings("unchecked")
	public List<T> queryByPage(Class<T> clazz, int begin, int pageSize,
			String orderBy, String order) {
		String sql = "select * from "+ clazz.getSimpleName();
		if (orderBy!=null) {
			//如果排序字段不为空，则加上order by
			sql = sql +  " order by " + orderBy;
			if (order == null) {
				//默认按照降序排序
				sql = sql + " DESC";
			}else {
				sql = sql + " " + order;
			}
		}
		sql = sql + " limit " + begin + "," + pageSize;
		List<T> list = (List<T>) sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(clazz).list();
		return list;
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

	public int queryCount(Class<T> clazz) {
		String sql = "select count(*) from " + clazz.getSimpleName();
		BigInteger count = (BigInteger) sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}
	
	
}
