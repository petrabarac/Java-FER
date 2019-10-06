package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DictionaryTest {

	@Test
	public void putAndGetTest() {
		
		Dictionary<Integer, String> d = new Dictionary<>();
		
		d.put(1,"Jasna");
		d.put(2, "Ivana");
		d.put(2, "Petra");
		
		assertEquals(2, d.size());
		assertEquals("Petra", d.get(2));
	}
	
	@Test
	public void clearSizeIsEmpty() {
		Dictionary<Integer, String> d = new Dictionary<>();
		
		d.put(1,"Jasna");
		d.put(2, "Ivana");
		d.put(3, "Petra");
		
		assertEquals(3, d.size());
		d.clear();
		assertTrue(d.isEmpty());
		assertEquals(0, d.size());
	}
	
	
}
