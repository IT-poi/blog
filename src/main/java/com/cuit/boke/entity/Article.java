package com.cuit.boke.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "article")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article {
	// 文章主键
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 文章标题
	@Column(nullable = false)
	private String title;

	// 文章概要
	@Column(nullable = false)
	private String brief;

	// 文章内容
	@Column(nullable = false, length=10000)
	private String content;

	// 图片URL,默认地址为"/"
	@Column(name="img_url",nullable=false)
	private String imgURL;

	// 文章上传时间(文章第一次上传的时间)
	@Column(nullable = false, name = "create_time")
	private Date createTime;

	// 文章浏览量
	@Column(name = "page_view", columnDefinition = "int default 0", nullable = false)
	private int pageView;

	// 文章评论数
	@Column(name = "comment_num", columnDefinition = "int default 0", nullable = false)
	private int commentNum;

	// 文章的标签，以分隔符(,)分开
	@Column
	private String label;
	
	//是否置顶，默认为否
	@Column(name = "stick", columnDefinition = "boolean default false", nullable = false)
	private boolean isStick;

	// 该文章对应的博主id
	@ManyToOne(targetEntity = Manager.class)
	@JoinColumn(name = "manager_id",nullable=false)
	private Manager manager;

	// 该文章对应的评论
	@OneToMany(targetEntity = Review.class, mappedBy = "article",
			cascade=CascadeType.REMOVE,fetch=FetchType.LAZY)
	private Set<Review> reviews = new HashSet<Review>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		if(imgURL.isEmpty()){
			imgURL = "/pictrue/1.png";
		}
		this.imgURL = imgURL;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getPageView() {
		return pageView;
	}

	public void setPageView(int pageView) {
		this.pageView = pageView;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	
	public boolean isStick() {
		return isStick;
	}

	public void setStick(boolean isStick) {
		this.isStick = isStick;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", brief=" + brief
				+ ", content=" + content + ", imgURL=" + imgURL
				+ ", createTime=" + ", pageView=" + pageView
				+ ", commentNum=" + commentNum + ", label=" + label
				+ ", manager="  + ", reviews=" + "]";
	}

	

}
