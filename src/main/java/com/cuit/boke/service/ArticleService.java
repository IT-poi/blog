package com.cuit.boke.service;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;


public interface ArticleService {
	
	/**
	 * 分页查询文章
	 * @param currPage 当前页
	 * @param pageSize 每页显示的条数
	 * @param order 排序方式
	 * @return 分页实体
	 */
	public PageBean<Article> recentArticleByPage(int currPage, int pageSize, String order);
	
	/**
	 * 保存文章
	 * @param article
	 * @return 保存文章的id
	 */
	public int saveArticle(Article article);

}
