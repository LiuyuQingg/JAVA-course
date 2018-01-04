package com.liuyq.bean;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="bookName")
	private String bookName;
	@Column(name="price")
	private Double price;
	@Column(name="author")
	private String author;
	@Column(name="publish")
	private String publish;
	@Column(name="description")
	private String description;
	@Column(name="imgUrl")
	private String imgUrl;
	@Column(name="content")
	private String content;
	@Column(name="authorDes")
	private String authorDes;
	@Column(name="recommendation")
	private String recommendation;
	@Column(name="oldPrice")
	private Double oldPrice;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="bookmarketing",joinColumns=@JoinColumn(name="bId"),inverseJoinColumns=@JoinColumn(name="mId"))
	private Set<Marketing> markSet = new HashSet<Marketing>();
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="booktype",joinColumns=@JoinColumn(name="bookId"),inverseJoinColumns=@JoinColumn(name="typeId"))
	private Set<Type> typeSet = new HashSet<Type>();
	@OneToMany(mappedBy="book", targetEntity=Orders.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<Orders> orderSet = new HashSet<Orders>();
	
	
	public Set<Type> getTypeSet() {
		return typeSet;
	}
	public void setTypeSet(Set<Type> typeSet) {
		this.typeSet = typeSet;
	}
	public Set<Marketing> getMarkSet() {
		return markSet;
	}
	public void setMarkSet(Set<Marketing> markSet) {
		this.markSet = markSet;
	}
	public String getContent() {
		return content;
	}
	public Set<Orders> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Orders> orderSet) {
		this.orderSet = orderSet;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getAuthorDes() {
		return authorDes;
	}
	public void setAuthorDes(String authorDes) {
		this.authorDes = authorDes;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublish() {
		return publish;
	}
	public void setPublish(String publish) {
		this.publish = publish;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
