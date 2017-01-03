package com.action.directory;

public class InFlowAction1 implements ActionDispatcher {

	@Override
	public void fire() {
		System.out.println("Action 1 is trigger and fire aways!");
	}

}
