package com.liuyq.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyq.bean.Book;
import com.liuyq.bean.Orders;
import com.liuyq.bean.Pages;
import com.liuyq.bean.Shop;
import com.liuyq.bean.Type;
import com.liuyq.bean.User;



@Repository
public class BookDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Book> selectAll(Pages page){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book");
		List<Book> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
	}
	

	
	public List<Book> recommendBook(){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select t from Book t  join t.markSet a where a.type = '推荐' ";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		
		return list;
	}
	
	public List<Book> mainPush(){
		
		Session session = sessionFactory.getCurrentSession();
		String hql =   "select t from Book t join t.typeSet join t.markSet a where a.type = '主推' ";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		
		
		return list;
		
	}
	
	public List<Book> newBook(){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select t from Book t join t.markSet a where a.type = '上新' ";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		return list;
		
	}
	
	public Book seeSingleBook(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select book from Book book join book.typeSet bt  where book.id= "+id;
		Query query = session.createQuery(hql);
		Book book =(Book) query.uniqueResult();
		return book;
		
	}
	
	public List<Book> foundBook(String tag){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.typeSet t where b.bookName like '%"+tag+"%' or b.description like'%"+tag+"%' or b.publish like'%"+tag+"%'"
				+ "or b.author like'%"+tag+"%' or t.type like '%"+tag+"%'";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		return list;
		
	}
	
	public List<Book> seeTypeBook(String type,Pages page){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.typeSet m where m.type =?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		List<Book> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
		
	}
	
public List<Book> seeMarkBook(String type,Pages page){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.markSet t where t.type =?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		List<Book> list = query.setFirstResult((page.getDpage()-1)*page.getPageCount()).setMaxResults(page.getPageCount()).list();
		return list;
		
	}

	public List<Book> newSeeTypeBook(String type){
	
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.markSet m  join b.typeSet t where m.type='上新' and  t.type =?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		List<Book> list = query.list();
		return list;
	
	}
	
	public boolean addChat(Orders order){
		
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
		return true;
		
		
	}
	
	public List<Orders> seeChat(Integer id){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select o from User u join u.orderSet o join o.book b where u.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		List<Orders> list = query.list();
		
		return list;
		
	}
	
	public boolean shopping(Shop shop) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(shop);
		
		Integer id = findMaxIdForShop();
		Shop shop1 = findShopById(id);
		
		return true;
		
	}
	
public Shop shopping1(Shop shop) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(shop);
		
		Integer sId = shop.getId();
		Orders order = findOrderByShopId(sId);
		order.setUser(null);
		upOrder(order);
		Integer id = findMaxIdForShop();
		Shop shop1 = findShopById(id);
		
		return shop1;
		
	}
	
	/* ***************查找ID最大的shop************** */
	public Integer  findMaxIdForShop() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select max(id) from Shop";
		Query query = session.createQuery(hql);
		Integer id = (Integer) query.uniqueResult();
		return id;
		
	}
	
	public Orders seeShop(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select o from Orders o join o.user join o.book where o.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Orders order = (Orders) query.uniqueResult();
		return order;
		
	}
	
	public List<Shop> seeShopping(Integer userId){
		
		System.out.println(userId);
		Session session = sessionFactory.getCurrentSession();
		String hql = "select s from Shop s join s.order o  join s.user su where su.id=? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		List<Shop> shoplist = query.list();
		return shoplist;
		
	}
	
	/* ************通过order的Id修改user地址************ */
	public boolean updateAddressByOrderId(Integer id,String address) {
		
		Orders order = selectOrderById(id);
		Session session = sessionFactory.getCurrentSession();
		String hql = "update User u join u.orderSet o set address = ? where o.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, address);
		query.setParameter(1, id);
		query.executeUpdate();
		return true;
		
	}
	
	/* ************通过order的Id查找user************ */
	public User findUserByOrderId(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select o.user from Orders o where o.id=? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		User user = (User) query.uniqueResult();
		//System.out.println(user.getUsername()+"00000000");
		return user;
		
	}
	
	/* *************更新数量************* */
	public boolean updateQuentity(Integer quentity,Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Orders set quentity=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, quentity);
		query.setParameter(1, id);
		query.executeUpdate();
		return true;
		
	}
	
	/* ************通过user改变address*********** */
	public boolean updateAddress(Integer id,String address,Integer quentity) {
		
		User user1 = findUserByOrderId(id);
		Integer userId = user1.getId();
		Session session = sessionFactory.getCurrentSession();
		String hql = "update User set address=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, address);
		query.setParameter(1, userId);
		query.executeUpdate();
		updateQuentity(quentity,id);
		return true;
		
		
	}
	
	
	public Orders selectOrderById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Orders where id=? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Orders order = (Orders) query.uniqueResult();
		return order;
		
	}
	
public Orders upOrderById(Integer id,String address,Integer quentity) {
		
	updateAddress(id,address,quentity);
	Orders order = selectOrderById(id);
	return order;
	
	}
	
	public boolean upOrder(Orders order) {
		
		Session session = sessionFactory.getCurrentSession();
		Integer id = order.getId();
		String hql = "update Orders o set o.user =? where o.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, null);
		query.setParameter(1, id);
		query.executeUpdate();
		return true;
		
	}
	
	/* ****根据类型查询书籍*****  */
	public List<Book> findBookByType(String type){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.typeSet bt where bt.type=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		List<Book> booklist = query.list();
		return booklist;
		
	}
	
	/* *****根据id查找书籍***** */
