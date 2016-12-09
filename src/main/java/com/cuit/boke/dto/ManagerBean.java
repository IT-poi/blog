package com.cuit.boke.dto;

import com.cuit.boke.entity.Manager;

public class ManagerBean {
	
	private boolean success;
	
	private Manager manager;
	
	private String message;
	
	//登录成功，返回manager实体
	public ManagerBean(boolean success, Manager manager, String message) {
		this.success = success;
		this.manager = manager;
		this.message = message;
	}
	
	//登录失败，只返回错误信息
	public ManagerBean(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
