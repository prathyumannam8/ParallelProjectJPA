package com.cg.bankwallet.dao;

public interface IQueryMapper {
	public String insert="insert into account1(name,email,phoneNumber,balance) values(?,?,?,?)";
	public String getBal="select balance from account1 where phoneNumber=?";
	public String getacc="select * from account1 where phoneNumber=?";
	public String update="update account1 set balance=?,date1=? where phoneNumber=?";
}
