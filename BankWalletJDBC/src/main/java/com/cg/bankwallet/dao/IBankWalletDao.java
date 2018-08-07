package com.cg.bankwallet.dao;

import com.cg.bankwallet.beans.Account;
import com.cg.bankwallet.exception.BankWalletException;

public interface IBankWalletDao {
	String createAccount(Account acc) throws BankWalletException;
	double showBalance(String mobileNo) throws BankWalletException;
	Account deposit(String mobileNo, double depositAmt) throws BankWalletException;
	Account withdraw(String mobileNo, double withdrawAmt) throws BankWalletException;
	Account printTransactionDetails(String mobileNo) throws BankWalletException;
	
}
