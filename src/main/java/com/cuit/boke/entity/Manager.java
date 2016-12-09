package com.cuit.boke.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "manager")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Manager implements Serializable{
	private static final long serialVersionUID = 1L;

	// 管理员id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// 管理员登录用户名
	@Column(nullable = false,length=20)
	private String number;

	// 管理员登录的密码
	@Column(nullable = false,length=100)
	private String password;

	// 个人说明
	@Column(length=1000)
	private String elucidation;

	// 博主名称
	@Column
	private String name;

	// 博主创建时间
	@Column(name = "create_time",nullable = false)
	private Date createTime;

	// 该博主对应的文章
	@OneToMany(mappedBy = "manager", targetEntity = Article.class,fetch=FetchType.LAZY)
	private Set<Article> articles = new HashSet<Article>();

	/**
	 * 管理员相关个人信息说明,可以自行增添。
	 */

	// 地址
	@Column
	private String address;

	// 职业
	@Column
	private String perfessional;

	// 个性签名
	@Column
	private String signature;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getElucidation() {
		return elucidation;
	}

	public void setElucidation(String elucidation) {
		this.elucidation = elucidation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		System.out.println("hello");
		this.createTime = createTime;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerfessional() {
		return perfessional;
	}

	public void setPerfessional(String perfessional) {
		this.perfessional = perfessional;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", number=" + number + ", password="
				+ password + ", elucidation=" + elucidation + ", name=" + name
				+ ", createTime=" + ", articles=" 
				+ ", address=" + address + ", perfessional=" + perfessional
				+ ", signature=" + signature + "]";
	}
	
	
	
}
