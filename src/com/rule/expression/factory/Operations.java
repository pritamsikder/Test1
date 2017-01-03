package com.rule.expression.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.rule.expression.Operation;

public enum Operations {
	/** Application of the singleton pattern using enum */
	INSTANCE;
	
	private final Map<String, Operation> operations = new HashMap<>();
	
	public void registerOperation(Operation operation, String symbol) {
		if (!operations.containsKey(symbol))
			operations.put(symbol, operation);
	}
	
	public void registerOperation(Operation operation) {
		if (!operations.containsKey(operation.getSymbol()))
			operations.put(operation.getSymbol(), operation);
	}
	
	public Operation getOperation(String symbol) {
		return operations.get(symbol);
	}
	
	public Set<String> getDefinedSymbol() {
		return operations.keySet();
	}
}
