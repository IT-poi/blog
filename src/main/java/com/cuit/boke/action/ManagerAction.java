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
import com.cuit.boke.service.ManagerService;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace(value = "/managerAction")
@ParentPackage(value = "json-default")
@Scope(value = "prototype")
@Results({
	@Result(name="success",type="json",params={"root","data"})
})
public class ManagerAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ManagerService managerService;

	private Map<String, com.cuit.boke.dto.Result<ManagerBean>> data = new HashMap<String, com.cuit.boke.dto.Result<ManagerBean>>();

	private String number;
	
	private String password;
	
	@Action(value = "getInfo")
	@Override
	public String execute() throws Exception {
		ManagerBean managerBean = managerService.validLogin(number, password);
		System.out.println("execute"+managerBean);
		com.cuit.boke.dto.Result<ManagerBean> result = new com.cuit.boke.dto.Result<ManagerBean>("", managerBean, "");
		if(result.getData().getManager()!=null){
			result.getData().getManager().setArticles(null);
		}
		System.out.println(result);
		data.put("result", result);
		System.out.println("hello");
		return SUCCESS;
	}
	
	public Map<String, com.cuit.boke.dto.Result<ManagerBean>> getData() {
		System.out.println("world");
		System.out.println("data:"+data);
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

	

}
