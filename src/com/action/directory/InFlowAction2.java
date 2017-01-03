/**
 * 
 */
package com.action.directory;

/**
 * 
 * Created on Dec 22, 2016
 *
 * @author Accenture
 */
public class InFlowAction2 implements ActionDispatcher {

	/* (non-Javadoc)
	 * @see com.rule.directory.ActionDispatcher#fire()
	 */
	@Override
	public void fire() {
		System.out.println("Action 2 is trigger and fire aways!");
	}
}
