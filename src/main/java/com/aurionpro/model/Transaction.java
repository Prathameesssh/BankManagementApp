package com.aurionpro.model;

import java.sql.Date;

public class Transaction {

	private int id;
	private double amount; 
	private Date createdAt;
	private String type,from_account, to_account;
	
	public Transaction(int id, String from_account, String to_account, double amount, Date createdAt) {
		super();
		this.id = id;
		this.from_account = from_account;
		this.to_account = to_account;
		this.amount = amount;
		this.createdAt = createdAt;
	}

	public Transaction(int id, String from_account, String to_account, double amount, Date createdAt, String type) {
		super();
		this.id = id;
		this.from_account = from_account;
		this.to_account = to_account;
		this.amount = amount;
		this.createdAt = createdAt;
		this.type = type;
	}


	public Transaction() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom_account() {
		return from_account;
	}

	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}

	public String getTo_account() {
		return to_account;
	}

	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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
		return "Transaction [id=" + id + ", from_account=" + from_account + ", to_account=" + to_account + ", amount="
				+ amount + ", createdAt=" + createdAt + "]";
	}
	
	
	
	
}
