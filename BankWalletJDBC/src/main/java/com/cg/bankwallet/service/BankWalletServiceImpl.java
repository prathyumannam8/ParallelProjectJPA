package com.cg.bankwallet.service;

import com.cg.bankwallet.beans.Account;
import com.cg.bankwallet.dao.BankWalletDaoImpl;
import com.cg.bankwallet.dao.IBankWalletDao;
import com.cg.bankwallet.exception.BankWalletException;
/*
 * Class Name: BankWalletServiceImpl
 * Interface Implemented: IBankWalletService
 * No. of methods: 6
 * Author: @mprathyu
 * Date Of Creation: 23th JULY 2018
 * Last Date of modification: 4th AUGUST 2018
 */
public class BankWalletServiceImpl implements IBankWalletService {
	IBankWalletDao dao = new BankWalletDaoImpl();

	public String createAccount(Account account) throws BankWalletException {
		if (!account.getMobileNo().matches("\\d{10}")) {
			throw new BankWalletException("Mobile Number should contain 10 digits");
			}
			if (account.getName().isEmpty() || account.getName() == null) {
			throw new BankWalletException("Name can't be empty");
			} else {
			if (!account.getName().matches("[A-Z][A-Za-z]{3,}")) {
			throw new BankWalletException("Name should start with capital letter and should contain only Alphabets");
			}
			}
			if (account.getBalance() < 0) {
			throw new BankWalletException("Balance should be greater than zero");
			}
			if (!account.getEmail().matches("[a-z]+@[a-z]+\\.com")) {
			throw new BankWalletException("Enter valid emailid");
			}
			return dao.createAccount(account);
	}

	public double showBalance(String mobileNo) throws BankWalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new BankWalletException("Mobile number should contain 10 digits");
			}
			return dao.showBalance(mobileNo);
	}

	public Account printTransactionDetails(String mobileNo) throws BankWalletException {
		return dao.printTransactionDetails(mobileNo);

	}

	public Account deposit(String mobileNo, double depositAmt) throws BankWalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new BankWalletException("Mobile number should contain 10 digits");
			}
			if (depositAmt <= 0) 
			{
			throw new BankWalletException("Deposit amount must be greater than zero");
			}			 
			return dao.deposit(mobileNo,depositAmt);	
	}

	public Account withdraw(String mobileNo, double withdrawAmt) throws BankWalletException {
		if (!mobileNo.matches("\\d{10}")) {
			throw new BankWalletException("Mobile number should contain 10 digits");
			}			
			if ( withdrawAmt <= 0) {
			throw new BankWalletException("The amount to be withdrawn should be greater than available balance and greater than zero");
			}			
			Account account1 = dao.withdraw(mobileNo,withdrawAmt);
			return account1;
	}

	public boolean fundTransfer(String sourceMobileNo, String destMobileNo, double transferAmt)
			throws BankWalletException {
		if (!sourceMobileNo.matches("\\d{10}")) {
			throw new BankWalletException("Mobile number should contain 10 digits");
			}
			if (!destMobileNo.matches("\\d{10}")) {
			throw new BankWalletException("Mobile number should contain 10 digits");
			}
			return true;
	}
		
	
}
