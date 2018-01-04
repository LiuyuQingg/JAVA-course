package com.liuyq.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="type")
public class Type {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="type")
	private String type;
	@ManyToMany(mappedBy="typeSet",fetch=FetchType.EAGER)
	private Set<Book> bookSet = new HashSet<Book>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<Book> getBookSet() {
		return bookSet;
	}
	public void setBookSet(Set<Book> bookSet) {
		this.bookSet = bookSet;
	}
	
	
	

}
