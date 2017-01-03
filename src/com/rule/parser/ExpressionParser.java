package com.rule.parser;

import java.util.Stack;

import com.rule.expression.Expression;
import com.rule.expression.Operation;
import com.rule.expression.factory.Operations;

public class ExpressionParser {

	private static final Operations operations = Operations.INSTANCE;
	
	public static Expression fromString(String expr) {
		Stack<Expression> stack = new Stack<Expression>();
		
		String[] tokens = expr.split("\\s");
		for (int i = 0; i < tokens.length-1; i++) {
			Operation operation = operations.getOperation(tokens[i]);
			
			if (operation != null) {
				operation = operation.copy();
				i = operation.parse(tokens, i, stack);
			}
		}
		
		return stack.pop();
	}

}
