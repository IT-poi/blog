package com.cuit.boke.action;
/**
 * 		名称：后台管理员操作Action类
 *      编码：谢旷，马荧
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
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
	/**
	 * 管理员登录Action
	 */
	@Action(value = "/login", results = {
			@Result(name="success",type="json",params={"root","blogResult"})
	})
	public String login(){
		try {
			//调用service验证
			ManagerBean managerBean = managerService.validLogin(number, password);
			blogResult = new com.cuit.boke.dto.Result<ManagerBean>("ok", managerBean, null);
		} catch (Exception e) {
			e.printStackTrace();
			blogResult = new com.cuit.boke.dto.Result<ManagerBean>("error", null, "系统错误！");
		}
		
		return SUCCESS;
	}
	/**
	 * 管理员登出Action
	 */
	@Action(value = "/logout", results = {
			@Result(name="success",location="/admin")
	})
	public String logout(){
		HttpServletRequest request =  ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("isLogin","false");  
		return SUCCESS;
	}
	
//	/**
//	 * 获取用户名Action
//	 */
//	@Action(value = "/admin/getUserName", results = {
//			@Result(name="success",type="json",params={"uname"})
//	})
//	public String getUserName(){
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpSession session = request.getSession();
//		String uname = (String)session.getAttribute("userName");
//		return SUCCESS;
//	}
	
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
