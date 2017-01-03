package com.certapp.bo;

import java.math.BigDecimal;

public class SARecieptAccount {

	private String accountNumber;
	
	private BigDecimal topUpLimit;
	
	private BigDecimal cpfAmount;
	
	private BigDecimal cashAmount;
	
	public SARecieptAccount() { }

	/**
	 * Testing 
	 * 
	 * @param accountNumber
	 * @param topUpLimit
	 * @param cpfAmount
	 * @param cashAmount
	 */
	public SARecieptAccount(String accountNumber, BigDecimal topupLimit) {
		this.accountNumber = accountNumber;
		
		this.topUpLimit = topupLimit;
		this.cpfAmount = new BigDecimal(0);
		this.cashAmount = new BigDecimal(0);
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the topUpLimit
	 */
	public BigDecimal getTopUpLimit() {
		return topUpLimit;
	}

	/**
	 * @param topUpLimit the topUpLimit to set
	 */
	public void setTopUpLimit(BigDecimal topUpLimit) {
		this.topUpLimit = topUpLimit;
	}

	/**
	 * @return the cpfAmount
	 */
	public BigDecimal getCpfAmount() {
		return cpfAmount;
	}

	/**
	 * @param cpfAmount the cpfAmount to set
	 */
	public void setCpfAmount(BigDecimal cpfAmount) {
		this.cpfAmount = cpfAmount;
	}

	/**
	 * @return the cashAmount
	 */
	public BigDecimal getCashAmount() {
		return cashAmount;
	}

	/**
	 * @param cashAmount the cashAmount to set
	 */
	public void setCashAmount(BigDecimal cashAmount) {
		this.cashAmount = cashAmount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SARecieptAccount [accountNumber=" + accountNumber
				+ ", topUpLimit=" + topUpLimit + ", cpfAmount=" + cpfAmount
				+ ", cashAmount=" + cashAmount + "]";
	}	
}
