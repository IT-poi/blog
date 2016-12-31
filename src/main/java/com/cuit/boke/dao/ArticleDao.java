package com.cuit.boke.dao;

import java.util.List;

import com.cuit.boke.entity.Article;

public interface ArticleDao extends GenericDao<Article, Integer> {
	
	/**
	 * 查询文章的总数
	 * @return 文章记录条数
	 */
	public int queryTotalCount();
	
	/**
	 * 查询某个标签下的文章数量
	 * @return
	 */
	public int queryTotalCountByLabel(String label);
	
	/**
	 * 查询包含某个关键字的文章条数
	 * @param keywords 关键字
	 * @return 文章条数
	 */
	public int queryTotalCountByKeywords(String keywords);
	/**
	 * 通过文章标签查询文章列表，分页查询
	 * @param label 文章标签
	 * @param begin 查询起点
	 * @param pageSize 分页大小（查询的记录条数）
	 * @return 查询结果（文章列表）
	 */
	public List<Article> queryByLabelPage(String label, int begin, int pageSize);
	
	/**
	 * 通过关键字查询文章列表
	 * @param keywords 关键字
	 * @param begin 开始位置
	 * @param pageSize 结束位置
	 * @return 查询结果文章列表
	 */
	public List<Article> queryByKeywordsPage(String keywords, int begin, int pageSize);
}
