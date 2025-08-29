package com.aurionpro.model;

import java.sql.Date;

public class Admin {

	private int id, userId;
	private String designation;
	private double salary;
	private Date hireDate;
	
	public Admin() {
		super();
	}

	public Admin(int id, int userId, String designation, double salary, Date hireDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.designation = designation;
		this.salary = salary;
		this.hireDate = hireDate;
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

	public void setUser_id(int userId) {
		this.userId = userId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", userId=" + userId + ", designation=" + designation + ", salary=" + salary
				+ ", hireDate=" + hireDate + "]";
	}	
	
}
