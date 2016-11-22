package com.cuit.boke.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;

import com.cuit.boke.entity.User;
import com.opensymphony.xwork2.ActionSupport;
@Controller("userAction")
public class UserAction extends ActionSupport{
	
	private Map users = new HashMap();
	
	public Map getUsers() {
		return users;
	}

	public void setUsers(Map users) {
		this.users = users;
	}

	public String getUser(){
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < 5; i++) {
			User user = new User();
			user.setAge(18);
			user.setName("Inori");
			user.setId(i);
			userList.add(user);
		}
		users.put("users", userList);
		return SUCCESS;
	}
}
