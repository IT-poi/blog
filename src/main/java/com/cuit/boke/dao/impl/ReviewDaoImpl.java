package com.cuit.boke.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuit.boke.dao.ReviewDao;
import com.cuit.boke.entity.Review;
@Repository
public class ReviewDaoImpl extends GenericDaoImpl<Review, Integer> implements ReviewDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Review> queryByArticleId(int id) {
		String sql = "select * from review where article_id="+id;
		return sessionFactory.getCurrentSession().createSQLQuery(sql)
				.addEntity(Review.class).list();
	}
	
	public void delete(Review review) {
		sessionFactory.getCurrentSession().delete(review);
	}
}
