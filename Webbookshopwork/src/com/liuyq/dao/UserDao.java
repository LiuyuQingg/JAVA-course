package com.liuyq.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.liuyq.bean.Book;
import com.liuyq.bean.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean insert(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return true;
		
	}
	
	public boolean insertImgUrl(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return true;
	}
	
	public User login(String password,String username) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User");
		List<User> list = query.list();
		for(User u : list) {
			
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				
				
				return u;
				
			}
			
		}
		return null;
		
	}
	
	public User aboutMe(Integer id) {
		

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where id=?");
		query.setParameter(0, id);
		User user = (User) query.uniqueResult();
		
		return user;
		
	}
	
	public User update(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		String hql = "update User set username=?,password=?,phone=?,imgUrl=?,address=? where id=?";
		Query query = session.createQuery(hql);
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());
		query.setParameter(2, user.getPhone());
		query.setParameter(3, user.getImgUrl());
		query.setParameter(4, user.getAddress());
		query.setParameter(5, user.getId());
		query.executeUpdate();
		
		Session session1 = sessionFactory.getCurrentSession();
		Query query1 = session1.createQuery("from User where id=?");
		query1.setParameter(0, user.getId());
		User user1 = (User) query1.uniqueResult();
		
		return user1;
		
	}
	
	/* ****** 根据邮箱查找用户 ******* */
	public User findUserByMail(String mail) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where email=?");
		query.setParameter(0, mail);
		User user = (User) query.uniqueResult();
		return user;
		
	}
	
	/* **********重置密码********** */
	public boolean resetPassword(User user,String password) {
		
		Integer id  = user.getId();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set password=? where id=?");
		query.setParameter(0, password);
		query.setParameter(1, id);
		query.executeUpdate();
		
		return true;
		
	}
	
	/* *************查询刚刚插入的一条记录*********** */
	public Integer findJustUser() {
		
		Session session = sessionFactory.getCurrentSession();
		String hql="select max(id) from User";
		Query query = session.createQuery(hql);
		Integer i = (Integer) query.uniqueResult();
		return i;
		
	}
	
	/* *************保存用户*********** */
	public Integer insertUser(User user) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		Integer i = findJustUser();
		return i;
		
	}
	
}
