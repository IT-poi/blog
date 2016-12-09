package com.cuit.boke.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuit.boke.dao.ReviewDao;
import com.cuit.boke.entity.Review;
import com.cuit.boke.service.ReviewService;
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private ReviewDao reviewDao;
	
	
	public void deleteReview(int id) {
		reviewDao.delete(reviewDao.queryById(Review.class, id));
	}
	
	public List<Review> showReview(int articleId) {
		return reviewDao.queryByArticleId(articleId);
	}

}
