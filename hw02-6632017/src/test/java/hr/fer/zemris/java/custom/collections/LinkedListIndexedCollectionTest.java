package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LinkedListIndexedCollectionTest {

		@Test
		public void add() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			//assertEquals(3, list.size());
			assertEquals("number3", collection.get(2));
		
		}

		@Test
		public void constructorWithhoutArgument() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			assertEquals(null, collection.first());
			
		}
		
		@Test
		public void constructorWithArgument() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			
		
			LinkedListIndexedCollection collection2 = new LinkedListIndexedCollection(collection);
			assertEquals("number1", collection2.get(0));
			
		}
		
		@Test
		public void getException() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			assertThrows(IndexOutOfBoundsException.class, () -> collection.get(5));	
		}
		
		@Test
		public void clear() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			
			collection.clear();
			
			assertEquals(0, collection.size());
			assertThrows(IndexOutOfBoundsException.class, () -> collection.get(0));
			
		}
		
		@Test
		public void insert() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			
			collection.insert("newNumber", 1);
			assertEquals("newNumber", collection.get(1));
			assertEquals(3, collection.size());		
		}
		
		@Test
		public void insertException() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			
			
			assertThrows(IndexOutOfBoundsException.class, () -> collection.insert("newNUmber", -2));
			assertThrows(IndexOutOfBoundsException.class, () -> collection.insert("newNUmber", 5));
			
		}
		
		@Test
		public void indexOf() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			
			assertEquals(0, collection.indexOf("number1"));
			assertEquals(2, collection.indexOf("number3"));
			assertEquals(-1, collection.indexOf("noExistingValue"));
			
		}
		
		@Test
		public void remove() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			collection.add("number2");
			collection.add("number3");
			
			

			collection.remove("number1");
			assertEquals(-1, collection.indexOf("number1"));

			assertEquals(0, collection.indexOf("number2"));
			assertEquals(1, collection.indexOf("number3"));
			
			collection.remove("number2");
			assertEquals(0, collection.indexOf("number3"));
			collection.remove("number3");
			assertEquals(0, collection.size());
		}
		
		@Test 
		public void remove2() {
			LinkedListIndexedCollection collection = new LinkedListIndexedCollection();
			collection.add("number1");
			
			collection.remove(0);
			assertEquals(0, collection.size());
			
		}
		
		
		
		
		
		

	}