public Book findBookById(Integer id){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select b from Book b join b.typeSet bt where b.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Book book = (Book) query.uniqueResult();
		return book;
		
	}
	
	/* *****根据id查找书籍***** */
	public User findUserById(Integer id){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select u from User u where u.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		User user = (User) query.uniqueResult();
		return user;
		
	}
	
	/* *********查找书籍的类别********** */
	
	public List<Type> findAllType(){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Type";
		Query query = session.createQuery(hql);
		List<Type> list = query.list();
		return list;
		
	}
	
	/* *******保存购物车信息******* */
	public Orders updateOrder(Integer id) {
		
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select o from Orders o where o.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Orders order = (Orders) query.uniqueResult();
		return order;
		
	}
	
	
	public Orders saveOrder(Orders order) {
		Integer quentity = order.getQuentity();
		Integer id = order.getId();
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Orders set quentity=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, quentity);
		query.setParameter(1, id);
		query.executeUpdate();
		
		Orders order1 = updateOrder(id);
		return order1;
		
	}
	
	/* ********根据id查购物车中的商品********* */
	public Orders findOrder(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Orders where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Orders order = (Orders) query.uniqueResult();
		return order;
		
	}
	
	/* *********删除购物车中的书籍********* */
	public boolean deleteOrder(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql="delete from Orders o where o.id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		int n = query.executeUpdate(); 
		if(n != 0) {
			return true;
		}else {
			return false;
		}
	}

	/* *************根据userId查找购物车信息************** */
	public List<Orders> findOrderByuserId(Integer userId){
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Orders where userId=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		List<Orders> list = query.list();
		
		return list;
		
	}
	
	
	/* ********清空购物车******** */
	public boolean deleteAllChat(Integer userId) {
		
		List<Orders> list = findOrderByuserId(userId);
		int n = 0;
		for(Orders o:list) {
			System.out.println(o.getBook().getBookName());
			Integer oId = o.getId();
			Session session = sessionFactory.getCurrentSession();
			String hql="delete from Orders o where o.id=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, oId);
			n = query.executeUpdate(); 
			
			
		}
		if(n != 0) {
			return true;
		}else {
			return false;
		}
		
	}
	
	/* **********根据id查询订单*********** */
	public Shop findShopById(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Shop where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Shop shop = (Shop) query.uniqueResult();
		return shop;
		
	}
	
	/* ************根据订单id查找购物车产品************* */
	public Orders findOrderByShopId(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select o from Orders o join o.shop os where os.id=? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Orders order = (Orders) query.uniqueResult();
		return order;
		
	}
	
	/* ************删除购物车产品************* */
	public boolean deleteOrderByShopId(Orders order) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(order);
		return true;
		
	}
	
	/* ***************根据Id删除订单************** */
	public boolean deleteShop(Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		Shop shop = findShopById(id);
		Orders order = findOrderByShopId(id);
		shop.setOrder(null);
		session.delete(shop);
		return true;
	}
	
	
	
	/* **********查询刚插入的一个order************ */
	public Integer findMaxId() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "select max(id) from Orders";
		Query query = session.createQuery(hql);
		Integer id = (Integer) query.uniqueResult();
		return id;
		
	}
	
	public Orders findMaxOrder() {
		Integer id = findMaxId();
		Session session = sessionFactory.getCurrentSession();
		String hql = "select o from Orders o where id=? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		Orders order = (Orders) query.uniqueResult();
		return order;
		
	}
	
	
	/* ************直接生成订单************ */
	public Orders createShop(Integer bookId,Integer quentity,User user) {
		
		Book book = seeSingleBook(bookId) ;
		Orders order = new Orders();
		order.setBook(book);
		Integer userId = user.getId();
		order.setUser(user);
		order.setQuentity(quentity);
		boolean res = addChat(order);
		Orders order1 = findMaxOrder();
		return order1;
		
	}
	
	/* **************取消订单*************** */
	public boolean clearShop(Integer id) {
		
		deleteShop(id);
		return true;
		
	}
	
	/* *************验证用户名************ */
	public boolean usernameIsUsered(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "from User";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		for(User u:list) {
			
			if(u.getUsername().equals(username)) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	/* *************修改User地址****************/
	public User updateUser(Integer id,String address) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update User set address=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, address);
		query.setParameter(1, id);
		query.executeUpdate();
		User user = findUserById(id);
		return user;
		
	}
	
	/* *****************修改Order****************** */
	public boolean updateOrderQ(Integer quentity,Integer id) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update Orders set quentity=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, quentity);
		query.setParameter(1, id);
		query.executeUpdate();
		return true;
		
	}
	
	/* ***************查询书籍总数**************** */
	public int selectBookAllCount() {
		
		Integer count=0;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) as num from Book";
		Query query = session.createQuery(hql);
		count = ((Number)query.uniqueResult()).intValue();
		return count;
		
	}
	
	/* ***************查询某类型书籍总数**************** */
	public int selectTypeBookAllCount(String type) {
		
		Integer count=0;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) as num from Book b join b.typeSet bt where bt.type=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		count = ((Number)query.uniqueResult()).intValue();
		return count;
		
	}
	
	/* ***************查询某标签书籍总数**************** */
	public int selectMarkBookAllCount(String type) {
		
		Integer count=0;
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(*) as num from Book b join b.markSet bm where bm.type=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, type);
		count = ((Number)query.uniqueResult()).intValue();
		return count;
		
	}
	
}
