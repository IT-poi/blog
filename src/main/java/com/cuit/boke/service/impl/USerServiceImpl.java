package com.cuit.boke.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.impl.UserDao;
import com.cuit.boke.entity.User;
import com.cuit.boke.service.UserService;
@Service
public class USerServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		try {
			return userDao.insert(user);
		} catch (DataIntegrityViolationException e) {
			// TODO: handle exception
			System.out.println("userServiceImpl catch exception!");
			return 0;
		}
	}
	
}
