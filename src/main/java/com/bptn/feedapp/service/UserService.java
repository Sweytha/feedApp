package com.bptn.feedapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bptn.feedapp.jdbc.UserBean;
import com.bptn.feedapp.jdbc.UserDao;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public List<UserBean> listUsers() {

		//this calls listUsers() method from UserDao class
		return this.userDao.listUsers();
	}

	public UserBean findByUsername(String username) {

		//this calls findByUsername() method from UserDao class
		return this.userDao.findByUsername(username);
		
	}
	
	public void createUser(UserBean user) {
		
		//this calls createUser() method from UserDao class
		this.userDao.createUser(user);
	}
	
	

}
