package hr.fer.zemris.java.custom.collections.Demo;
import hr.fer.zemris.java.custom.collections.*;

public class Demo1_5 {

		
		public static void main(String[] args) {
			Collection col1 = new LinkedListIndexedCollection();
			Collection col2 = new ArrayIndexedCollection();
			col1.add(2);
			col1.add(3);
			col1.add(4);
			col1.add(5);
			col1.add(6);
			col2.add(12);
			col2.addAllSatisfying(col1, new EvenIntegerTester());
			col2.forEach(System.out::println);
		}

	}


