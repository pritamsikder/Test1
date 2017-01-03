package com.certapp.de;

import java.math.BigDecimal;

import com.certapp.bo.SARecieptAccount;

public class SARecipentAccountDE {

	private String accountNumber;
	private BigDecimal topupLimit;
	
	public SARecipentAccountDE() { }
	
	public SARecipentAccountDE(String accountNumber, BigDecimal topupLimit) {
		this.accountNumber = accountNumber;
		this.topupLimit = topupLimit;
	}
	
	public SARecieptAccount invoke() {
		SARecieptAccount account = new SARecieptAccount(accountNumber, topupLimit);
		account.setTopUpLimit(new BigDecimal(1000));
		
		return account;
	}
}