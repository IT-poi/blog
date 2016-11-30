package com.cuit.boke.dto;

import java.util.List;

/**
 * @author inori
 *	
 *	分装分页的类
 *
 * @param <T> 封装的数据类型，用泛型约束
 */
public class PageBean<T> {
	
	private int currPage; //当前页数
	
	private int pageSize; //每页显示记录数
	
	private int totalCount; //总记录数
	
	private int totalPage; //总页数
	
	private List<T> list; //每页要显示的数据

	
	public int getCurrPage() {
		return currPage;
	}

	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
	
}
