package com.cg.bankwallet.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cg.bankwallet.bean.Account;
import com.cg.bankwallet.exception.BankWalletException;
import com.cg.bankwallet.service.BankWalletServiceImpl;
import com.cg.bankwallet.service.IBankWalletService;



public class BankWalletTest {


	private IBankWalletService service;

	@Before

	public void init() {
		service = new BankWalletServiceImpl();
	}

  //Test 1
	@Test
	public void testCreateAccountForMobile() {
		Account ac = new Account();
		ac.setMobileNo("1234897565");
		ac.setName("Marria");
		ac.setEmail("marria@cg.com");
		ac.setBalance(200.0);
		try {
			service.createAccount(ac);
		} catch (BankWalletException e) {
			assertEquals("Mobile number should contain 10 digits", e.getMessage());
		}
	}
	
	//Test 2
	@Test
	public void testCreateAccountForName() {
		Account ac = new Account();
		ac.setMobileNo("8185800166");
		ac.setName("Miya12");
		ac.setEmail("miya@cg.com");
		ac.setBalance(500.0);
		try {
			service.createAccount(ac);
		} catch (BankWalletException e) {
			assertEquals("Name should start with capital letter and should contain only Alphabets", e.getMessage());
		}
	}
	
	//Test 3
	@Test
	public void testCreateAccountForNameIsEmpty() {
		Account ac = new Account();
		ac.setMobileNo("8185800167");
		ac.setName("");
		ac.setEmail("Keira@cg.com");
		ac.setBalance(200.0);
		try {
			service.createAccount(ac);
		} catch (BankWalletException e) {
			assertEquals("Name can't be empty", e.getMessage());
		}
	}	
	//Test 4
	@Test
	public void testCreateAccountForEmailId() {
		Account ac = new Account();
		ac.setMobileNo("9948032465");
		ac.setName("Benji");
		ac.setEmail("benji@cg.com");
		ac.setBalance(200.0);
		try {
			service.createAccount(ac);
		} catch (BankWalletException e) {
			assertEquals("Enter valid emailid", e.getMessage());
		}
	}
	//Test5
	@Test
	public void testCreateAccount() {
		Account ac = new Account();
		ac.setMobileNo("8185800165");
		ac.setName("Judy");
		ac.setEmail("judy@gmail.com");
		ac.setBalance(200.0);
			try {
				String s=service.createAccount(ac);
				assertNotNull(s);
			} catch (BankWalletException e) {
						

			}
	}	
	//Test 6
	@Test
	public void testShowBalanceForMobileNo() {
		
		try {
			service.showBalance("818580");
		} catch (BankWalletException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}	

	//Test 7
	@Test
	public void testShowBalanceForMobileNoDoesNotExist() {
		
		try {
			service.showBalance("818580166");
		} catch (BankWalletException e) {			
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	//Test 8
	@Test
	public void testDepositForMobileNo() {
		Account ac=new Account();
		ac.setMobileNo("818580016");
		try {
			service.deposit(ac.getMobileNo(), 230);
		} catch (BankWalletException e) {			
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	//Test 9
	@Test
	public void testDepositForDepositAmt1() {
		Account ac=new Account();
		ac.setMobileNo("8185800166");
		try {
			service.deposit(ac.getMobileNo(), -230);
		} catch (BankWalletException e) {
			assertEquals("Deposit amount must be greater than zero",e.getMessage());
		}
	}	
	
	//Test 10
	@Test
	public void testWithDrawForMobileNo() {
		Account ac=new Account();
		ac.setMobileNo("818580016");
		try {
			service.withdraw(ac.getMobileNo(), 230);
		} catch (BankWalletException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	
	//Test 11
	@Test
	public void testWithdrawForAmt() {
		Account ac=new Account();
		ac.setMobileNo("8185800166");
		try {
			service.withdraw(ac.getMobileNo(), -230);
		} catch (BankWalletException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	//Test 12
	@Test
	public void testWithdrawForAmt1() {
		Account ac=new Account();
		ac.setMobileNo("9948032465");
		try {
			service.withdraw(ac.getMobileNo(), -230);
		} catch (BankWalletException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	//Test 13
	@Test
	public void testWithdrawForAmt2() {
		Account ac=new Account();
		ac.setMobileNo("8185800165");
		try {
			service.withdraw(ac.getMobileNo(), -230);
		} catch (BankWalletException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}	
	//Test 14
	@Test
	public void testWithdrawForAmt3() {
		Account ac=new Account();
		ac.setMobileNo("8185800167");
		try {
			service.withdraw(ac.getMobileNo(), -230);
		} catch (BankWalletException e) {
			assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	//Test 15
	@Test
	public void testFundTransferForMobileNo() {
		Account ac=new Account();
		Account ac2=new Account();
		ac.setMobileNo("818580");
		ac2.setMobileNo("1234");
		try {
			service.fundTransfer(ac.getMobileNo(),ac2.getMobileNo(), 230);
		} catch (BankWalletException e) {
			assertEquals("Mobile number should contain 10 digits",e.getMessage());
		}
	}
	
	//Test 16
	@Test
	public void testFundTransferForAmt() {
		Account ac=new Account();
		Account ac2=new Account();
		ac.setMobileNo("8185800166");
		ac2.setMobileNo("8185800165");
		try {
			service.fundTransfer(ac.getMobileNo(), ac2.getMobileNo(),  -230);
		} catch (BankWalletException e) {
		assertEquals("The amount to be withdrawn should be greater than available balance and greater than zero",e.getMessage());
		}
	}
	


}
