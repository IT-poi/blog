package com.cuit.boke.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuit.boke.dao.ManagerDao;
import com.cuit.boke.dto.ManagerBean;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.service.ManagerService;
import com.cuit.boke.utils.Md5Utils;
@Service
@Transactional
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private ManagerDao managerDao;
	
	public ManagerBean validLogin(String number, String password) {
		List<Manager> managers = managerDao.queryAll(Manager.class);
		if(managers.isEmpty()){
			return new ManagerBean(false, "用户名或者密码错误");
		}else {
			for(Manager manager:managers){
				if(manager.getNumber().equals(number)
						&&manager.getPassword().equals(Md5Utils.getMd5(password))){
					HttpServletRequest request = ServletActionContext.getRequest();
					HttpSession session = request.getSession();
					session.setAttribute("isLogin", "true");
					return new ManagerBean(true, manager, null);
				}
			}
		}
		return new ManagerBean(false, "用户名或者密码错误");
	}
	
	public Manager showManager(int id) {
		
		return managerDao.queryById(Manager.class, id);
	
	}

	public Manager showManagerForPerson(int id) {
		Manager manager = showManager(id);
		manager.setPassword("");
		manager.setNumber("");
		return manager;
	}
}
