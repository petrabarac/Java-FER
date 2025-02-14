package hr.fer.zemris.java.custom.collections;
import java.util.Arrays;

/**
 *  Class that represents example of usage class collection
 * @author Petra Barać
 *
 */

public class Example {
	
	public static void main(String[] args) {
		ArrayIndexedCollection col = new ArrayIndexedCollection(2);
		col.add(Integer.valueOf(20));
		col.add("New York");
		col.add("San Francisco"); 
		System.out.println(col.contains("New York")); 
		col.remove(1); 
		System.out.println(col.get(1)); 
		System.out.println(col.size());
		col.add("Los Angeles");
		
		LinkedListIndexedCollection col2 = new LinkedListIndexedCollection(col);
		
		class P extends Processor {
		public void process(Object o) {
		System.out.println(o);
		}
		};
		System.out.println("col elements:");
		col.forEach(new P());
		System.out.println("col elements again:");
		System.out.println(Arrays.toString(col.toArray()));
		System.out.println("col2 elements:");
		col2.forEach(new P());
		System.out.println("col2 elements again:");
		System.out.println(Arrays.toString(col2.toArray()));
		System.out.println(col.contains(col2.get(1))); 
		System.out.println(col2.contains(col.get(1))); 
		col.remove(Integer.valueOf(20)); 
		
	}

}
