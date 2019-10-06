package hr.fer.zemris.java.custom.collections.Demo;

import hr.fer.zemris.java.custom.collections.*;

public class EvenIntegerTester implements Tester {
	
	public boolean test(Object obj) {
		if(!(obj instanceof Integer)) return false;
		Integer i = (Integer)obj;
		return i % 2 == 0;
	}
}
