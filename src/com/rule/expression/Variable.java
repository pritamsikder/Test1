package com.rule.expression;

import java.util.Map;

public class Variable implements Expression {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	@Override
	public boolean interpret(Map<String, ?> bindings) {
		// TODO Auto-generated method stub
		return true;
	}
}
