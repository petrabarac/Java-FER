package hr.fer.zemris.java.custom.scripting.exec;



public class ValueWrapper {

	private Object value;
	
	

	public ValueWrapper(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
	public void add(Object incValue) {
		value = executeOperation(Operations.ADD, value, incValue);
		
	}
	
	public void subtract(Object decValue) {
		value = executeOperation(Operations.SUB, value, decValue);
	}
	
	public void multiply(Object mulValue) {
		value = executeOperation(Operations.MUL, value, mulValue);		
	}
	
	public void divide(Object divValue) {
		value = executeOperation(Operations.DIV, value, divValue);
	}
	
	public int numCompare(Object withValue) {
		value = executeOperation(Operations.COMP, value, withValue);
		return (Integer)value;
	}
		
	private Number executeOperation(Operation o,Object value1, Object value2) {
		
		Number num1 = null;
		Number num2 = null;
		
		try {
			num1 = checkValueInstance(value1);
			num2 = checkValueInstance(value2);
		}catch (RuntimeException e) {
			e.getMessage();
		}
		return o.operate(num1, num2);
	}
	
	private Number checkValueInstance(Object value) {
		
		if(value == null) {
			return Integer.valueOf(0);
		}
		
		if(value instanceof String) {
			String stringValue = (String) value;
			if(stringValue.contains(".") || stringValue.contains("e") || stringValue.contains("E")) {
				return Double.parseDouble(stringValue);
			}
			return Integer.parseInt(stringValue);
		}
		
		if(value instanceof Double || value instanceof Integer) {
			return (Number) value;
		}
		
		throw new RuntimeException("Invalid value type");
	}
	
}
