package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.Date;
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

	    String insertSQL = "INSERT INTO account (account_number, user_id, type, balance, created_at) VALUES (?, ?, ?, ?, ?)";
	    try {
	        String accountNumber = generateUniqueAccountNumber();
	        Date createdDate = new Date(System.currentTimeMillis());

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
	        String checkSQL = "SELECT COUNT(*) FROM account WHERE account_number = ?";
	        ps = connection.prepareStatement(checkSQL);
	        ps.setString(1, accountNumber);
	        rs = ps.executeQuery();
	        rs.next();
	        exists = rs.getInt(1) > 0;
	    } while (exists);

	    return accountNumber;
	}

	public Account getUserAccount(int userId) {
		// TODO Auto-generated method stub
		if (connection == null) {
	        throw new RuntimeException("Database connection is null");
	    }
		Account account = null;
		String sqlStatement = "SELECT * FROM account WHERE user_id = ?";
		try {
			ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String accountNumber = rs.getString("account_number");
				String type = rs.getString("type");
				double balance = rs.getDouble("balance");
				Date createdAt = rs.getDate("created_at");
				
				

				account = new Account(id,user_id,accountNumber,balance,type,createdAt);
				return account;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return account;
	}

	public boolean sendMoney(String fromAccount, String toAccount, double transferAmount) {
	    try {
	        // Check if connection is valid (your existing check)
	        if (connection == null) {
	            throw new RuntimeException("Database connection is null");
	        }
	        
	        // Start transaction by disabling auto-commit
	        connection.setAutoCommit(false);
	        
	        // 1. Check sender's balance
	        String checkBalanceSQL = "SELECT balance FROM account WHERE account_number = ?";
	        ps = connection.prepareStatement(checkBalanceSQL);
	        ps.setString(1, fromAccount);
	        rs = ps.executeQuery();
	        
	        if (!rs.next()) {
	            connection.rollback();
	            return false; // From account not found
	        }
	        
	        double currentBalance = rs.getDouble("balance");
	        if (currentBalance < transferAmount) {
	            connection.rollback();
	            return false; // Insufficient balance
	        }
	        rs.close();
	        ps.close();
	        
	        // 2. Debit from sender's account
	        String debitSQL = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
	        ps = connection.prepareStatement(debitSQL);
	        ps.setDouble(1, transferAmount);
	        ps.setString(2, fromAccount);
	        int debitResult = ps.executeUpdate();
	        ps.close();
	        
	        // 3. Credit to receiver's account  
	        String creditSQL = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
	        ps = connection.prepareStatement(creditSQL);
	        ps.setDouble(1, transferAmount);
	        ps.setString(2, toAccount);
	        int creditResult = ps.executeUpdate();
	        ps.close();
	        
	        // 4. Check if both operations were successful
	        if (debitResult == 1 && creditResult == 1) {
	            connection.commit(); // Commit transaction
	            return true;
	        } else {
	            connection.rollback(); // Rollback if any operation failed
	            return false;
	        }
	        
	    } catch (SQLException e) {
	        // Rollback on any exception
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	        return false;
	        
	    } finally {
	        // Restore auto-commit mode
	        try {
	            if (connection != null) {
	                connection.setAutoCommit(true);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
