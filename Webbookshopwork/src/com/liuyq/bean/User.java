package com.liuyq.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@Column(name="imgUrl")
	private String imgUrl;
	@Column(name="phone")
	private String phone;
	@Column(name="category")
	private String category;
	@Column(name="email")
	private String email;
	
	
	@OneToMany(mappedBy="user", targetEntity=Orders.class,cascade=CascadeType.ALL)
	private Set<Orders> orderSet = new HashSet<Orders>();
	
	@OneToMany(mappedBy="user", targetEntity=Orders.class,cascade=CascadeType.ALL)
	private Set<Shop> shopSet = new HashSet<Shop>();

	
	public Set<Shop> getShopSet() {
		return shopSet;
	}

	public void setShopSet(Set<Shop> shopSet) {
		this.shopSet = shopSet;
	}

	public String getCategory() {
		return category;
	}

	public Set<Orders> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Orders> orderSet) {
		this.orderSet = orderSet;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
	
}
