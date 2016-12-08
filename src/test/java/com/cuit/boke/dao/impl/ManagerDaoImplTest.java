package com.cuit.boke.dao.impl;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.entity.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration({"classpath:ssh-spring.xml"})
public class ManagerDaoImplTest {
	
	
	@Autowired
	private ManagerDaoImpl managerDaoImpl;
	
	@Test
	@Rollback(false)
	@Transactional
	public void testInsert() {
		Manager manager = new Manager();
		manager.setNumber("1");
		manager.setPassword("1");
		manager.setCreateTime(new Date());
		System.out.println(manager);
		System.out.println(managerDaoImpl.insert(manager));
		Manager manager1 = new Manager();
		manager1.setNumber("2");
		manager1.setPassword("2");
		manager1.setCreateTime(new Date());
		System.out.println(manager1);
		System.out.println(managerDaoImpl.insert(manager1));
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testQueryById(){
		Manager manager = managerDaoImpl.queryById(Manager.class, 1);
		System.out.println(manager);
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testUpdate(){
		Manager manager = managerDaoImpl.queryById(Manager.class, 1);
		manager.setAddress("address");
		managerDaoImpl.update(manager);
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testQueryAll(){
		List<Manager> managers = managerDaoImpl.queryAll(Manager.class);
		for(Manager manager:managers){
			System.out.println(manager);
		}
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testDeleteById(){
		managerDaoImpl.deleteById(Manager.class, 1);
	}
	

}
