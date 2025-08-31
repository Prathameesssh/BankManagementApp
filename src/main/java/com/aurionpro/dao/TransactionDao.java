package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.Transaction;

public class TransactionDao {

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public TransactionDao() {
		this.connection = Database.getConnection();
	}

	public List<Transaction> getAllTransactions() {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}
		List<Transaction> transactions = new ArrayList<>();
		String sqlStatement = "SELECT * FROM transaction";

		try {
			ps = connection.prepareStatement(sqlStatement);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String fromAccount = rs.getString("from_account");
				String toAccount = rs.getString("to_account");
				double amount = rs.getDouble("amount");
				Date createdAt = rs.getDate("created_at"); // using java.sql.Date since DB column is DATE
				
				Transaction transaction = new Transaction(id, fromAccount, toAccount, amount,createdAt);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactions;
	}

	public List<Transaction> getUserTransaction(String accountNumber) {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}
		List<Transaction> transactions = new ArrayList<>();
		String sqlStatement = "SELECT * FROM transaction WHERE from_account = ? OR to_account = ?";
		
		try {
			ps = connection.prepareStatement(sqlStatement);
			ps.setString(1, accountNumber);
			ps.setString(2, accountNumber);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id1 = rs.getInt("id");
				String fromAccount = rs.getString("from_account");
				String toAccount = rs.getString("to_account");
				double amount = rs.getDouble("amount");
				Date createdAt = rs.getDate("created_at"); // using java.sql.Date since DB column is DATE
				
				Transaction transaction = new Transaction(id1, fromAccount, toAccount, amount,createdAt);
				System.out.println("Into transaction: "+transaction);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
	}

	public boolean addTransaction(String fromAccount, String toAccount, double transferAmount) {
	    if (connection == null) {
	        throw new RuntimeException("Database Error");
	    }

	    String insertSQL = "INSERT INTO transaction (from_account, to_account, amount, created_at) VALUES (?, ?, ?, ?)";
	    boolean success = false;

	    try {
	        // Begin transaction
	        connection.setAutoCommit(false);

	        // 1. Insert the transaction record
	        ps = connection.prepareStatement(insertSQL);
	        ps.setString(1, fromAccount);
	        ps.setString(2, toAccount);
	        ps.setDouble(3, transferAmount);
	        ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
	        int rowsInserted = ps.executeUpdate();
	        ps.close();

	        if (rowsInserted != 1) {
	            throw new SQLException("Failed to insert transaction record");
	        }

	        // Commit if insert succeeded
	        connection.commit();
	        success = true;

	    } catch (SQLException e) {
	        // Rollback on error
	        try {
	            connection.rollback();
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        e.printStackTrace();
	        success = false;
	    } finally {
	        // Restore auto-commit and clean up
	        try {
	            connection.setAutoCommit(true);
	            if (ps != null && !ps.isClosed()) {
	                ps.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return success;
	}


}
