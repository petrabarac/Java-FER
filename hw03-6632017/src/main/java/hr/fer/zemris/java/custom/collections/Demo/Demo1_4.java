package hr.fer.zemris.java.custom.collections.Demo;

import hr.fer.zemris.java.custom.collections.*;


public class Demo1_4 {
	
	public static void main(String[] args) {
		Collection col = new ArrayIndexedCollection();
		col.add("Ivo");
		col.add("Ana");
		col.add("Jasna");
		
		ElementsGetter getter = col.createElementsGetter();
		getter.getNextElement();
		
		getter.processRemaining(System.out::println);
		}

}
