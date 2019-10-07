package hr.fer.zemris.java.custom.scripting.exec;

public class Operations{

	public static final Operation ADD = new Operation() {
		
		public Number operate(Number value1, Number value2) {
			if(value1 instanceof Integer && value2 instanceof Integer) {
				return value1.intValue() + value2.intValue();
			}			
			return value1.doubleValue() + value2.doubleValue();
		}
	};
	
	public static final Operation SUB = new Operation() {
		
		public Number operate(Number value1, Number value2) {
			
			if(value1 instanceof Integer && value2 instanceof Integer) {
				return value1.intValue() - value2.intValue();
			}			
			return value1.doubleValue() - value2.doubleValue();
		}
	};
	
	public static final Operation MUL = new Operation() {
		
		public Number operate(Number value1, Number value2) {
			
			if(value1 instanceof Integer && value2 instanceof Integer) {
				return value1.intValue() * value2.intValue();
			}			
			return value1.doubleValue() * value2.doubleValue();
		}
	};
	
	public static final Operation DIV = new Operation() {
		
		public Number operate(Number value1, Number value2) {
			
			if(value1 instanceof Integer && value2 instanceof Integer) {
				return value1.intValue() / value2.intValue();
			}			
			return value1.doubleValue() / value2.doubleValue();
		}
	};
	
	public static final Operation COMP = new Operation() {
		
		public Number operate(Number value1, Number value2) {
			
			if(value1 instanceof Integer && value2 instanceof Integer) {
				return Integer.compare(value1.intValue(), value2.intValue());
			}			
			return Double.compare(value1.intValue(), value2.intValue());
		}
	};
	
	
		
}


	





