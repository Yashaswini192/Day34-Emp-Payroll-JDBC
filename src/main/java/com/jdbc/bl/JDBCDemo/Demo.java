package com.jdbc.bl.JDBCDemo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Statement;

public class Demo {

	//static Connection obj;
	public static void listDrivers() {
		Enumeration<Driver> obj1 = DriverManager.getDrivers();
		while(obj1.hasMoreElements()) {
			Driver obj2 = obj1.nextElement();
			System.out.println( " "+obj2.getClass().getName());
		}
	}
	public static void getData() {
		try {
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","T@eHyung143");
			PreparedStatement obj3 = obj.prepareStatement("select * from employee");
			ResultSet result = obj3.executeQuery();
			while(result.next()) {		

				System.out.println("Employee Id: "+result.getInt(1)+
						"\nEmployee name: "+result.getString(2)+
						"\nPhoneNumber:"+result.getString(3)+
						"\nDepartment:"+result.getString(4)+
						"\nGender:"+result.getString(5)+
						"\nAddress:"+result.getString(6)+
						"\nBasicPay:"+result.getString(7)+
						"\nDeductions:"+result.getString(8)+
						"\nTaxablePay:"+result.getString(9)+
						"\nTax:"+result.getString(10)+
						"\nNetPay:"+result.getString(11)+
						"\nStartDate:"+result.getString(12));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public static void connectDatabase() {
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
	}
	public static void closeConnection(){

		try {
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","T@eHyung143");
			obj.close();
			System.out.println("---Database Connection Closed---");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		catch (NullPointerException np){
			System.out.println("----Database Connection Never Started----");
		}

	}


	public static void updateContact(){

		try{
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","T@eHyung143");
			String updateSQL = "update employee_payroll set basic_pay = 30000 where name = \"Terisa\"";
			PreparedStatement statement = obj.prepareStatement(updateSQL);
			int update = statement.executeUpdate(updateSQL);


			if (update > 0){
				System.out.println("Data Successfully Updated : " + update + " Rows Updated");
			}
			else{
				System.out.println("Data Not Added");
			}
		}
		catch (Exception e){

			System.out.println("Field Not Available");
		}

	}

	public static void updateData(){
		String name;
		int salary;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter The Name : ");
		name = sc.next();
		System.out.print("Enter Salary : ");
		salary = sc.nextInt();

		try{
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","T@eHyung143");
			PreparedStatement ps = obj.prepareStatement("update employee_payroll set basic_pay = ? where name = ?");
			ps.setInt(1,salary);
			ps.setString(2,name);
			int update = ps.executeUpdate();


			if (update > 0){
				System.out.println("Data Successfully Updated : " + update + " Rows Updated");
			}
			else{
				System.out.println("Data Not Added");
			}
		}
		catch (Exception e){

			System.out.println("Field Not Available");
		}

	}

	public static void getDataByName() {

		String name;
		Scanner s = new Scanner(System.in);
		System.out.print("Enter The Name : ");
		name = s.next();

		try {
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","T@eHyung143");
			PreparedStatement ps1 = obj.prepareStatement("Select * from employee_payroll where name = ?");
			ps1.setString(1,name);
			ResultSet result = ps1.executeQuery();
			while(result.next()){
				System.out.println("Employee Id: "+result.getInt(1)+
						"\nEmployee name: "+result.getString(2)+
						"\nPhoneNumber:"+result.getString(3)+
						"\nDepartment:"+result.getString(4)+
						"\nGender:"+result.getString(5)+
						"\nAddress:"+result.getString(6)+
						"\nBasicPay:"+result.getString(7));
				System.out.print(
						"Deductions:"+result.getString(8)+
						"\nTaxablePay:"+result.getString(9)+
						"\nTax:"+result.getString(10)+
						"\nNetPay:"+result.getString(11)+
						"\nStartDate:"+result.getString(12));


			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void getByDateRange() {

		String startingDate;
		String endingDate;
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter The StartingDate : ");
		startingDate = scan.next();
		System.out.print("Enter The Ending Date : ");
		endingDate = scan.next();

		try {
			Connection obj = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service","root","T@eHyung143");

			PreparedStatement  ps = obj.prepareStatement("select * from employee_payroll where start_Date between cast(? as date) and date (?)");
			ps.setString(1, startingDate);
			ps.setString(2,endingDate);
			ResultSet result = ps.executeQuery();
			while(result.next()){System.out.println("Employee Id: "+result.getInt(1)+
					"\nEmployee name: "+result.getString(2)+
					"\nPhoneNumber:"+result.getString(3)+
					"\nDepartment:"+result.getString(4)+
					"\nGender:"+result.getString(5)+
					"\nAddress:"+result.getString(6)+
					"\nBasicPay:"+result.getString(7));
			System.out.print(
					"Deductions:"+result.getString(8)+
					"\nTaxablePay:"+result.getString(9)+
					"\nTax:"+result.getString(10)+
					"\nNetPay:"+result.getString(11)+
					"\nStartDate:"+result.getString(12));

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		System.out.println("--->Press 1 to Connect With The Database<---" +
				"\n--->Press 2 To Get Data From Database<---" +
				"\n-->press 3 to get list of Drivers"+
				"\n-->Press 4 to update basic pay"+
				"\n-->Press 5 to update Data"+
				"\n-->Press 6 to Get Data By Name"+
				"\n-->Press 7 to Get Data By Range"+
				"\n--->Press 8 To Close The Database Connection<---");
		int userinput=sc.nextInt();

		switch(userinput) {

		case 1:
			connectDatabase();	
			break;
		case 2:
			getData();
			break;
		case 3:
			listDrivers();
			break;
		case 4:
			updateContact();
			break;
		case 5:
			updateData();
			break;
		case 6:
			getDataByName();
			break;
		case 7:
			getByDateRange();
			break;
		case 8:
			closeConnection();
			break;
		default:
			System.out.println("cannot find database");
		}
	}
}
