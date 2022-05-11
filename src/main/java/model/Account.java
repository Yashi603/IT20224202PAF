package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Account {
		//A common method to connect to the DB
		 private Connection connect() 
		 { 
			 Connection con = null; 
			 try
			 { 
				 Class.forName("com.mysql.jdbc.Driver"); 
				 
				 //Provide the correct details: DBServer/DBName, username, password 
				 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf", "root", ""); 
			 } 
			 catch (Exception e) 
			 {
				 e.printStackTrace();
			 } 
			 return con; 
		 }
		 
		 public String insertAccount(String accountNo, String areaOffice, String customerID, String accountTypeID) 
		 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = connect();
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for inserting."; 
				 }
				 
				 // create a prepared statement
				 String query = " insert into accounts (`accountID`,`accountNo`,`areaOffice`,`customerID`,`accountTypeID`)"
				 + " values (?, ?, ?, ?, ?)"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, 0); 
				 preparedStmt.setString(2, accountNo); 
				 preparedStmt.setString(3, areaOffice); 
				 preparedStmt.setInt(4, Integer.parseInt(customerID)); 
				 preparedStmt.setInt(5, Integer.parseInt(accountTypeID));  
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newAccounts = readIAccounts(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}";
				 //output = "Inserted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				//output = "Error while inserting"; 
				 output = "{\"status\":\"error\", \"data\": \"Error while inserting the account.\"}"; 
				 System.err.println(e.getMessage());
			 } 
			 return output; 
		 } 
		 
		 public String readIAccounts() 
		 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for reading."; 
				 }
				 
				 // Prepare the html table to be displayed
				 output = "<table border='1'><tr><th>Account No</th>" +
				 "<th>Area Office</th>" + 
				 "<th>Customer ID</th>" +
				 "<th>Account Type ID</th>" +
				 "<th>Update</th><th>Remove</th>"+
				 "</tr>"; 
				 
				 String query = "select * from accounts"; 
				 Statement stmt = con.createStatement(); 
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 // iterate through the rows in the result set
				 while (rs.next()) 
				 { 
					 String accountID = Integer.toString(rs.getInt("accountID")); 
					 String accountNo = rs.getString("accountNo"); 
					 String areaOffice = rs.getString("areaOffice");  
					 String customerID = Integer.toString(rs.getInt("customerID")); 
					 String accountTypeID = Integer.toString(rs.getInt("accountTypeID")); 
					 
					 
					 // Add into the html table
					// output += "<tr><td>" + accountID + "</td>"; 
					 output += "<tr><td>" + accountNo + "</td>"; 
					 output += "<td>" + areaOffice + "</td>"; 
					 output += "<td>" + customerID + "</td>"; 
					 output += "<td>" + accountTypeID + "</td>"; 
					 
					 // buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'"
							 + "data-accountid='" + accountID + "'></td>"
							  + "<td><input name='btnRemove' type='button' value='Remove' "
							  + "class='btnRemove btn btn-danger' data-accountid='" + accountID + "'></td></tr>";
				 } 
				 
				 con.close();  
				 
				 // Complete the html table
				 output += "</table>"; 
			 } 
			 catch (Exception e) 
			 { 
				 output = "Error while reading the account types."; 
				 System.err.println(e.getMessage()); 
			 } 
			 return output; 
		 } 
		 
		 public String updateAccount(String ID, String accountNo, String areaOffice, String customerID, String accountTypeID) 
		 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for updating."; 
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE accounts SET accountNo=?,areaOffice=?,customerID=?,accountTypeID=? WHERE accountID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values 
				 preparedStmt.setString(1, accountNo); 
				 preparedStmt.setString(2, areaOffice); 
				 preparedStmt.setInt(3, Integer.parseInt(customerID)); 
				 preparedStmt.setInt(4, Integer.parseInt(accountTypeID));
				 preparedStmt.setInt(5, Integer.parseInt(ID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newAccounts = readIAccounts(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}"; 
				 //output = "Updated successfully";  
			 } 
			 catch (Exception e) 
			 { 
				//output = "Error while updating the item.";
				 output = "{\"status\":\"error\", \"data\":\"Error while updating the account.\"}";
				 System.err.println(e.getMessage());  
			 } 
			 return output; 
		 } 
		 
		 public String deleteAccounts(String accountID) 
		 { 
			 String output = ""; 
			 
			 try
			 { 
				 Connection con = connect(); 
				 
				 if (con == null) 
				 {
					 return "Error while connecting to the database for deleting."; 
				 }
				 
				 // create a prepared statement
				 String query = "delete from accounts where accountID=?"; 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				 
				 // binding values
				 preparedStmt.setInt(1, Integer.parseInt(accountID)); 
				 
				 // execute the statement
				 preparedStmt.execute(); 
				 con.close(); 
				 
				 String newAccounts = readIAccounts(); 
				 output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}"; 
				 //output = "Deleted successfully"; 
			 } 
			 catch (Exception e) 
			 { 
				//output = "Error while deleting the account."; 
				 output = "{\"status\":\"error\", \"data\": \"Error while deleting the account.\"}"; 
				 System.err.println(e.getMessage());
			 } 
			 return output; 
		 } 
}