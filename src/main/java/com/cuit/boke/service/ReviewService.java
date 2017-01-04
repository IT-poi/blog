package com.cuit.boke.service;

import java.util.List;

import com.cuit.boke.entity.Review;

public interface ReviewService {
	
	
	
	/**
	 * 返回所有评论的总条数
	 * @return
	 */
	public int reviewsCount();
	/**
	 * 删除评论
	 * @param id 评论id
	 */
	public void deleteReview(int id);
	
	/**
	 * 展示文章评论
	 * @param articleId 文章id
	 * @return 所有评论
	 */
	public List<Review> showReview(int articleId);
	
	/**
	 * 新添评论
	 * @param articleId 文章id
	 * @param parentId 父评论id
	 * @param review 评论详情
	 */
	public void addReviews(int articleId, String parentId, Review review);
	
	/**
	 * 删除所选评论
	 * @param id 评论id
	 */
	public void deleteReviews(int id);

	public List<Review> getReviews(int articleId);
}
