package com.cuit.boke.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dao.MangerDao;
import com.cuit.boke.dao.impl.ManagerDaoImpl;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.exception.UpdateFailException;

@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration({"classpath:ssh-spring.xml"})
@Transactional
public class ArticleServiceImplTest {

	@Autowired
	private ArticleServiceImpl articleServiceImpl;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private MangerDao mangerDao;
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		Article article = articleDao.queryById(Article.class, 1);
		article.setCommentNum(80);
		Manager manager = new Manager();
		manager.setId(30);
		article.setManager(manager);
		try {
			articleServiceImpl.updateArticle(article);
		} catch (DataIntegrityViolationException e) {
			System.out.println("123");
		}
	}
	@Test
	@Rollback(false)
	public void testSave(){
		Manager manager = new Manager();
		manager.setId(1);
		Article article = new Article();
		article.setTitle("后天");
		article.setContent("后天很美好，充满希望");
		article.setManager(manager);
		article.setBrief("后天很美好");
		article.setImgURL("");
		article.setStick(false);
		article.setCreateTime(new Date());
//		System.out.println(article.getCreateTime());
		article.setLabel("生活");
		articleServiceImpl.saveArticle(article);
	}
	@Test
	@Rollback(false)
	public void testQueryPage(){
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(1);
		pageBean.setPageSize(5);
		pageBean.setOrderBy(PageBean.TIME);
		pageBean.setOrder(PageBean.ASC);
		System.out.println(articleServiceImpl.recentArticleByPage(pageBean));
	}
	@Test
	@Rollback(false)
	public void testDelete(){
		Article article = articleDao.queryById(Article.class, 2);
		articleServiceImpl.deleteArticle(article);
	}

}
