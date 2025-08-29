package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.User;

public class UserDao {

	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserDao() {
		this.connection = Database.getConnection();
	}

	public User getUser(String email, String password) {

		User user = null;
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}

		String sqlStatement = "SELECT * FROM users WHERE email=? AND password = ?";
		try {
			ps = connection.prepareStatement(sqlStatement);
			ps.setString(1, email);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String empEmail = rs.getString("email");
				long mobile = rs.getLong("mobile");
				long aadharNo = rs.getLong("aadhar_no");
				String empPassword = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				boolean isAdmin = rs.getBoolean("is_admin");
				boolean isVerified = rs.getBoolean("is_verified");
				boolean isRejected = rs.getBoolean("is_rejected");

				user = new User(id, name, address, empEmail, empPassword, mobile, aadharNo, createdAt, isAdmin,
						isVerified, isRejected);
				System.out.println(user);
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return user;

	}

	public boolean addUser(User user) {

		if (connection == null) {
			throw new RuntimeException("Database Error");
		}

		String sql = "INSERT INTO users (name, address, email, password, mobile, aadhar_no, created_at) VALUES (?, ?, ?, ?, ?, ?, NOW())";

		try (Connection conn = Database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getAddress());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setLong(5, user.getMobile());
			pstmt.setLong(6, user.getAadharNo());

			int rowsInserted = pstmt.executeUpdate();
			return rowsInserted > 0; // true if at least one row inserted

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	public List<User> getPendingUsers() {
		// TODO Auto-generated method stub
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}

		List<User> users = new ArrayList<>();
		String sqlStatement = "SELECT * FROM users WHERE is_verified = false";

		try {
			ps = connection.prepareStatement(sqlStatement);

			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String empEmail = rs.getString("email");
				long mobile = rs.getLong("mobile");
				long aadharNo = rs.getLong("aadhar_no");
				String empPassword = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				boolean isAdmin = rs.getBoolean("is_admin");
				boolean isVerified = rs.getBoolean("is_verified");
				boolean isRejected = rs.getBoolean("is_rejected");

				User user = new User(id, name, address, empEmail, empPassword, mobile, aadharNo, createdAt, isAdmin,
						isVerified, isRejected);
				users.add(user);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return users;

	}

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = null;
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}

		String sqlStatement = "SELECT * FROM users WHERE id=?";
		try {
			ps = connection.prepareStatement(sqlStatement);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String empEmail = rs.getString("email");
				long mobile = rs.getLong("mobile");
				long aadharNo = rs.getLong("aadhar_no");
				String empPassword = rs.getString("password");
				Date createdAt = rs.getDate("created_at");
				boolean isAdmin = rs.getBoolean("is_admin");
				boolean isVerified = rs.getBoolean("is_verified");
				boolean isRejected = rs.getBoolean("is_rejected");

				user = new User(id, name, address, empEmail, empPassword, mobile, aadharNo, createdAt, isAdmin,
						isVerified, isRejected);
				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return user;

	}

	public boolean updateUserStatus(int userId, boolean status) {
		if (connection == null) {
			throw new RuntimeException("Database Error");
		}
		String query = "UPDATE users SET is_rejected = ? WHERE id = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, status);
			ps.setInt(2, userId);

			int rowsUpdated = ps.executeUpdate();
			return rowsUpdated > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
