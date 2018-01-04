package com.liuyq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liuyq.bean.User;
import com.liuyq.dao.BookDao;
import com.liuyq.dao.UserDao;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean regist(User user) {
		
		return userDao.insert(user);
		
	}
	
	public boolean insertImgUrl(User user) {
		
		return userDao.insertImgUrl(user);
		
	}
	
	public User login(String password,String username) {
		
		return userDao.login(password, username);
		
	}
	
	public User aboutMe(Integer id) {
		
		return userDao.aboutMe(id);
		
	}
	
	public User update(User user) {
		
		return userDao.update(user);
		
	}
	
	public User findUserByMail(String mail) {
		
		return userDao.findUserByMail(mail);
		
	}
	
	public boolean resetPassword(User user,String password) {
		
		return userDao.resetPassword(user, password);
		
	}
	
	public Integer insertUser(User user) {
		
		return userDao.insertUser(user);
		
	}
	
}
