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
					session.setAttribute("userName", manager.getName());
					session.setAttribute("userId",manager.getId());
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
		//将去到的manager复制到一个新的实体中，去掉博主账号，密码和创建时间
		//如果直接修改manager对象，会将修改后的结果同步到数据库中
		Manager easyManager = new Manager();
		easyManager.setId(manager.getId());
		easyManager.setName(manager.getName());
		easyManager.setElucidation(manager.getElucidation());
		easyManager.setAddress(manager.getAddress());
		easyManager.setPerfessional(manager.getPerfessional());
		easyManager.setSignature(manager.getSignature());
		return easyManager;
	}

	public void updateInfo(Manager manager,int id) {
		Manager ma = showManager(id);
		ma.setName(manager.getName());
		ma.setAddress(manager.getAddress());
		ma.setElucidation(manager.getElucidation());
		ma.setPerfessional(manager.getPerfessional());
		ma.setSignature(manager.getSignature());
		managerDao.update(ma);
	}

	public String updatePwd(int id, String password, String new_password) {
		Manager ma = showManager(id);
		if(Md5Utils.getMd5(password).equals(ma.getPassword())){
			ma.setPassword(Md5Utils.getMd5(new_password));//md5加密
			managerDao.update(ma);
			return "success";
		}
		return "pwd_error";  //返回旧密码匹配错误
	}
}
