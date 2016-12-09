package com.cuit.boke.dao.impl;

import org.springframework.stereotype.Repository;

import com.cuit.boke.dao.ManagerDao;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.utils.Md5Utils;

@Repository
public class ManagerDaoImpl extends GenericDaoImpl<Manager, Integer> implements ManagerDao{
	
	@Override
	public Integer insert(Manager t) {
		t.setPassword(Md5Utils.getMd5(t.getPassword()));
		return super.insert(t);
	}
}
