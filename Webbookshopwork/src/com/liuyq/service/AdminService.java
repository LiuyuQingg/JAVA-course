package com.liuyq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuyq.bean.Admin;
import com.liuyq.bean.Book;
import com.liuyq.bean.Marketing;
import com.liuyq.bean.Page;
import com.liuyq.bean.Shop;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;
import com.liuyq.dao.AdminDao;


@Service
@Transactional
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	public Admin login(String password,String username) {
		
		return adminDao.login(password, username);
		
	}
	
	public List<User> findAllUser(Page page){
		
		return adminDao.findAllUser(page);
		
	}
	
	public List<Admin> findAllAdmin(Page page){
		
		return adminDao.findAllAdmin(page);
		
	}
	
	public Admin seeSinglAdmin(Integer id) {
		
		return adminDao.seeSinglAdmin(id);
		
	}
	
	public User findUserById(Integer id) {
		
		return adminDao.findUserById(id);
		
	}
	
	public boolean deleteUser(Integer id) {
		
		return adminDao.deleteUser(id);
		
	}
	
	public List<Shop> findShopByUserId(Integer id){
		
		return adminDao.findShopByUserId(id);
		
	}
	
	public List<User> findUserByPoint(String point){
		
		return adminDao.findUserByPoint(point);
		
	}
	
	public List<Book> seeAllBook(Page page){
		
		return adminDao.seeAllBook(page);
		
	}
	
	public List<Type> seeAllType(){
		
		return adminDao.seeAllType();
		
	}
	
	public List<Book> findBokkByKey(Integer booktype,String point){
		
		return adminDao.findBokkByKey(booktype, point);
		
	}
	
	public Book findBookById(Integer id) {
		
		return adminDao.findBookById(id);
		
	}
	
	public boolean updateBook(Book book) {
		
		return adminDao.updateBook(book);
		
	}
	
	public boolean updateBookExceptImgurl(Book book) {
		
		return adminDao.updateBookExceptImgurl(book);
		
	}
	
	public List<Shop> seeAllShop(Page page){
		
		return adminDao.seeAllShop(page);
		
	}
	
	public Shop findShop(Integer id) {
		
		return adminDao.findShop(id);
		
	}
	
	public boolean updateShop(Integer id) {
		
		return adminDao.updateShop(id);
		
	}
	
	public List<Shop> findShopByState(String state,Page page){
		
		return adminDao.findShopByState(state,page);
		
	}
	
	public int selectShopAllCountByState(String state) {
		
		return adminDao.selectShopAllCountByState(state);
		
	}
	
	public boolean insertBook(Book book) {
		
		return adminDao.insertBook(book);
		
	}
	
	public Type findType(String type) {
		
		return adminDao.findType(type);
		
	}
	
	public boolean deleteBookById(Integer id) {
		
		return adminDao.deleteBookById(id);
		
	}
	
	public boolean insertType(Type type) {
		
		return adminDao.insertType(type);
		
	}
	
	public List<Type> findAllType(){
		
		return adminDao.findAllType();
		
	}
	
	public Type findTypeById(Integer id) {
		
		return adminDao.findTypeById(id);
		
	}
	
	public boolean updateType(Type type) {
		
		return adminDao.updateType(type);
		
	}
	
	public boolean deleteType(Integer id) {
		
		return adminDao.deleteType(id);
		
	}
	
	public List<Shop> findShopByKey(String point){
		
		return adminDao.findShopByKey(point);
		
	}
	
	public List<Type> findTypeByPoint(String point){
		
		return adminDao.findTypeByPoint(point);
		
	}
	
	public boolean deleteShop(Integer id) {
		
		return adminDao.deleteShop(id);
		
	}
	
	public Long sumBook() {
		
		return adminDao.sumBook();
		
	}
	
	public Long sumUser() {
		
		return adminDao.sumUser();
		
	}
	
	public Long sumShop() {
		
		return adminDao.sumShop();
		
	}
	
	public Admin updateAdmin(Admin admin) {
		
		return adminDao.updateAdmin(admin);
		
	}
	
	public boolean deleteAdmin(Integer id) {
		
		return adminDao.deleteAdmin(id);
		
	}
	
	public boolean insertAdmin(Admin admin) {
		
		return adminDao.insertAdmin(admin);
		
	}
	
	public Admin findAdminById(Integer id) {
		
		return adminDao.findAdminById(id);
		
	}
	
	public Admin findMaxIdAdmin() {
		
		return adminDao.findMaxIdAdmin();
		
	}
	
	public List<Marketing> findAllMarket(){
		
		return adminDao.findAllMarket();
		
	}
	
	public List<Admin> findAdminByPoint(String point){
		
		return adminDao.findAdminByPoint(point);
		
	}
	
	public Marketing findMarketById(Integer id) {
		
		return adminDao.findMarketById(id);
		
	}
	
	public Marketing findMarketingByType(String type) {
		
		return adminDao.findMarketingByType(type);
		
	}
	
	public int selectAllCount() {
		
		return adminDao.selectAllCount();
		
	}
	
	public int selectUserAllCount() {
		
		return adminDao.selectUserAllCount();
		
	}
	
	public List<Type> getAllType(Page page){
		
		return adminDao.getAllType(page);
		
	}
	
	public int selectTypeAllCount() {
		
		return adminDao.selectTypeAllCount();
		
	}

	public int selectShopAllCount() {
		
		return adminDao.selectShopAllCount();
		
	}
	
	public int selectAdminAllCount() {
		
		return adminDao.selectAdminAllCount();
		
	}
	
	
	
}
