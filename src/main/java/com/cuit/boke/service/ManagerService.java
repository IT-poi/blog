package com.cuit.boke.service;

import com.cuit.boke.dto.ManagerBean;
import com.cuit.boke.entity.Manager;

public interface ManagerService {
	
	/**
	 * 验证密码是否正确
	 * @param name 用户名
	 * @param password 密码
	 * @return ManagerBean
	 */
	public ManagerBean validLogin(String number,String password);
	
	/**
	 * 展示博主个人信息
	 * @return ManagerBean
	 */
	public Manager showManager(int id);
	
	/**
	 * 显示给别人的博主信息
	 * @param id
	 * @return
	 */
	public Manager showManagerForPerson(int id);
	/**
	 * 修改博主信息
	 * @param id
	 * @return
	 */
	public Manager updateInfo(Manager manager);
	/**
	 * 修改博主密码
	 * @param id
	 * @return
	 */
	public Manager updatePwd(int id,String password);

}
