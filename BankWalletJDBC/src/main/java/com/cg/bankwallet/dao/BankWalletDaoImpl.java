package com.cg.bankwallet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

import com.cg.bankwallet.beans.Account;
import com.cg.bankwallet.db.BankWalletDb;
import com.cg.bankwallet.exception.BankWalletException;
/*
 * Class Name: BankWalletDaoImpl
 * Interface Implemented: IBankWalletDao
 * No. of methods: 5
 * Author: @mprathyu
 * Date Of Creation: 23th JULY 2018
 * Last Date of modification: 4th AUGUST 2018
 */
public class BankWalletDaoImpl implements IBankWalletDao {

	public String createAccount(Account acc) throws BankWalletException {
		Connection con=BankWalletDb.getConnection();
		PreparedStatement stat;
		try{
		con.setAutoCommit(false);
		stat = con.prepareStatement(IQueryMapper.insert);
		stat.setString(1, acc.getName());
		stat.setString(2, acc.getEmail());
		stat.setString(3, acc.getMobileNo());
		stat.setDouble(4, acc.getBalance());
		int res=stat.executeUpdate();
		if(res==1){
		con.commit();
		return acc.getMobileNo();
		}else{
		throw new BankWalletException("Could not create account");
		}
		 
		} 
		catch (SQLException e) {
				throw new BankWalletException(e.getMessage());
		}		 
		}	
	

    public double showBalance(String mobileNo) throws BankWalletException {
    	Connection con=BankWalletDb.getConnection();
		PreparedStatement stat;
		try{
		stat=con.prepareStatement(IQueryMapper.getBal);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		con.commit();
		if(rs!=null){
		rs.next();
		return rs.getDouble("balance");
		}else{
		throw new BankWalletException("mobile no does not exists");
		}		 
		}
		catch (SQLException e) {
				throw new BankWalletException(e.getMessage());
		} 	
	}

	public Account deposit(String mobileNo, double depositAmt) throws BankWalletException {
		Connection con=BankWalletDb.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{		 
		stat=con.prepareStatement(IQueryMapper.getacc);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		if(rs!=null){
		rs.next();
		Account account1=new Account();
		double balance=rs.getDouble("balance")+depositAmt;
		account1.setName(rs.getString("name"));
		account1.setMobileNo(rs.getString("mobileno"));
		account1.setBalance(balance);
		account1.setEmail(rs.getString("email"));
		account1.setDate(Date.valueOf(LocalDate.now()));		 
		stat1=con.prepareStatement(IQueryMapper.update);
		stat1.setDouble(1, account1.getBalance());
		stat1.setDate(2, account1.getDate());
		stat1.setString(3, account1.getMobileNo());
		int rs1=stat1.executeUpdate();		 
		if(rs1==1)
		{		 
		con.commit();		 
		return account1;
		}
		else{
		throw new BankWalletException("balance is not updated");
		}		 
		}
		else{
		throw new BankWalletException("mobile no does not exists");
		}		 
		}
		catch (SQLException e) {
				throw new BankWalletException(e.getMessage());
		}		
	}

	public Account withdraw(String mobileNo, double withdrawAmt) throws BankWalletException {
		Connection con=BankWalletDb.getConnection();
		PreparedStatement stat;
		PreparedStatement stat1;
		try{		 
		stat=con.prepareStatement(IQueryMapper.getacc);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		if(rs!=null){
		rs.next();
		Account account=new Account();
		double balance=rs.getDouble("balance")-withdrawAmt;
		account.setName(rs.getString("name"));
		account.setMobileNo(rs.getString("phoneNumber"));
		account.setBalance(balance);
		account.setEmail(rs.getString("email"));
		account.setDate(Date.valueOf(LocalDate.now()));
		stat1=con.prepareStatement(IQueryMapper.update);
		stat1.setDouble(1, account.getBalance());
		stat1.setDate(2, account.getDate());
		stat1.setString(3, account.getMobileNo());
		int rs1=stat1.executeUpdate();		 
		if(rs1==1){
		con.commit();
		return account;
		}else{
		throw new BankWalletException("mobile no does not exists");
		}
		}
		else{
		throw new BankWalletException("mobile no does not exists");
		}		 
		}
		catch (SQLException e) {
		throw new BankWalletException(e.getMessage());
		}		
	}

	public Account printTransactionDetails(String mobileNo) throws BankWalletException {
		Connection con=BankWalletDb.getConnection();
		PreparedStatement stat;
		try{
		stat=con.prepareStatement(IQueryMapper.getacc);
		stat.setString(1, mobileNo);
		ResultSet rs=stat.executeQuery();
		con.commit();
		if(rs!=null){
		rs.next();
		Account account=new Account();
		account.setName(rs.getString("name"));
		account.setMobileNo(rs.getString("mobileno"));
		account.setEmail(rs.getString("email"));
		account.setBalance(rs.getDouble("balance"));
		account.setDate(rs.getDate("date1"));
		return account;
		}else{
		throw new BankWalletException("mobile no does not exists");
		}		 
		}
		catch (SQLException e) {		
		throw new BankWalletException(e.getMessage());
		}
		 
	}

	
	}



