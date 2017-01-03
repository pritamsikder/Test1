package com.action.directory;

public class NullActionDispatcher implements ActionDispatcher {

	@Override
	public void fire() {
		System.out.println("Validation has failed!");
	}
}
