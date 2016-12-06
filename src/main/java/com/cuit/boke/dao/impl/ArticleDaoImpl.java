package com.cuit.boke.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
@Repository
public class ArticleDaoImpl extends GenericDaoImpl<Article, Integer> implements ArticleDao{
	
	@Autowired
	private SessionFactory sessionFactory;


	public List<Article> queryByPage(int begin, int pageSize, String order) {
		String sql = "select * from article order by " + order + " DESC limit " + begin + "," + pageSize;
		@SuppressWarnings("unchecked")
		List<Article> list = (List<Article>) sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Article.class).list();
		return list;
	}



	public int queryTotalCount() {
		String sql = "select count(*) from article";
//		List<Long> list = (List<Long>) this.getCurrentSession().createSQLQuery(sql).addEntity(Long.class).list();
//		if(list!=null && list.size()>0){
//			return list.get(0).intValue();
//		}
		
		BigInteger count = (BigInteger) sessionFactory.getCurrentSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}



	public List<Article> queryByLabelPage(String label, int begin, int pageSize) {
		//查询包含当前标签的文章列表
		String sql = "select * from article where label like '%"+ label + "%' limit " + begin + "," 
				+ pageSize + " order by " + PageBean.TIME + " DESC";
		@SuppressWarnings("unchecked")
		List<Article> list = (List<Article>) sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(Article.class).list();
		return list;
	}
	
}
