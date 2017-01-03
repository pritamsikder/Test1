/**
 * 
 */
package com.rule.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.action.directory.ActionDispatcher;
import com.action.directory.InFlowAction1;
import com.action.directory.InFlowAction2;
import com.rule.directory.Rule;
import com.rule.expression.Expression;
import com.rule.expression.factory.Operations;
import com.rule.expression.operation.And;
import com.rule.expression.operation.Equal;
import com.rule.expression.operation.Not;
import com.rule.parser.ExpressionParser;

/**
 * 
 * Created on Dec 22, 2016
 *
 * @author Accenture
 */
public class MainExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Create a singleton container for operations
		Operations operations = Operations.INSTANCE;
		
		// Register operations with the previously created container
		operations.registerOperation(new And());
		operations.registerOperation(new Equal());
		operations.registerOperation(new Not());
		
		// Defines the triggers when a rule should fire
		Expression ex3 = ExpressionParser.fromString("PATIENT_TYPE = 'A' AND NOT ADMISSION_TYPE = 'O'");
        Expression ex1 = ExpressionParser.fromString("PATIENT_TYPE = 'A' AND ADMISSION_TYPE = 'O'");
        Expression ex2 = ExpressionParser.fromString("PATIENT_TYPE = 'B'");
        
        // Define the possible action for rules that can fire
        ActionDispatcher action1 = new InFlowAction1();
        ActionDispatcher action2 = new InFlowAction2();
        
        // Creates rules and link them to the corresponding expression and actions
        Rule rule1 = new Rule.Builder()
        					.setName("Action 1")
        					.withExpression(ex1)
        					.withDispatcher(action1)
        					.build();
        
        Rule rule2 = new Rule.Builder()
        					.setName("Action 2")
        					.withExpression(ex2)
        					.withExpression(ex3)
        					.withDispatcher(action2)
        					.build();
        
        // Add all rules into one single container
        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(rule1);
        rules.add(rule2);
        
        // For text purpose define a variable binding
        Map<String, String> bindings = new HashMap<String, String>();
        bindings.put("PATIENT_TYPE", "'A'");
        bindings.put("ADMISSION_TYPE", "'O'");
        
        for (Rule rule : rules) {
        	boolean triggered = rule.eval(bindings);
            System.out.println(rule.getRuleName() + " triggered: " + triggered);
        }
	}
}
