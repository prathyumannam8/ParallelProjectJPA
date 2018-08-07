package com.cg.bankwallet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



import com.cg.bankwallet.exception.BankWalletException;

public class BankWalletDb {
	public static Connection getConnection() throws BankWalletException{
		String url="jdbc:mysql://localhost:3306/jpa";
		try{
		//Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url,"root","root");
		}catch(ClassNotFoundException e){
		throw new BankWalletException(e.getMessage());
		}catch(SQLException e1){
		throw new BankWalletException(e1.getMessage());
		} 
	
	}
}
	
