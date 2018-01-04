package com.liuyq.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyq.bean.Admin;
import com.liuyq.bean.Book;
import com.liuyq.bean.Marketing;
import com.liuyq.bean.Orders;
import com.liuyq.bean.Page;
import com.liuyq.bean.Shop;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;

@Repository
public class AdminDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	/* *************管理员登录*************** */
	public Admin login(String password,String username) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Admin");
		List<Admin> list = query.list();
		for(Admin a : list) {
			
			if(a.getUsername().equals(username) &&a.getPassword().equals(password)) {
				
				return a;
				
			}
			
		}
		return null;
		
	}
	
	/* **********查询所有用户*********** */
	public List<User> findAllUser(Page page){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User");
		List<User> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
		
		
	}
	
	/* **********查询所有管理员*********** */
	public List<Admin> findAllAdmin(Page page){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Admin");
		List<Admin> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
		
	}
	
	/* **************根据ID 查找用户信息*************** */
	public User findUserById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		User user = (User) query.uniqueResult();
		return user;
		
	}
	
	
	
	
	/* *************根据用户ID查询用户订单************ */
	public List<Shop> findShopByUserId(Integer id){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Shop where userId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Shop> list = query.list();
		List<Shop> list1 = new ArrayList<Shop>();
		for(Shop s:list) {
			
			if(s.getState().equals("未发货")) {
				list1.add(s);
			}else {
				
			}
			
		}
		return list1;
		
	}
	
	/* ************删除用户************ */
	public boolean deleteUser(Integer id) {
		
		User user =  findUserById(id);
		Session session = sessionFactory.getCurrentSession();
		session.delete(user);
		
		return true;
	}
	
	/* ************模糊查询用户*********** */
	public List<User> findUserByPoint(String point){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select u from User u where u.id like '%"+point+"%' or u.username like'%"+point+"%' or u.phone like'%"+point+"%'"
				+ "or u.address like'%"+point+"%' or u.email like '%"+point+"%'";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		return list;
		
	}
	
	/* ************模糊查询管理员*********** */
	public List<Admin> findAdminByPoint(String point){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select a from Admin a where a.id like '%"+point+"%' or a.username like'%"+point+"%' or a.phone like'%"+point+"%'"
				+ "or a.address like'%"+point+"%' or a.email like '%"+point+"%'";
		Query query = session.createQuery(hql);
		List<Admin> list = query.list();
		return list;
		
	}
	
	/* **********查看所有书籍********* */
	public List<Book> seeAllBook(Page page){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b";
		Query query = session.createQuery(hql);
		List<Book> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
		
		
	}
	
	/* **************查询所有类别*************** */
	public List<Type> seeAllType(){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Type";
		Query query = session.createQuery(hql);
		List<Type> list = query.list();
		return list;
		
	}
	
	/* *********模糊查询书籍********* */
	public List<Book> findBokkByKey(Integer booktype,String point){
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.typeSet bt where b.id like '%"+point+"%' or b.bookName like'%"+point+"%' or b.author like'%"+point+"%'"
				+ "or b.publish like'%"+point+"%' or bt.type like '%"+point+"%'";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		return list;
	}

	/* ***********根据Id查询书籍*********** */
	public Book findBookById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.typeSet bt where b.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Book book = (Book) query.uniqueResult();
		return book;
		
	}
	
	/* ************修改书籍************ */
	public boolean updateBook(Book book) {
		
		Session session = sessionFactory.getCurrentSession();
		//session.update(book);
		
		return true;
		
	}
	
	/* ************修改书籍************ */
	public boolean updateBookExceptImgurl(Book book) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(book);
		return true;
		
	}
	
	/* *************查看所有订单************** */
	public List<Shop> seeAllShop(Page page){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select s from Shop s join s.order o join s.user";
		Query query = session.createQuery(hql);
		List<Shop> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
		
		
	}
	
	/* ***************查找订单数量*************** */
	//查询订单总数
	public int selectShopAllCount() {
		
		Integer count=0;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) as num from Shop";
		Query query = session.createQuery(hql);
		count = ((Number)query.uniqueResult()).intValue();
		return count;
		
	}
	
	/* ************根据Id查找订单*********** */
	public Shop findShop(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select s from Shop s join s.order o join s.user where s.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Shop shop  = (Shop) query.uniqueResult();
		return shop;
		
	}
	
	/* ************修改订单*********** */
	public boolean updateShop(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Shop set state='已发货' where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
		return true;
		
	}
	
	/* ***************查找订单数量*************** */
	//根据状态查询订单总数
	public int selectShopAllCountByState(String state) {
		
		Integer count=0;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) as num from Shop where state=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, state);
		count = ((Number)query.uniqueResult()).intValue();
		return count;
		
	}
	
	
	/* ************根据状态查找订单*********** */
	public List<Shop> findShopByState(String state,Page page) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select s from Shop s join s.order o join s.user where s.state=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, state);
		List<Shop> shoplist = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return shoplist;
		
	}
	
	/* ************添加图书*********** */
	public boolean insertBook(Book book) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(book);
		return true;
		
	}
	
	/* *********根据type查找Type********* */
	public Type findType(String type){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Type where type=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		Type t = (Type) query.uniqueResult();
		return t;
		
	}
	
	/* *********根据type查找Marketing********* */
	public Marketing findMarketingByType(String type){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Marketing where type=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		Marketing m = (Marketing) query.uniqueResult();
		return m;
		
	}
	
	/* **************删除商品************* */
	public boolean deleteBookById(Integer id) {
		
		Book book = findBookById(id);
		book.getTypeSet().clear();
		book.getMarkSet().clear();
		Session session = sessionFactory.getCurrentSession();
		session.delete(book);
		return true;
		
	}
	
	/* ***********添加分类************ */
	public boolean insertType(Type type) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(type);
		return true;
		
	}
	
	/* **********查看所有的分类********** */
	public List<Type> findAllType(){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Type";
		Query query = session.createQuery(hql);
		List<Type> list = query.list();
		return list;
		
	}
	
	/* **********根据Id查找Type*********** */
	public Type findTypeById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Type where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Type type = (Type) query.uniqueResult();
		return type;
		
	}
	
	/* *************修改Type************** */
	public boolean updateType(Type type) {
		
		Session session = sessionFactory.getCurrentSession();
		session.update(type);
		return true;
	}
	
	/* *********删除Type******** */
	public boolean deleteType(Integer id) {
		
		Type type = findTypeById(id);
		Session session = sessionFactory.getCurrentSession();
		session.delete(type);
		return true;
	}
	
	/* ***********模糊查询订单*********** */
	public List<Shop> findShopByKey(String point){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select s from Shop s join s.user su join s.order so where s.id like '%"+point+"%' or s.user.username like'%"+point+"%' or so.book.bookName like'%"+point+"%'"
				+ "or so.book.author like'%"+point+"%' ";
		Query query = session.createQuery(hql);
		List<Shop> list = query.list();
		return list;
		
	} 
	
	/* ************模糊查询类别************ */
	public List<Type> findTypeByPoint(String point){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Type where type  like '%"+point+"%' ";
		Query query = session.createQuery(hql);
		List<Type> list = query.list();
		return list;
		
	}
	
	/* **************删除order*************** */
	public boolean deleteOrder(Orders order) {
		
		Session session = sessionFactory.getCurrentSession();
		session.delete(order);
		return true;
		
	}
	
	/* *************删除订单************ */
	public boolean deleteShop(Integer id) {
		
		Shop shop = findShop(id);
		Orders order = shop.getOrder();
		Session session = sessionFactory.getCurrentSession();
		String hql="delete Shop where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		query.executeUpdate();
		//deleteOrder(order);
		return true;
		
	}
	
	/* **********查找书籍总数********* */
	public Long sumBook() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql="select count(id) from Book";
		Query query = session.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count;
		
	}
	
	/* *************查找用户总数************** */
	public Long sumUser() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql="select count(id) from User";
		Query query = session.createQuery(hql);
		Long count = (Long) query.uniqueResult();
		return count;
		
	}
	
	/* **************查找未处理订单总数*************** */
	public Long sumShop() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql="select count(id) from Shop where state=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, "未发货");
		Long count = (Long) query.uniqueResult();
		return count;
		
	}
	
	/* ***************根据ID查找管理员***************** */
	public Admin seeSinglAdmin(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql="select a from Admin a where a.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Admin admin = (Admin) query.uniqueResult();
		return admin;
		
	}
	
	/* ***********修改管理员信息********** */
	public Admin updateAdmin(Admin admin) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Admin set username=?,password=?,phone=?,imgUrl=?,address=?,email=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, admin.getUsername());
		query.setParameter(1, admin.getPassword());
		query.setParameter(2, admin.getPhone());
		query.setParameter(3, admin.getImgUrl());
		query.setParameter(4, admin.getAddress());
		query.setParameter(5, admin.getEmail());
		query.setParameter(6, admin.getId());
		query.executeUpdate();
		
		Session session1 = sessionFactory.getCurrentSession();
		Query query1 = session1.createQuery("from Admin where id=?");
		query1.setParameter(0, admin.getId());
		Admin admin1 = (Admin) query1.uniqueResult();
		
		return admin1;
		
	}
	
	/* ************删除管理员*********** */
	public boolean deleteAdmin(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Admin where id=?");
		query.setParameter(0, id);
		query.executeUpdate();
		
		return true;
		
	}
	
	/* ************添加管理员********** */
	public boolean insertAdmin(Admin admin) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(admin);
		
		return true;
		
	}
	
	/* ***************根据ID查找管理员*************** */
	public Admin findAdminById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Admin where id=?");
		query.setParameter(0, id);
		Admin admin = (Admin) query.uniqueResult();
		
		return admin;
		
	}
	
	/* ***********查找ID最大的管理员*********** */
	public Admin findMaxIdAdmin() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select max(id) from Admin");
		Integer id = (Integer) query.uniqueResult();
		
		Admin admin = findAdminById(id);
		return admin;
		
	}
	
	/* ************查找所有的标签************* */
	public List<Marketing> findAllMarket(){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Marketing");
		List<Marketing> list = query.list();
		return list;
		
	}
	
	/* *************根据Id查找标签************** */
	public Marketing findMarketById(Integer id){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Marketing where id=?");
		query.setParameter(0, id);
		Marketing m = (Marketing) query.uniqueResult();
		return m;
		
	}
	
	//查询图书总数
		public int selectAllCount() {
			Integer count=0;
			Session session = sessionFactory.getCurrentSession();
			String hql = "select count(*) as num from Book";
			Query query = session.createQuery(hql);
			count = ((Number)query.uniqueResult()).intValue();
			return count;
		}

	//查询用户总数
		public int selectUserAllCount() {
			
			Integer count=0;
			Session session = sessionFactory.getCurrentSession();
			String hql = "select count(*) as num from User";
			Query query = session.createQuery(hql);
			count = ((Number)query.uniqueResult()).intValue();
			return count;
			
		}
	
		//查询所有类别
		public List<Type> getAllType(Page page){
			Session session = sessionFactory.getCurrentSession();
			String hql = "from Type";
			Query query = session.createQuery(hql);
			List<Type> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
			return list;
		}

		//查询类别总数
				public int selectTypeAllCount() {
					
					Integer count=0;
					Session session = sessionFactory.getCurrentSession();
					String hql = "select count(*) as num from Type";
					Query query = session.createQuery(hql);
					count = ((Number)query.uniqueResult()).intValue();
					return count;
					
				}
				
				//查询管理员总数
				public int selectAdminAllCount() {
					
					Integer count=0;
					Session session = sessionFactory.getCurrentSession();
					String hql = "select count(*) as num from Admin";
					Query query = session.createQuery(hql);
					count = ((Number)query.uniqueResult()).intValue();
					return count;
					
				}
				
}
