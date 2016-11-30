package com.cuit.boke.dao;

import com.cuit.boke.entity.Article;

public interface ArticleDao extends GenericDao<Article, Integer> {
	
	/**
	 * 查询文章的总数
	 * @return 文章记录条数
	 */
	public int queryTotalCount();
}
