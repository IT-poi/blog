package com.cuit.boke.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.GenericDao;
import com.cuit.boke.entity.User;
@Component
public class UserDao implements GenericDao<User, Integer> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public User queryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<User> queryByPage(int begin, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer insert(User t) {
		int i = -1;
		try {
			//返回id
			i = (Integer)this.getCurrentSession().save(t);
			System.out.println("本次操作影响的行数："+i);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("userDao catch exception!");
		}
		return i;
	}

	public Integer update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flush() {
		this.getCurrentSession().flush();
	}

}
