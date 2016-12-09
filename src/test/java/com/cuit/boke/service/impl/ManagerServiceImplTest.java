package com.cuit.boke.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuit.boke.dto.ManagerBean;
import com.cuit.boke.service.ManagerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:ssh-spring.xml" })
public class ManagerServiceImplTest {
	@Autowired
	ManagerService managerService;

	@Test
	@Rollback(false)
	public void testValidLogin() {
		String number = "kanyuxia";
		String password = "123456";
		ManagerBean managerBean = managerService.validLogin(number, password);
		System.out.println(managerBean);
	}
	@Test
	@Rollback(false)
	public void testShowManager(){
		System.out.println(managerService.showManager(2));
	}
}
