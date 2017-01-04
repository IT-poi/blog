package com.cuit.boke.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dao.ReviewDao;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Review;
import com.cuit.boke.service.ReviewService;
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao reviewDao;
	
	@Autowired
	private ArticleDao articleDao;
	
	
	public void deleteReview(int id) {
		reviewDao.delete(reviewDao.queryById(Review.class, id));
	}
	
	public List<Review> showReview(int articleId) {
		return reviewDao.queryByArticleId(articleId);
	}

	/**
	 * 添加评论
	 */
	public void addReviews(int articleId, String parentId, Review review) {
		int pId;
		Article article = articleDao.queryById(Article.class, articleId);
		review.setCreateTime(new Date());
		review.setPortraitURL("");
		review.setArticle(article);
		if(parentId != null){
			pId = Integer.valueOf(parentId).intValue();
			Review parentReview = reviewDao.queryById(Review.class, pId);
			review.setParentReview(parentReview);
			review.setFloor(parentReview.getFloor());
		}else{
			reviewDao.queryByPage(Review.class, 0, 1, PageBean.TIME, PageBean.DESC);
			int i = reviewDao.maxFooler(articleId);
			if(i < 0){
				review.setFloor(1);
			}else {
				review.setFloor(i+1);
			}
		}
		reviewDao.insert(review);
	}
	
	

	public int reviewsCount() {
		return reviewDao.queryCount(Review.class);
	}

	//删除评论
	public void deleteReviews(int id) {
		reviewDao.deleteById(Review.class, id);
	}
	
	//获取文章所有评论
	public List<Review> getReviews(int articleId) {
		return reviewDao.queryByArticleId(articleId);
	}

}

	