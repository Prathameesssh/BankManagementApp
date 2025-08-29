package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aurionpro.database.Database;
import com.aurionpro.model.Account;
import com.aurionpro.service.AdminService;

public class AccountDao {

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public AccountDao() {
		this.connection = Database.getConnection();
	}
	
	public Account createAccount(int userId) {
	    if (connection == null) {
	        throw new RuntimeException("Database connection is null");
	    }

	    String insertSQL = "INSERT INTO accounts (account_number, user_id, type, balance, created_at) VALUES (?, ?, ?, ?, ?)";
	    try {
	        String accountNumber = generateUniqueAccountNumber();
	        java.sql.Date createdDate = new java.sql.Date(System.currentTimeMillis());

	        ps = connection.prepareStatement(insertSQL);
	        ps.setString(1, accountNumber);
	        ps.setInt(2, userId);
	        ps.setString(3, "SAVINGS"); // default type
	        ps.setDouble(4, 0.0);       // default balance
	        ps.setDate(5, createdDate); // only DATE

	        int rows = ps.executeUpdate();
	        if(rows > 0) {
	        	return new Account(userId, accountNumber, 0.0,"SAVINGS", createdDate);
	        };
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	private String generateUniqueAccountNumber() throws SQLException {
	    String accountNumber;
	    boolean exists;

	    do {
	        accountNumber = AdminService.generateRandomAlphaNumeric(10); // 10-char alphanumeric
	        String checkSQL = "SELECT COUNT(*) FROM accounts WHERE account_number = ?";
	        ps = connection.prepareStatement(checkSQL);
	        ps.setString(1, accountNumber);
	        rs = ps.executeQuery();
	        rs.next();
	        exists = rs.getInt(1) > 0;
	    } while (exists);

	    return accountNumber;
	}
}
