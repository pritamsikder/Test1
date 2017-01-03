package com.certapp.general;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.certapp.bo.SARecieptAccount;
import com.certapp.service.Service;

public class ServiceDistributer {
	
	public static Service getServiceClass(String className) {
		BOClass boCLassName = BOClass.valueOf(className);
		
		switch (boCLassName) {
			case RuleService:
				ArrayList<Class<?>> ruleInputs = new ArrayList<Class<?>>();
				ruleInputs.add(BigDecimal.class);
				ruleInputs.add(BigDecimal.class);
				ruleInputs.add(SARecieptAccount.class);
				
				ArrayList<Class<?>> ruleOutputs = new ArrayList<Class<?>>();
				ruleOutputs.add(Boolean.class);
				
				return new Service("com.certapp.rule.RuleService", ruleOutputs, ruleOutputs);
				
			case SARecieptAccount:
				ArrayList<Class<?>> deInputs = new ArrayList<Class<?>>();
				deInputs.add(String.class);
				deInputs.add(BigDecimal.class);
				
				ArrayList<Class<?>> deOutputs = new ArrayList<Class<?>>();
				deOutputs.add(SARecieptAccount.class);
				
				return new Service("com.certapp.de.SARecipentAccountDE", deInputs, deOutputs);

			default:
				return null;
		}
	}
	
	public enum BOClass {
		RuleService, SARecieptAccount
	}
}
