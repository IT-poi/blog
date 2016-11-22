package com.cuit.boke.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cuit.boke.entity.User;
import com.cuit.boke.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

@Controller("bokeAction")
public class BokeAction extends ActionSupport {
	
	@Autowired
	private UserService userService;
	
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String ds(){
		return "error";
	}
	
	public String dsa(){
		
		System.out.println( "name from html is :" + this.user.getName());
		System.out.println( "Age from html is :" + this.user.getAge());
		System.out.println( "Id from html is :" + this.user.getId());
		boolean flag = false;
		try {
			flag = userService.insertUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("action catch exception!");
			flag = false;
		}
		if(flag){
			return "success";
		}else {
			return "error";
		}
	}
	
}