package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.AccountDao;
import com.aurionpro.dao.TransactionDao;
import com.aurionpro.model.Transaction;

public class TransactionService {

	
	public static List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		TransactionDao transactionDao = new TransactionDao();
		List<Transaction> transactions = transactionDao.getAllTransactions();
		return transactions;
	}

	public static List<Transaction> getUserTransaction(String accountNumber) {
		
		TransactionDao transactionDao = new TransactionDao();
		List<Transaction> transactions = transactionDao.getUserTransaction(accountNumber);
		return transactions;
	}

	public static boolean transferMoney(String fromAccount, String toAccount, double transferAmount) {
		// TODO Auto-generated method stub
		TransactionDao transactionDao = new TransactionDao();
		AccountDao accountDao = new AccountDao();
		boolean isMoneySent = accountDao.sendMoney(fromAccount, toAccount, transferAmount);
		if(!isMoneySent) {
			return false;
		}
		boolean addTransaction = transactionDao.addTransaction(fromAccount, toAccount, transferAmount);
		return true;
	}
}
