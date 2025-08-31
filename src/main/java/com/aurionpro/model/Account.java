package com.aurionpro.model;

import java.sql.Date;

public class Account {

	private int id, userId;
	private String accountNumber;
	private double balance;
	private Date createdAt;
	private String type;

	public Account() {
		super();
	}

	public Account(int id, int userId, String accountNumber, double balance, String type, Date createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.setType(type);
		this.createdAt = createdAt;
	}

	public Account(int userId, String accountNumber, double balance, String type, Date createdAt) {
		super();
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.setType(type);
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ ", createdAt=" + createdAt + "]";
	}

}
