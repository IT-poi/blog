package com.cuit.boke.service;

import com.cuit.boke.dto.ArticleBean;
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
	
	/**
	 * 通过文章id，查询文章与评论
	 * @param articleId
	 * @return
	 */
	public ArticleBean queryArticleById(int articleId);
	
	/**
	 * 通过文章标签（分类）查询文章列表 
	 * @param lable 文章标签
	 * @param article 封装了分页参数
	 * @return 查询结果
	 * @throws UnknowException 可能会出现的异常
	 */
	public PageBean<Article> articlePageByLable(String lable, PageBean<Article> article)
			throws UnknowException;
	
	/**
	 * 通过关键字查询文章列表（关键字可以是文章标题，标签，内容中的文字）
	 * @param keywords 关键字
	 * @param article 内含分页参数
	 * @return 查询结果
	 * @throws UnknowException 可能会出现的异常
	 */
	public PageBean<Article> articlePageByKeywords(String keywords, PageBean<Article> article)
			throws UnknowException;
	
}
