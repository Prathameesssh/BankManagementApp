package com.aurionpro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Connection connection;

	private Database() {

	}
	
	public static Connection getConnection() {
		if(connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/BankManagementSystem", "postgres",
						"Prathamesh@1783");
				if(connection!=null) {
					System.out.println("Connection successfull");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
}
