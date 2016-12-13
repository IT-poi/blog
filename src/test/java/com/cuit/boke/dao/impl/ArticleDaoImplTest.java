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

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.utils.Md5Utils;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration({"classpath:ssh-spring.xml"})
public class ArticleDaoImplTest {

	@Autowired
	private ArticleDaoImpl articleDao;
	
	@Autowired
	private ManagerDaoImpl managerDaoImpl;
	
	@Test
	//测试按id查询
	@Transactional
	public void testQueryById() {
		Integer id = 1;
		Article article = articleDao.queryById(Article.class,id);
		System.out.println(article);
	}
	
	
	@Test
	@Rollback(false)
	public void testInsert() {
		Manager manager1 = new Manager();
		manager1.setNumber("113");
		manager1.setPassword("1312");
		manager1.setCreateTime(new Date());
		System.out.println(manager1);
		System.out.println(managerDaoImpl.insert(manager1));
		System.out.println("---------------------------------------");
		
		
		Manager manager = managerDaoImpl.queryById(Manager.class,1);
		Article article1 = new Article();
		article1.setTitle("java编程思想");
		article1.setContent("Thinking in java是一本很好的Java学习数据");
		article1.setManager(manager);
		article1.setBrief("Thinking in java 是一本...");
		article1.setImgURL("");
		article1.setStick(true);
		article1.setCreateTime(new Date());
		article1.setLabel("java");
		
		
		Article article = new Article();
		article.setTitle("明天");
		article.setContent("明天很美好，充满希望");
		article.setManager(manager);
		article.setBrief("明天很美好");
		article.setImgURL("");
		article.setStick(false);
		article.setCreateTime(new Date());
		article.setLabel("生活");
		
		
		Article article2 = new Article();
		article2.setTitle("梦想");
		article2.setContent("梦想是很缥缈的东西，也很美好");
		article2.setManager(manager);
		article2.setBrief("梦想是很缥缈的东西");
		article2.setImgURL("");
		article2.setCreateTime(new Date());
		article2.setLabel("生活");
		
		int i = articleDao.insert(article);
		System.out.println(i);
		
		int i1 = articleDao.insert(article1);
		System.out.println(i1);
		
		int i2 = articleDao.insert(article2);
		System.out.println(i2);
	
	}
	
	@Test
	@Rollback(false)
	public void testQueryByPageLabel(){
		List<Article> list = articleDao.queryByLabelPage("java", 0, 2);
		System.out.println(list);
	}
	
	@Test
	public void testQueryByPage(){
		List<Article> list = articleDao.queryByPage(Article.class, 0, 2, PageBean.TIME, null);
		System.out.println(list);
	}
	
	@Test
	@Rollback(false)
	public void testUpdate(){
		Article article = articleDao.queryById(Article.class, 1);
		article.setPageView(2);
		Article article2 = new Article();
		System.out.println(article2);
		articleDao.update(article2);
	}
	
	@Test
	public void testMd5(){
		String md5_1 = "123456";
		String md5_2 = "123456";
		String md5_3 = "123457";
		
		System.out.println(Md5Utils.getMd5(md5_1));
		System.out.println(Md5Utils.getMd5(md5_2));
		System.out.println(Md5Utils.getMd5(md5_3));
		System.out.println(Md5Utils.getMd5(md5_1).equals(Md5Utils.getMd5(md5_2)));
		System.out.println(Md5Utils.getMd5(md5_1).equals(Md5Utils.getMd5(md5_3)));
	}

}
