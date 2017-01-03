package com.rule.expression;

import java.util.Map;

public class BaseType<T> implements Expression {
	
	public T value;
	public Class<?> type;
	
	public BaseType(T value, Class<T> type) {
		this.value = value;
		this.type = type;
	}
	
	public T getValue() {
		return value;
	}
	
	public Class<?> getType() {
		return type;
	}

	@Override
	public boolean interpret(Map<String, ?> bindings) {
		return true;
	}
	
	/**
	 * Contains a factory method to generate concrete value types for a specific Java type.
	 * 
	 * @param string	Concrete value to convert to BaseType
	 * @return			BaseType according to concrete value
	 */
	public static BaseType<?> getBaseType(String string) {
		if (string == null)
			throw new IllegalArgumentException("The provided string must not be null");
		
		if ("true".equals(string) || "false".equals(string))
			return new BaseType<>(Boolean.getBoolean(string), Boolean.class);
			
		else if (string.startsWith("'"))
			return new BaseType<>(string, String.class);
			
		else if (string.contains("."))
			return new BaseType<>(Float.parseFloat(string), Float.class);
			
		else
			return new BaseType<>(Integer.parseInt(string), Integer.class);
			
	}
}
