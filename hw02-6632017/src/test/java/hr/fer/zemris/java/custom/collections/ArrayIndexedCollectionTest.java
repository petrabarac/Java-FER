package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ArrayIndexedCollectionTest {
	
	@Test
	public void constructorOne() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		assertEquals(16, collection.capacity());
	}
	
	@Test
	public void constructorTwoIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> new ArrayIndexedCollection(0));
	}
	
	@Test
	public void constructorTwo() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		assertEquals(2, collection.capacity());		
	}
	
	@Test
	public void constructorThree() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		collection.add("two");
		
		ArrayIndexedCollection collection2 = new ArrayIndexedCollection(collection);
		
		assertEquals("one", collection2.get(0));
		assertEquals("two", collection2.get(1));	
	}
	
	@Test
	public void constructorFour() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		collection.add("two");
		
		ArrayIndexedCollection collection2 = new ArrayIndexedCollection(collection, 1);
		assertEquals("one", collection2.get(0));
		assertEquals("two", collection2.get(1));		
	}
	
	@Test
	public void constructorFourException() {
		assertThrows(NullPointerException.class, () -> new ArrayIndexedCollection(null, 3));		
	}
	
	@Test
	public void addWithoutCapacity() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(2);
		collection.add("one");
		collection.add("two");
		collection.add("three");
		assertEquals("three", collection.get(2));
	}
	
	@Test
	public void addWithCapacity() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(4);
		collection.add("one");
		collection.add("two");
		collection.add("three");
		
		assertEquals("three", collection.get(2));		
	}
	
	@Test
	public void addNullException() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection();
		assertThrows(NullPointerException.class, () -> collection.add(null));
	}
	
	@Test
	public void getException() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(4);
		assertThrows(IndexOutOfBoundsException.class, () -> collection.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> collection.get(7));		
	}
	
	@Test
	public void Clear() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		collection.add("two");
		collection.add("three");
		collection.clear();
		assertEquals(0,collection.size() );
	}
	
	@Test
	public void insert() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		collection.add("two");
		collection.add("three");
		collection.insert("insertValue", 2);
		assertEquals("insertValue", collection.get(2));
		assertEquals(4, collection.size());
	}
	
	@Test
	public void insertException() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		assertThrows(IndexOutOfBoundsException.class, () -> collection.insert("insertValue", -2));
	}
	
	
	@Test
	public void indexOf() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		collection.add("two");
		assertEquals(0, collection.indexOf("one"));
		assertEquals(-1, collection.indexOf("four"));
	}
	
	@Test
	public void remove() {
		ArrayIndexedCollection collection = new ArrayIndexedCollection(3);
		collection.add("one");
		collection.add("two");
		collection.add("three");
		collection.remove(0);
		assertEquals(2, collection.size());
	}
	
	
	
	
	
	
	
	
	
	
}
