package com.liuyq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuyq.bean.Book;
import com.liuyq.bean.Orders;
import com.liuyq.bean.Pages;
import com.liuyq.bean.Shop;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;
import com.liuyq.dao.BookDao;



@Service
@Transactional
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	 public List<Book> findAll(Pages page){
		 List<Book> list = bookDao.selectAll(page);
		 return list;
	 }
	 
	 public List<Book> recommendBook(){
		 
		 List<Book> list = bookDao.recommendBook();
		 return list;
		 
	 }
	 
	 public List<Book> mainPush(){
		 
		 List<Book> list = bookDao.mainPush();
		 return list;
		 
	 }
	 
	 public List<Book> newBook(){
		 
		 List<Book> list = bookDao.newBook();
		 return list;
		 
	 }	
	 
	 public Book seeSingleBook(Integer id) {
		 
		 Book book = bookDao.seeSingleBook(id);
		 return book;
		 
	 }
	 
	 public List<Book> foundBook(String tag){
		 
		 List<Book> list = bookDao.foundBook(tag);
		 return list;
		 
	 }
	 
	 public List<Book> seeTypeBook(String type,Pages page){
		 
		 List<Book> list = bookDao.seeTypeBook(type,page);
		 return list;
		 
	 }
	 
 public List<Book> seeMarkBook(String type,Pages page){
		 
		 List<Book> list = bookDao.seeMarkBook(type,page);
		 return list;
		 
	 }
	 
 public List<Book> newSeeTypeBook(String type){
	 
	 List<Book> list = bookDao.newSeeTypeBook(type);
	 return list;
	 
 }
 
 public boolean addChat(Orders order) {
	 
	 boolean result = bookDao.addChat(order);
	 return result;
	 
 }
 
 public List<Orders> seeChat(Integer id){
	 
	 List<Orders> list = bookDao.seeChat(id);
	 
	 return list;
	 
 }
 
 	public boolean shopping(Shop shop) {
 		
 		boolean result = bookDao.shopping(shop);
 		return result;
 		
 	}
 	
public Shop shopping1(Shop shop) {
 		
 		return bookDao.shopping1(shop);
 		
 	}
 	
 	public Orders seeShop(Integer id) {
 		
 		return bookDao.seeShop(id);
 		
 	}
 	
 	public List<Shop> seeShopping(Integer userId){
 		
 		List<Shop> list = bookDao.seeShopping(userId);
 		return list;
 		
 	}
 	public Orders upOrderById(Integer id,String address,Integer quentity) {
 		
 		return bookDao.upOrderById(id, address,quentity);
 		
 	}
 	
 	public boolean upOrder(Orders order) {
 		
 		return bookDao.upOrder(order);
 		
 	}
 	
 	public List<Book> findBookByType(String type){
 		
 		return bookDao.findBookByType(type);
 		
 	}
 	
 	public Book findBookById(Integer id) {
 		
 		return bookDao.findBookById(id);
 		
 	}
 	
 	public User findUserById(Integer id) {
 		
 		return bookDao.findUserById(id);
 		
 	}
 	
 	public List<Type> findAllType(){
 		
 		return bookDao.findAllType();
 		
 	}
 	
 	public Orders saveOrder(Orders order) {
 		
 		return bookDao.saveOrder(order);
 		
 	}
 	
 	public boolean deleteOrder(Integer id) {
 		
 		return bookDao.deleteOrder(id);
 		
 	}
 	
 	public boolean deleteAllChat(Integer userId) {
 		
 		return bookDao.deleteAllChat(userId);
 		
 	}
 	
 	public boolean deleteShop(Integer id) {
 		
 		return bookDao.deleteShop(id);
 		
 	}
 
 	public Orders createShop(Integer bookId,Integer quentity,User user) {
 		
 		return bookDao.createShop(bookId, quentity, user);
 		
 	}
 	
 	public boolean clearShop(Integer id) {
 		
 		return bookDao.clearShop(id);
 		
 	}
 	
 	public boolean usernameIsUsered(String username) {
 		
 		return bookDao.usernameIsUsered(username);
 		
 	}
 	
 	public Orders selectOrderById(Integer id) {
 		
 		return bookDao.selectOrderById(id);
 		
 	}
 	
	public User updateUser(Integer id,String address) {
		
		return bookDao.updateUser(id, address);
		
	}
 	
	public boolean updateOrderQ(Integer quentity,Integer id) {
		
		return bookDao.updateOrderQ(quentity, id);
		
	}
	
	public int selectBookAllCount() {
		
		return bookDao.selectBookAllCount();
		
	}
	
	public int selectTypeBookAllCount(String type) {
		
		return bookDao.selectTypeBookAllCount(type);
		
	}
	
	public int selectMarkBookAllCount(String type) {
		
		return bookDao.selectMarkBookAllCount(type);
		
	}
	
}
