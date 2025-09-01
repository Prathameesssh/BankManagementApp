package com.aurionpro.service;

import java.util.List;
import java.util.Random;

import com.aurionpro.dao.AccountDao;
import com.aurionpro.dao.UserDao;
import com.aurionpro.model.Account;
import com.aurionpro.model.User;
import com.aurionpro.utils.AuthBalance;

public class AdminService {

	public static List<User> getAllUser() {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();

		List<User> users = userDao.getAllUsers();
		return users;
	}

	public boolean rejectUser(int userId) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		return userDao.updateUserStatus(userId, true);
	}

	public boolean approveUser(int userId) {
		UserDao userDao = new UserDao();
		return userDao.updateUserStatus(userId, false);
	}

	public Account createAccount(int userId) {
		// TODO Auto-generated method stub
		AccountDao accountDao = new AccountDao();
		
		return accountDao.createAccount(userId);
	}
	public static String generateRandomAlphaNumeric(int length) {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuilder sb = new StringBuilder();
	    Random rnd = new Random();
	    for (int i = 0; i < length; i++) {
	        sb.append(chars.charAt(rnd.nextInt(chars.length())));
	    }
	    return sb.toString();
	}

	public boolean changeFreezeStatus(String accountNumber, boolean status) {
		// TODO Auto-generated method stub
		AccountDao accountDao = new AccountDao();
		
		return accountDao.changeFreezeStatus(accountNumber,status);
	}

	public boolean creditAccount(String accountNumber, double amount) {
		// TODO Auto-generated method stub
		AccountDao accountDao = new AccountDao();
		
		return accountDao.creditAccount(accountNumber, amount);
	}

	public boolean debitAccount(String accountNumber, double amount) {
		// TODO Auto-generated method stub
		AccountDao accountDao = new AccountDao();
		Account account = accountDao.getUserAccount(accountNumber);
		boolean isBalanceAvailable = AuthBalance.checkSenderBalance(account, amount);
		if(!isBalanceAvailable)
			return false;
		return accountDao.debitAccount(accountNumber, amount);
	}

	public static List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		AccountDao accountDao = new AccountDao();
		
		return accountDao.getAllAccounts();
	}

}
