package com.aurionpro.model;

import java.sql.Date;

public class User {

	private int id;
	private String name, address, email, password;
	private long mobile,aadharNo;
	private Date created_at;
	private boolean isAdmin,isVerified,isRejected;
	
	public User() {
		super();
	}

	public User(int id, String name, String address, String email, String password, long mobile,
			long aadharNo, Date created_at, boolean isAdmin, boolean isVerified, boolean isRejected) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.aadharNo = aadharNo;
		this.created_at = created_at;
		this.isAdmin = isAdmin;
		this.isVerified = isVerified;
		this.isRejected = isRejected;
	}

	

	public User(String name, String address, String email, String password, long mobile, long aadharNo) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.aadharNo = aadharNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public Date getCreated_at() {
		return created_at;
	}

	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean is_verified) {
		this.isVerified = is_verified;
	}

	public boolean isRejected() {
		return isRejected;
	}

	public void setRejected(boolean isRejected) {
		this.isRejected = isRejected;
	}
	
	
	
}
