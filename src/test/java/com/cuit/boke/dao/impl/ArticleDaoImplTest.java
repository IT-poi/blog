package com.cuit.boke.dao.impl;


import java.util.Date;

import javax.transaction.Transactional;

import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration({"classpath:ssh-spring.xml"})
public class ArticleDaoImplTest {

	@Autowired
	private ArticleDaoImpl articleDao;
	
	@Autowired
	private ManagerDaoImpl ManagerDaoImpl;
	
	@Test
	//测试按id查询
	@Transactional
	public void testQueryById() {
		Integer id = 1;
		Article article = articleDao.queryById(Article.class,id);
		System.out.println(article);
	}
	
	
	@Test
	public void testInsert() {
		Transaction managerTransaction = ManagerDaoImpl.getSessionFactory().getCurrentSession().beginTransaction();
		Manager manager1 = new Manager();
		manager1.setNumber("113");
		manager1.setPassword("1312");
		manager1.setCreateTime(new Date());
		System.out.println(manager1);
		System.out.println(ManagerDaoImpl.insert(manager1));
		System.out.println("---------------------------------------");
		managerTransaction.commit();
		Transaction artiTransaction = articleDao.getSessionFactory().getCurrentSession().beginTransaction();
		
		Manager manager = ManagerDaoImpl.queryById(Manager.class,1);
		Article article = new Article();
		article.setTitle("生活");
		article.setContent("美好的生活");
		article.setManager(manager);
		article.setBrief("美好的生活");
		article.setImgURL("");
		article.setCreateTime(new Date());
		System.out.println(article.getCreateTime());
		article.setLabel("生活");
		System.out.println(article);
		int i = articleDao.insert(article);
		artiTransaction.commit();
		articleDao.flush();
		System.out.println(i);
	
	}

}
