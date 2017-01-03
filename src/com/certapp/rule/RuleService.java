package com.certapp.rule;

import java.math.BigDecimal;

import com.certapp.bo.SARecieptAccount;

public class RuleService {
	
	private BigDecimal cpfAmount;
	private BigDecimal cashAmount;
	private SARecieptAccount recepientAccount;
	
	public RuleService() {};
	
	public RuleService(BigDecimal cpfAmount, BigDecimal cashAmount, SARecieptAccount recepientAccount) {
		this.cpfAmount = cpfAmount;
		this.cashAmount = cashAmount;
		this.recepientAccount = recepientAccount;
	}
	
	public boolean invoke() {
		System.out.print(cpfAmount + " + " + cashAmount + " = ");
		BigDecimal totalAmount = cpfAmount;
		totalAmount = totalAmount.add(cashAmount);
		
		System.out.println(totalAmount);
		if (totalAmount.compareTo(recepientAccount.getTopUpLimit()) > 0) {
			return true;
		} else {
			return false;
		}
	}
}
