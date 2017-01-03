package com.certapp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.certapp.general.ServiceDistributer;
import com.certapp.service.Service;
import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;

public class MainExecutor {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.format("%s%n", "Invoke RuleService: ");
			
			// Get class
			Service ruleService = ServiceDistributer.getServiceClass("RuleService"); 
			Class<?> clzz = ruleService.getClazz();
			
			// Get user inputs to invoke services
			ArrayList<Object> objectList = insertUserData(clzz, br);

			// Instantiate service class and invoke method
			Object obj = formatObject(clzz, objectList);
	
			Method method = clzz.getMethod("invoke");
			System.out.println("Output of the Service (com.certapp.rule.RuleService) : " + method.invoke(obj));
			
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} 
	}
	
	public static ArrayList<Object> insertUserData(Class<?> clzz, BufferedReader br) {
		ArrayList<Object> objectList = new ArrayList<Object>();
		
		try {
			// Retrieve all constructors 
			Constructor<?>[] allConstructor = clzz.getDeclaredConstructors();
			
			for (Constructor<?> constructor : allConstructor) {
				// Get all parameter types for the constructor
				Class<?>[] argumentType = constructor.getParameterTypes();

				// TODO: Pass in the service object itself to validate the list of input data type against the constructors found in the class
				if (argumentType.length > 0) {
					
					// Get parameter names assigned in the constructor
					Paranamer info = new CachingParanamer(new AnnotationParanamer(new BytecodeReadingParanamer()));
					String[] parameterNames = info.lookupParameterNames(constructor);
					
					// Output parameter names on console for user input
					for (int i = 0; i < argumentType.length; i++) {
						
						Class<?> dataClass = argumentType[i];
						
						if (dataClass.getPackage().getName().contains("com.certapp.bo")) {		// If data type is a business object
							
							System.out.format("\t%s:\n ", formatVariableName(parameterNames[i]));
							
							// Retrieve the corresponding DE service to retrieve business object
							Service deService = ServiceDistributer.getServiceClass(dataClass.getSimpleName());
							
							// Get user inputs to invoke DE service
							ArrayList<Object> argList = insertUserData(deService.getClazz(), br);
							objectList.add(invokeDEService(deService, argList));
									
						} else { 	// If data type is native

							System.out.format("\t\t%s: ", formatVariableName(parameterNames[i]));
							String input = br.readLine();
							
							objectList.add(getNativeParameter(argumentType[i], input));
						}
					}
					
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return objectList;
	}
	
	public static Object formatObject(Class<?> dataClass, ArrayList<Object> argList) {
		try {
			Class<?>[] classList = new Class<?>[argList.size()];
			Object[] valueList = new Object[argList.size()];
			
			for (int i = 0; i < argList.size(); i++) {
				Object obj = argList.get(i);
				
				classList[i] = obj.getClass();
				valueList[i] = obj;
			}
			
			Constructor<?> objConstructor = dataClass.getDeclaredConstructor(classList);
			
			return objConstructor.newInstance(valueList);
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String formatVariableName(String name) {
		String new_string = "";
		
	    for (int i = 0; i < name.length(); i++){
	    	
	    	if (i == 0) {
	    		new_string = "" + Character.toUpperCase(name.charAt(i));
	    		continue;
	    	}
	    	
	        char charValue = name.charAt(i);
	        
	        if (Character.isUpperCase(charValue)) {               
	            new_string = new_string + " " + charValue;
	        } else {
	            new_string = new_string + charValue;
	        }
	    }
	    
	    return new_string;
	}
	
	public static Object invokeDEService(Service deService, ArrayList<Object> argList) {
		try {
			// Get declared constructor to check for the existence of instance
			Class<?> clzz = deService.getClazz();
			Class<?>[] inputs = deService.getInputs().toArray(new Class<?>[deService.getInputSize()]);
			Constructor<?> constructor = clzz.getDeclaredConstructor(inputs);
			
			// Insert user inputs to instantiate a new instance
			Object[] objectList = argList.toArray();
			Object obj = constructor.newInstance(objectList);
			
			// Invoke overriden method to retrieve Business object
			Method method = clzz.getDeclaredMethod("invoke");
			return method.invoke(obj);
			
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} 
		
		//Constructor<?> boConstructor = deSer
		return null;
	}
	
	public static Object getNativeParameter(Class<?> dataType, String value) {
		if (dataType.equals(BigDecimal.class)) {
			return new BigDecimal(value);
		} else if (dataType.equals(String.class)) {
			return String.valueOf(value);
		}
		
		return null;
	}
}