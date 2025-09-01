package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			ps.setDouble(4, 0.0); // default balance
			ps.setDate(5, createdDate); // only DATE

			int rows = ps.executeUpdate();
			if (rows > 0) {
				return new Account(userId, accountNumber, 0.0, "SAVINGS", createdDate);
			}
			;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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

		ps.close();
		rs.close();
		return accountNumber;
	}

	public Account getUserAccount(String accountNumber) {
		if (connection == null) {
			throw new RuntimeException("Database connection is null");
		}
		Account account = null;
		String sqlStatement = "SELECT * FROM account WHERE account_number = ?";
		try {
			ps = connection.prepareStatement(sqlStatement);
			ps.setString(1, accountNumber);

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String account_number = rs.getString("account_number");
				String type = rs.getString("type");
				double balance = rs.getDouble("balance");
				Date createdAt = rs.getDate("created_at");
				boolean isFreezed = rs.getBoolean("is_freezed");

				account = new Account(id, user_id, account_number, balance, type, createdAt, isFreezed);
				return account;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return account;
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
				boolean isFreezed = rs.getBoolean("is_freezed");

				account = new Account(id, user_id, accountNumber, balance, type, createdAt, isFreezed);
				return account;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return account;
	}

	public boolean sendMoney(String fromAccount, String toAccount, double transferAmount) {
		if (connection == null) {
			throw new RuntimeException("Database connection is null");
		}

		try {
			// Start transaction
			connection.setAutoCommit(false);

			// 1. Check sender's balance and freeze status
			String checkSenderSQL = "SELECT balance, is_freezed FROM account WHERE account_number = ?";
			ps = connection.prepareStatement(checkSenderSQL);
			ps.setString(1, fromAccount);
			rs = ps.executeQuery();
			if (!rs.next()) {
				connection.rollback();
				return false; // From account not found
			}
			double currentBalance = rs.getDouble("balance");
			boolean senderFrozen = rs.getBoolean("is_freezed");
			rs.close();
			ps.close();

			if (senderFrozen) {
				connection.rollback();
				return false; // Sender account is frozen
			}
			if (currentBalance < transferAmount) {
				connection.rollback();
				return false; // Insufficient balance
			}

			// 2. Check receiver freeze status
			String checkReceiverSQL = "SELECT is_freezed FROM account WHERE account_number = ?";
			ps = connection.prepareStatement(checkReceiverSQL);
			ps.setString(1, toAccount);
			rs = ps.executeQuery();
			if (!rs.next()) {
				connection.rollback();
				return false; // To account not found
			}
			boolean receiverFrozen = rs.getBoolean("is_freezed");
			rs.close();
			ps.close();

			if (receiverFrozen) {
				connection.rollback();
				return false; // Receiver account is frozen
			}

			// 3. Debit sender
			String debitSQL = "UPDATE account SET balance = balance - ? WHERE account_number = ?";
			ps = connection.prepareStatement(debitSQL);
			ps.setDouble(1, transferAmount);
			ps.setString(2, fromAccount);
			int debitResult = ps.executeUpdate();
			ps.close();

			// 4. Credit receiver
			String creditSQL = "UPDATE account SET balance = balance + ? WHERE account_number = ?";
			ps = connection.prepareStatement(creditSQL);
			ps.setDouble(1, transferAmount);
			ps.setString(2, toAccount);
			int creditResult = ps.executeUpdate();
			ps.close();

			// 5. Commit or rollback
			if (debitResult == 1 && creditResult == 1) {
				connection.commit();
				return true;
			} else {
				connection.rollback();
				return false;
			}

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
			return false;

		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean changeFreezeStatus(String accountNumber, boolean status) {
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}

		String freezeSQL = "UPDATE account SET is_freezed = ? WHERE account_number = ?";

		try {
			ps = connection.prepareStatement(freezeSQL);
			ps.setBoolean(1, status);
			ps.setString(2, accountNumber);

			int rowsAffected = ps.executeUpdate();
			ps.close();

			return rowsAffected > 0; // Returns true if account was found and frozen

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean creditAccount(String accountNumber, double amount) {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}
		String creditSQL = "UPDATE account SET balance = balance + ? WHERE account_number = ?";

		try {
			ps = connection.prepareStatement(creditSQL);
			ps.setDouble(1, amount);
			ps.setString(2, accountNumber);

			int rowsAffected = ps.executeUpdate();
			ps.close();

			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean debitAccount(String accountNumber, double amount) {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}
		String creditSQL = "UPDATE account SET balance = balance - ? WHERE account_number = ?";

		try {
			ps = connection.prepareStatement(creditSQL);
			ps.setDouble(1, amount);
			ps.setString(2, accountNumber);

			int rowsAffected = ps.executeUpdate();
			ps.close();

			return rowsAffected > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public List<Account> getAllAccounts() {
		if (connection == null) {
			throw new RuntimeException("Database connection is null");
		}
		List<Account> accounts = new ArrayList<>();
		String sqlStatement = "SELECT * FROM account";
		try {
			ps = connection.prepareStatement(sqlStatement);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				String accountNumber = rs.getString("account_number");
				String type = rs.getString("type");
				double balance = rs.getDouble("balance");
				Date createdAt = rs.getDate("created_at");
				boolean isFreezed = rs.getBoolean("is_freezed");

				Account account = new Account(id, user_id, accountNumber, balance, type, createdAt, isFreezed);
				accounts.add(account);
			}
			return accounts;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// TODO Auto-generated method stub
		return accounts;
	}

}
