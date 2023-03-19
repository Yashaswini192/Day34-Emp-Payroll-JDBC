package com.jdbc.bl.JDBCDemo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import com.mysql.cj.xdevapi.Statement;

public class Demo {

	public static void listDrivers() {
		Enumeration<Driver> obj1 = DriverManager.getDrivers();
		while(obj1.hasMoreElements()) {
			Driver obj2 = obj1.nextElement();
			System.out.println( " "+obj2.getClass().getName());
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("driver loaded");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("cannot find the driver in the classpath!", e);
		}
		try {		
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "T@eHyung143");		    
			System.out.println("Connection with database is succesfull");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listDrivers();
		
	}

}
