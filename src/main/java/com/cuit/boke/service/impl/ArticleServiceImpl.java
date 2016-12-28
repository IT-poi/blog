package com.cuit.boke.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cuit.boke.dao.ArticleDao;
import com.cuit.boke.dao.ReviewDao;
import com.cuit.boke.dao.impl.ManagerDaoImpl;
import com.cuit.boke.dto.ArticleBean;
import com.cuit.boke.dto.PageBean;
import com.cuit.boke.entity.Article;
import com.cuit.boke.entity.Manager;
import com.cuit.boke.entity.Review;
import com.cuit.boke.exception.DeleteFailException;
import com.cuit.boke.exception.SaveFailException;
import com.cuit.boke.exception.UnknowException;
import com.cuit.boke.exception.UpdateFailException;
import com.cuit.boke.service.ArticleService;
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private ManagerDaoImpl managerDaoImpl;
	@Autowired
	private ReviewDao reviewDao;
	
	public PageBean<Article> recentArticleByPage(PageBean<Article> pageBean) throws UnknowException {
		if (pageBean == null) {
			throw new UnknowException("pageBean为空");
		}
		//封装当前页数
//		pageBean.setCurrPage(pageBean.getCurrPage());
		//封装每页显示的记录数
//		pageBean.setPageSize(pageBean.getPageSize());
		//封装总记录条数
		int totalCount = articleDao.queryTotalCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
//		Double pageCount = Math.ceil(totalCount / pageSize);
		int pageCount = (totalCount + pageBean.getPageSize() -1) / pageBean.getPageSize();
		pageBean.setTotalPage(pageCount);
		if (pageBean.getCurrPage() > pageCount) {
			throw new UnknowException("请求页面参数错误");
		}
		//封装每页显示的数据
		int begin = (pageBean.getCurrPage() - 1)*pageBean.getPageSize();
		List<Article> list = articleDao.queryByPage(Article.class, begin, pageBean.getPageSize(), pageBean.getOrderBy(), pageBean.getOrder());
		pageBean.setList(list);
		return pageBean;
	}

	public int saveArticle(Article article) throws SaveFailException{
		int articleId = -1;
		try {
			Manager manager = managerDaoImpl.queryById(Manager.class,article.getManager().getId());
			article.setManager(manager);
			articleId = articleDao.insert(article);
		} catch (Exception e) {
			throw new SaveFailException("保存失败!");
		}
		return articleId;
	}

	public void updateArticle(Article article) throws UpdateFailException {
		try {
			articleDao.update(article);
		} catch (Exception e) {
			throw new UpdateFailException("修改失败！");
		}
	}

	public void deleteArticle(Article article) throws DeleteFailException {
		try {
			articleDao.deleteById(Article.class, article.getId());
		} catch (Exception e) {
			throw new DeleteFailException("删除失败");
		}
	}
	
	public ArticleBean queryArticleById(int articleId) {
		Article article = articleDao.queryById(Article.class, articleId);
		List<Review> reviews = reviewDao.queryByArticleId(articleId);
		ArticleBean articleBean = new ArticleBean(article, reviews);
		return articleBean;
	}
	public PageBean<Article> ArticlePageByLable(String label, PageBean<Article> pageBean)
			throws UnknowException {
		if (pageBean == null) {
			throw new UnknowException("pageBean为空");
		}
		//封装当前页数
//		pageBean.setCurrPage(pageBean.getCurrPage());
		//封装每页显示的记录数
//		pageBean.setPageSize(pageBean.getPageSize());
		//封装总记录条数
		int totalCount = articleDao.queryTotalCountByLabel(label);
		pageBean.setTotalCount(totalCount);
		//封装总页数
//		Double pageCount = Math.ceil(totalCount / pageSize);
		int pageCount = (totalCount + pageBean.getPageSize() -1) / pageBean.getPageSize();
		pageBean.setTotalPage(pageCount);
		if (pageBean.getCurrPage() > pageCount) {
			throw new UnknowException("请求页面参数错误");
		}
		//封装每页显示的数据
		int begin = (pageBean.getCurrPage() - 1)*pageBean.getPageSize();
		List<Article> list = articleDao.queryByLabelPage(label, begin, pageBean.getPageSize());
		pageBean.setList(list);
		return pageBean;
	}
}
