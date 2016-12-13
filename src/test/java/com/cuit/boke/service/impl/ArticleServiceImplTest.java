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
import com.cuit.boke.dao.ManagerDao;
import com.cuit.boke.dto.ArticleBean;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.entity.Review;
import com.cuit.boke.service.ArticleService;

@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration({"classpath:ssh-spring.xml"})
@Transactional
public class ArticleServiceImplTest {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ManagerDao mangerDao;
	
	@Test
	@Rollback(false)
	public void testUpdate() {
		Article article = articleDao.queryById(Article.class, 1);
		article.setCommentNum(80);
		Manager manager = new Manager();
		manager.setId(30);
		article.setManager(manager);
		try {
			articleService.updateArticle(article);
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
		article.setLabel("生活");
		articleService.saveArticle(article);
	}
	@Test
	@Rollback(false)
	public void testQueryPage(){
		PageBean<Article> pageBean = new PageBean<Article>();
		pageBean.setCurrPage(1);
		pageBean.setPageSize(5);
		pageBean.setOrderBy(PageBean.TIME);
		pageBean.setOrder(PageBean.ASC);
		System.out.println(articleService.recentArticleByPage(pageBean));
	}
	@Test
	@Rollback(false)
	public void testDelete(){
		Article article = articleDao.queryById(Article.class, 2);
		articleService.deleteArticle(article);
	}
	
	@Test
	@Rollback(false)
	public void testQueryArticleByid(){
		ArticleBean articleBean = articleService.queryArticleById(1);
		System.out.println(articleBean.getArticle());
		for(Review review:articleBean.getReviews()){
			System.out.println(review);
		}
	}
}
