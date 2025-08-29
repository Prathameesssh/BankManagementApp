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
import com.aurionpro.model.User;

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
				int fromAccount = rs.getInt("from_account");
				int toAccount = rs.getInt("to_account");
				double amount = rs.getDouble("amount");
				Date createdAt = rs.getDate("created_at"); // using java.sql.Date since DB column is DATE
				String type = rs.getString("type");

				Transaction transaction = new Transaction(id, fromAccount, toAccount, amount, type, createdAt);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return transactions;
	}

}
