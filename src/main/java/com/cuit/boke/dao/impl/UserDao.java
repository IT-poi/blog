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
public class UserDao implements GenericDao<User> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public User queryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insert(User t) {
		// TODO Auto-generated method stub
		boolean flag = true;
		try {
			//返回id
			int i = (Integer)this.getCurrentSession().save(t);
			System.out.println("本次操作影响的行数："+i);
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("userDao catch exception!");
			flag = false;
		}
		return flag;
	}

	public int update(User t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flush() {
		// TODO Auto-generated method stub
		if(this.getCurrentSession()!=null){
			this.getCurrentSession().flush();
		}
	}

}
