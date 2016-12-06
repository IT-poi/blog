package com.cuit.boke.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuit.boke.dao.MangerDao;
import com.cuit.boke.entity.Manager;

@Repository
public class ManagerDaoImpl implements MangerDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	
	public Manager queryById(Integer id) {
		String sql = "select * from manager where id = "+ id;
		List<Manager> list = (List<Manager>) this.getCurrentSession().createSQLQuery(sql).addEntity(Manager.class).list();
		if (list == null || list.size()<=0) {
			return null;
		}
		return list.get(0);
	}

	public List<Manager> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer insert(Manager t) {
		System.out.println(t.toString());
		return (Integer)this.getCurrentSession().save(t);
	}

	public Integer update(Manager t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void flush() {
		// TODO Auto-generated method stub
		
	}

}
