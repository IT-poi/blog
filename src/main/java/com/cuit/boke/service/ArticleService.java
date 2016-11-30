package com.cuit.boke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;

@Service
@Transactional
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	/**
	 * 分页查询文章
	 * @param currPage 当前页
	 * @param pageSize 每页显示的条数
	 * @return 分页实体
	 */
	public PageBean<Article> recentArticleByPage(int currPage, int pageSize){
		PageBean<Article> pageBean = new PageBean<Article>();
		//封装当前页数
		pageBean.setCurrPage(currPage);
		//封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		//封装总记录条数
		int totalCount = articleDao.queryTotalCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		Double pageCount = Math.ceil(totalCount/pageSize);
		pageBean.setTotalPage(pageCount.intValue());
		//封装每页显示的数据
		int begin = (currPage - 1)*pageSize;
		System.out.println("---------Service");
		List<Article> list = articleDao.queryByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}
	
	public int saveArticle(Article article){
		return articleDao.insert(article);
	}

}
