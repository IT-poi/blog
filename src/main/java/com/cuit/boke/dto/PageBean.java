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
	
	public static final String VIEW = "page_view"; //按文章浏览量排序
	
	public static final String TIME = "create_time"; //按文章发布时间排序
	
	public static final String ASC = "ASC"; //升序
	
	public static final String DESC = "DESC"; //降序
	
	private int currPage; //当前页数
	
	private int pageSize; //每页显示记录数
	
	private int totalCount; //总记录数
	
	private int totalPage; //总页数
	
	private String orderBy; //排序方式，可以通过最新和最热排序
	
	private String order; //排序方式，升序或者降序
	
	private List<T> list; //每页要显示的数据
	

	public int getCurrPage() {
		return currPage;
	}
	
	/**
	 * 当前页至少为1
	 * @param currPage
	 */
	public void setCurrPage(int currPage) {
		if (currPage<=0) {
			this.currPage = 1;
		}else {
			this.currPage = currPage;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	/**
	 * pageSize至少为1
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			this.pageSize = 1;
		}else {
			this.pageSize = pageSize;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * 总记录条数至少为0
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if(totalCount<0){
			this.totalCount = 0;
		}else {
			this.totalCount = totalCount;
		}
	}

	public int getTotalPage() {
		return totalPage;
	}
	
	/**
	 * 总页数最小为1
	 * @param totalPage
	 */
	public void setTotalPage(int totalPage) {
		if (totalPage<=0) {
			this.totalPage = 1;
		}else {
			this.totalPage = totalPage;
		}
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getOrder() {
		return order;
	}

	/**
	 * 默认按照最新发表排序
	 * @param order
	 */
	public void setOrder(String order) {
		if(order == null){
			order = DESC;
			return;
		}else if(order.isEmpty()){
			order = DESC;
			return;
		}
		this.order = order;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		if(orderBy == null){
			orderBy = TIME;
			return;
		}else if(orderBy.isEmpty()){
			orderBy = TIME;
			return;
		}
		this.orderBy = orderBy;
	}

	@Override
	public String toString() {
		return "PageBean [currPage=" + currPage + ", pageSize=" + pageSize
				+ ", totalCount=" + totalCount + ", totalPage=" + totalPage
				+ ", orderBy=" + orderBy + ", order=" + order + ", list="
				+ list + "]";
	}
	
}
