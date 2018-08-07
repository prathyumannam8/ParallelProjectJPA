package com.cg.bankwallet.beans;

import java.sql.Date;
/*
 * Class Name:Account 
 * Author: @mprathyu
 * Date Of Creation: 23th JULY 2018
 * Last Date of modification: 4th AUGUST 2018
 */

public class Account {
	private String name;
	private String mobileNo; 
	private String email;
    private double balance;
    Date date;
  //Getters & Setters 
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	//Constructor
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(String mobileNo, String name, String email, double balance,
			Date date) {
		super();
		this.mobileNo = mobileNo;
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.date = date;
	}
	
}	
		
	
	
	
	

