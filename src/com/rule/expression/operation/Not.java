package com.rule.expression.operation;

import java.util.Map;
import java.util.Stack;

import com.rule.expression.Expression;
import com.rule.expression.Operation;

public class Not extends Operation {

	public Not() {
		super("NOT");
	}

	@Override
	public boolean interpret(Map<String, ?> bindings) {
		return !this.rightOperand.interpret(bindings);
	}

	@Override
	public Not copy() {
		// TODO Auto-generated method stub
		return new Not();
	}

	@Override
	public int parse(String[] tokens, int pos, Stack<Expression> stack) {
		int i = findNextExpression(tokens, pos+1, stack);
		Expression right = stack.pop();
		
		this.rightOperand = right;
		stack.push(this);
		
		return i;
	}

}
