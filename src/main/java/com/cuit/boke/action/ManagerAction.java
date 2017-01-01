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
	
	private com.cuit.boke.dto.Result<String>  update_result;
	
	private String password;
	
	private String new_password;
	
	private String name;
	
	private String profession;
	
	private String elucidation;
	
	private String signature;

	private String address;
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
	 * 管理员修改个人信息Action
	 */
	@Action(value = "/upadte_info", results = {
			@Result(name="success",type="json",params={"root","update_result"})
	})
	public String updateInfo(){
		int id = 1;
		Manager manager = new Manager();
		manager.setName(name);
		manager.setElucidation(elucidation);
		manager.setAddress(address);
		manager.setPerfessional(profession);
		manager.setSignature(signature);	
		try {
			//调用service验证
			managerService.updateInfo(manager, id);
			update_result = new com.cuit.boke.dto.Result<String>("ok", "true", null);
		} catch (Exception e) {
			e.printStackTrace();
			update_result = new com.cuit.boke.dto.Result<String>("error", "false", "修改失败");
		}
		
		return SUCCESS;
	}
	/**
	 * 管理员修改密码Action
	 */
	@Action(value = "/upadte_pwd", results = {
			@Result(name="success",type="json",params={"root","update_result"})
	})
	public String updatePwd(){
		int id = 1;
		try {
			//调用service验证
			String result = managerService.updatePwd(id, password, new_password);
			update_result = new com.cuit.boke.dto.Result<String>("ok", "true", null);
			if (result.equals("pwd_error") ) {
				update_result = new com.cuit.boke.dto.Result<String>("error", "false", "修改失败，输入旧密码错误");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			update_result = new com.cuit.boke.dto.Result<String>("error", "false", "修改失败");
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
	
	@Action(value = "easyinfo", results = {
			@Result(name="success",type="json",params={"root","result"})
	})
	public String managerForPerson(){
		try {
			//
			Manager manager = managerService.showManagerForPerson(1);
			System.out.println(manager);
			result = new com.cuit.boke.dto.Result<Manager>("ok", manager, null);
		} catch (Exception e) {
			e.printStackTrace();
			result = new com.cuit.boke.dto.Result<Manager>("ok", null, "系统错误！");
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
	
	
	public com.cuit.boke.dto.Result<String> getUpdate_result() {
		return update_result;
	}
	public void setUpdate_result(com.cuit.boke.dto.Result<String> update_result) {
		this.update_result = update_result;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getElucidation() {
		return elucidation;
	}
	public void setElucidation(String elucidation) {
		this.elucidation = elucidation;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

}
