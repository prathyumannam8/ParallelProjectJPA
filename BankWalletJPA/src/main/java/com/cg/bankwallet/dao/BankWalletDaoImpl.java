package com.cg.bankwallet.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.bankwallet.bean.Account;
import com.cg.bankwallet.exception.BankWalletException;

public class BankWalletDaoImpl implements IBankWalletDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hi");	 
	EntityManager em = emf.createEntityManager();

	public String createAccount(Account account) throws BankWalletException {
		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
		return account.getMobileNo();
	}

	public double showBalance(String mobileNo) throws BankWalletException {
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(mobileNo.equals(ac.getMobileNo())) {
		return ac.getBalance();
		}else {
		throw new BankWalletException("number doesnot exists");
		}
	}

	public Account deposit(String mobileNo, double depositAmt) throws BankWalletException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		throw new BankWalletException("does not exists");
		}
		double d=ac.getBalance()+depositAmt;
		ac.setBalance(d);
		em.merge(ac);
		 
		 
		em.getTransaction().commit();
		return ac;
		 
	}

	public Account withdraw(String mobileNo, double withdrawAmt) throws BankWalletException {
		em.getTransaction().begin();
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		throw new BankWalletException("does not exists");
		}
		double d=ac.getBalance()-withdrawAmt;
		ac.setBalance(d);
		em.merge(ac);
		 
		 
		em.getTransaction().commit();
		return ac;
	}

	public Account printTransactionDetails(String mobileNo) throws BankWalletException {
		String str="select a from Account a where a.mobileno=?";
		TypedQuery<Account> query=em.createQuery(str,Account.class);
		query.setParameter(1,mobileNo);
		Account ac=query.getSingleResult();
		if(ac==null) {
		return ac;
		}else {
		throw new BankWalletException("number doesnot exists");
		}
		}

	
	

}
