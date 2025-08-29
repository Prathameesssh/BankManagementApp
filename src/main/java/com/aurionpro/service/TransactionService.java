package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.TransactionDao;
import com.aurionpro.model.Transaction;

public class TransactionService {

	
	public static List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		TransactionDao transactionDao = new TransactionDao();
		List<Transaction> transactions = transactionDao.getAllTransactions();
		return transactions;
	}
}
