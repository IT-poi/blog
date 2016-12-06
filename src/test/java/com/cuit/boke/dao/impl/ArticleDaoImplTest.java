package com.cuit.boke.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.entity.Article;

@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration(locations = {"classpath:ssh-*.xml", 
		"classpath:hibernate.cfg.xml", 
		"classpath:db.properties"})

public class ArticleDaoImplTest {

	@Autowired
	private ArticleDao articleDao;
	
	@Test
	//测试按id查询
	public void testQueryById() {
		Integer id = 1;
		Article article = articleDao.queryById(id);
		System.out.println(article);
	}

}
