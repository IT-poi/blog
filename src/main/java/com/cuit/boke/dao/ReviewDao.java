package com.cuit.boke.dao;



import java.util.List;

import com.cuit.boke.entity.Review;

public interface ReviewDao extends GenericDao<Review, Integer>{
	
	/**
	 * 查询文章所有的评论
	 * @param id 文章id
	 */
	public List<Review> queryByArticleId(int id);
	
	/**
	 * 删除评论(会自动删除该条评论的子评论)
	 * @param review
	 */
	public void delete(Review review);
	
	
}
