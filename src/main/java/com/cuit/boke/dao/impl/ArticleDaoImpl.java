package com.cuit.boke.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.entity.Article;
@Component
public class ArticleDaoImpl implements ArticleDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	 
	
	public Article queryById(Integer id) {
		return null;
	}

	public List<Article> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Article> queryByPage(int begin, int pageSize) {
		System.out.println("daozhele ~------------------");
		String sql = "select * from article limit " + begin +","+ pageSize;
		List<Article> list = (List<Article>) this.getCurrentSession().createSQLQuery(sql).addEntity(Article.class).list();
		return list;
	}

	public Integer insert(Article t) {
		return (Integer)this.getCurrentSession().save(t);
	}

	public Integer update(Article t) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Integer deleteById(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void flush() {
		// TODO Auto-generated method stub
		
	}

	public int queryTotalCount() {
		String sql = "select count(*) from article";
//		List<Long> list = (List<Long>) this.getCurrentSession().createSQLQuery(sql).addEntity(Long.class).list();
//		if(list!=null && list.size()>0){
//			return list.get(0).intValue();
//		}
		
		BigInteger count = (BigInteger) this.getCurrentSession().createSQLQuery(sql).uniqueResult();
		return count.intValue();
	}
	
}
