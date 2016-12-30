package com.cuit.boke.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.cuit.boke.dto.ManagerBean;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.service.ManagerService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace(value = "/manager")
@ParentPackage(value = "json-default")
@Scope(value = "prototype")
public class ManagerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ManagerService managerService;

	private Map<String, com.cuit.boke.dto.Result<ManagerBean>> data = new HashMap<String, com.cuit.boke.dto.Result<ManagerBean>>();

	private com.cuit.boke.dto.Result<Manager> result;
	
	private com.cuit.boke.dto.Result<ManagerBean> blogResult;
	
	private String number;
	
	private String password;
	
	@Action(value = "/login", results = {
			@Result(name="success",type="json",params={"root","blogResult"})
	})
	public String login(){
		try {
			ManagerBean managerBean = managerService.validLogin(number, password);
			blogResult = new com.cuit.boke.dto.Result<ManagerBean>("ok", managerBean, null);
		} catch (Exception e) {
			e.printStackTrace();
			blogResult = new com.cuit.boke.dto.Result<ManagerBean>("error", null, "系统错误！");
		}
		
		return SUCCESS;
	}
	@Action(value = "/back/index", results = {
			@Result(name="/back/index",location="/admin/index.html")
	})
	public String index(){
		return "success";
	}
	@Action(value = "easyinfo", results = {
			@Result(name="success",type="json",params={"root","result"})
	})
	public String managerForPerson(){
		try {
			//
			Manager manager = managerService.showManagerForPerson(1);
			System.out.println(manager);
			result = new com.cuit.boke.dto.Result<Manager>("OK", manager, null);
		} catch (Exception e) {
			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<Manager>("OK", null, "系统错误！");
		}
		return SUCCESS;
	}
	public Map<String, com.cuit.boke.dto.Result<ManagerBean>> getData() {
		return data;
	}
	
	@JSON(serialize=false)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	@JSON(serialize=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public com.cuit.boke.dto.Result<Manager> getResult() {
		return result;
	}
	
	public void setResult(com.cuit.boke.dto.Result<Manager> result) {
		this.result = result;
	}
	
	public com.cuit.boke.dto.Result<ManagerBean> getBlogResult() {
		return blogResult;
	}
	
	public void setBlogResult(com.cuit.boke.dto.Result<ManagerBean> blogResult) {
		this.blogResult = blogResult;
	}
}
