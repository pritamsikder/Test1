package com.rule.expression.operation;

import java.util.Map;
import java.util.Stack;

import com.rule.expression.BaseType;
import com.rule.expression.Expression;
import com.rule.expression.Operation;
import com.rule.expression.Variable;

public class Equal extends Operation {

	public Equal() {
		super("=");
	}

	@Override
	public boolean interpret(Map<String, ?> bindings) {
		Variable var = (Variable) this.leftOperand;
		Object obj = bindings.get(var.getName());
		
		if (obj == null)
			return false;
		
		BaseType<?> type = (BaseType<?>) this.rightOperand;
		if (type.getType().equals(obj.getClass())) {
			
			if (type.getValue().equals(obj))
				return true;
		}
		
		return false;
	}

	@Override
	public Equal copy() {
		return new Equal();
	}

	@Override
	public int parse(String[] tokens, int pos, Stack<Expression> stack) {
		if (pos-1 >= 0 && tokens.length >= pos+1) {
			String var = tokens[pos-1];
			
			this.leftOperand = new Variable(var);
			this.rightOperand = BaseType.getBaseType(tokens[pos+1]);
			stack.push(this);
			
			return pos+1;
		}
		
		throw new IllegalArgumentException("Cannot assign value to variable");
	}
}
