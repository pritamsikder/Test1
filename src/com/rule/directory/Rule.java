package com.rule.directory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.action.directory.ActionDispatcher;
import com.action.directory.NullActionDispatcher;
import com.rule.expression.Expression;

public class Rule {
	private String ruleName;
	private List<Expression> expressions;
	private ActionDispatcher dispatcher;
	
	public static class Builder {
		
		private String ruleName = "No Name";
		private List<Expression> expressions = new ArrayList<Expression>();
		private ActionDispatcher dispatcher = new NullActionDispatcher();
				
		public Builder setName(String ruleName) {
			this.ruleName = ruleName;
			return this;
		}
		
		public Builder withExpression(Expression expr) {
			expressions.add(expr);
			return this;
		}
		
		public Builder withDispatcher(ActionDispatcher dispatcher) {
			this.dispatcher = dispatcher;
			return this;
		}
		
		public Rule build() {
			return new Rule(ruleName, expressions, dispatcher);
		}
	}
	
	private Rule(String ruleName, List<Expression> expressions, ActionDispatcher dispatcher) {
		this.ruleName = ruleName;
		this.expressions = expressions;
		this.dispatcher = dispatcher;
	}
	
	public String getRuleName() {
		return ruleName;
	}
	
	public boolean eval(Map<String, ?> bindings) {
		boolean eval = false;
		for (Expression expression : expressions) {
			eval = expression.interpret(bindings);
			
			if (eval)
				dispatcher.fire();
		}
		
		return eval;
	}
}