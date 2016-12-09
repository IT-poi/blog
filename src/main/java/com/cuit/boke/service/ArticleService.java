package com.cuit.boke.service;

import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.exception.DeleteFailException;
import com.cuit.boke.exception.SaveFailException;
import com.cuit.boke.exception.UnknowException;
import com.cuit.boke.exception.UpdateFailException;


public interface ArticleService {
	
	/**
	 * 分页查询文章
	 * @param currPage 当前页
	 * @param pageSize 每页显示的条数
	 * @param order 排序方式
	 * @return 分页实体
	 */
	public PageBean<Article> recentArticleByPage(PageBean<Article> article)
	throws UnknowException;
	
	/**
	 * 保存文章
	 * @param article
	 * @return 保存文章的id
	 */
	public int saveArticle(Article article) throws SaveFailException;
	
	/**
	 * 修改文章
	 * @param article 修改后的文章实体
	 */
	public void updateArticle(Article article) throws UpdateFailException;

	/**
	 * 删除文章
	 * @param article 要删除的文章
	 * @throws DeleteFailException 删除失败异常
	 */
	public void deleteArticle(Article article) throws DeleteFailException;
}
