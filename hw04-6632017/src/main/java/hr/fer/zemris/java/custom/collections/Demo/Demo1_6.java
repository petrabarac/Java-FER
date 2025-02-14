package hr.fer.zemris.java.custom.collections.Demo;
import hr.fer.zemris.java.custom.collections.*;

public class Demo1_6 {
	public static void main(String[] args) {
		
		List<String> col1 = new ArrayIndexedCollection<>();
		List<String> col2 = new LinkedListIndexedCollection<>();
		col1.add("Ivana");
		col2.add("Jasna");
		Collection<String> col3 = col1;
		Collection<String> col4 = col2;
		col1.get(0);
		col2.get(0);
		//col3.get(0); // neće se prevesti! Razumijete li zašto?
		//col4.get(0); // neće se prevesti! Razumijete li zašto?
		col1.forEach(System.out::println);
		col2.forEach(System.out::println);
		col3.forEach(System.out::println);
		col4.forEach(System.out::println);

		
	}
}
