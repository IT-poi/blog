package com.cuit.boke.dao.impl;



import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Review;


@RunWith(SpringJUnit4ClassRunner.class)
//junit 整合 spring 单元测试框架 导入配置文件，支持通配符
@ContextConfiguration({"classpath:ssh-spring.xml"})
public class ReviewDaoImplTest {
	@Autowired
	private ReviewDaoImpl reviewDaoImpl;
	@Autowired
	private ArticleDaoImpl articleDaoImpl;
	
	
	@Test
	@Rollback(false)
	@Transactional
	public void testInsert() {
		Review review = new Review();
		review.setLevel(3);
		review.setName("44444");
		review.setPortraitURL("4444");
		review.setContent("4444");
		review.setCteateTime(new Date());
//		review.setParentReview(reviewDaoImpl.queryById(Review.class, 7));
		review.setParentReview(null);
		review.setArticle(articleDaoImpl.queryById(Article.class, 1));
		reviewDaoImpl.insert(review);
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testQueryById(){
		Review review = reviewDaoImpl.queryById(Review.class,1);
		System.out.println(review.getName());
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testUpdate(){
		Review review = reviewDaoImpl.queryById(Review.class,1);
		review.setName("tingfenglai");
		reviewDaoImpl.update(review);
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testQueryAll(){
		List<Review> reviews = reviewDaoImpl.queryAll(Review.class);
		if(!reviews.isEmpty()){
			for(Review review:reviews){
				System.out.println(review.getContent());
			}
		}
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testDelete(){
		reviewDaoImpl.delete(reviewDaoImpl.queryById(Review.class, 4));
	}
	
	@Test
	@Rollback(false)
	@Transactional
	public void testArticle(){
		Article article = articleDaoImpl.queryById(Article.class, 1);
		System.out.println(article.getContent());
	}

}
