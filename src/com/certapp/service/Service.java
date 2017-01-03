package com.certapp.service;

import java.util.ArrayList;

/**
 * Service object for entity matching modules, data extension modules, 
 * rule modules and action modules to be invoked during the process 
 * module controller
 * 
 * @author quee.leong.lee
 * Created on 04/10/2016
 */
public class Service {
	
	/** Class object for the service */
	private Class<?> clazz;
	/** List of input parameters */
	private ArrayList<Class<?>> inputs;
	/** List of output parameters */
	private ArrayList<Class<?>> outputs;

	/**
	 * Constructor that instantiate Service object with the following parameters
	 * @param serviceId			ID of the service
	 * @param serviceType		Type of the service (Entity Matching, Data Extension, Rule Modules and Action)
	 * @param description		Description of the service
	 * @param className			Name of the class for the service
	 * @param methodName		Name of the method to invoke if any
	 * @param inputs				List of input parameters
	 * @param outputs			List of output parameters
	 * @throws ClassException 	Class Exception Encountered
	 */
	public Service(String className, ArrayList<Class<?>> inputs, ArrayList<Class<?>> outputs) {
		
		try {
			this.clazz = Class.forName(className);
			
			this.inputs = inputs;
			this.outputs = outputs;
			
		} catch (IllegalArgumentException | ArrayIndexOutOfBoundsException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Retrieve the class object for the service
	 * @return the class object for the service
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * Retrieve all input parameters for the service
	 * @return a array list that contains all input parameters for the service
	 */
	public ArrayList<Class<?>> getInputs() {
		return inputs;
	}
	
	/**
	 * Retrieve the size of input parameters
	 * @return Integer value that contains the size of input parameters
	 */
	public int getInputSize() {
		return inputs.size();
	}
	
	/**
	 * Retrieve all output parameters for the service
	 * @return a array list that contains all output parameters for the service
	 */
	public ArrayList<Class<?>> getOutputs() {
		return outputs;
	}
	
	/**
	 * Retrieve the size of output parameters
	 * @return	Integer value that contains the size of output parameters
	 */
	public int getOutputSize() {
		return outputs.size();
	}

	/**
	 * Enumerator class for the different type of service available
	 * @author quee.leong.lee
	 * Created on 04/10/2016
	 */
	public enum ServiceType {
		EM, DE, Rule, Action
	}
}