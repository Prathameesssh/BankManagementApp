package com.aurionpro.service;

import com.aurionpro.dao.AccountDao;
import com.aurionpro.dao.UserDao;
import com.aurionpro.model.Account;
import com.aurionpro.model.User;

public class UserService {

	public static User getUser(String email, String password) {
		
		UserDao userDao = new UserDao();
		User user = userDao.getUser(email,password);
		System.out.println(user);
		return user;
	}

	public static boolean addUser(String name, String address, String email, String password, long mobile,
			long aadharNo) {
		UserDao userDao = new UserDao();
		
		User user = new User(name,address,email,password,mobile,aadharNo);
		boolean isAdded = userDao.addUser(user);
		return isAdded;
	}

	public static User getUserById(int userId) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		User user = userDao.getUserById(userId);
		return user;
	}

	public static Account getAccount(int id) {
		// TODO Auto-generated method stub
		AccountDao accountDao = new AccountDao();
		Account account = accountDao.getUserAccount(id);
		return account;
	}

}
