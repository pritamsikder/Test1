package com.rule.expression;

import java.util.Stack;

import com.rule.expression.factory.Operations;

public abstract class Operation implements Expression {

	protected String symbol;
	
	protected Expression leftOperand = null;
	protected Expression rightOperand = null;
	
	public Operation(String symbol) {
		this.symbol = symbol;
	}
	
	public abstract Operation copy();

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}
	
	public abstract int parse(final String[] tokens, final int pos, final Stack<Expression> stack);
	
	protected Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack) {
		Operations operations = Operations.INSTANCE;
		
		for (int i = pos; i < tokens.length; i++) {
			Operation operation = operations.getOperation(tokens[i]);
			
			if (operation != null) {
				operation = operation.copy();
				
				// Found an operation
				i = operation.parse(tokens, i, stack);
				
				return i;
			}
		}
		
		return null;
		
	}
}
