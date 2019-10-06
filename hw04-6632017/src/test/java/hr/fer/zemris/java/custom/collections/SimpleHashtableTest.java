package hr.fer.zemris.java.custom.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleHashtableTest {
	
	@Test
	void iteratorRemoveTest() {
		SimpleHashtable<Integer, Integer> dictonary = new SimpleHashtable<>(2);
		dictonary.put(1, 10);
		dictonary.put(123,0);
		dictonary.put(2, 20);
		dictonary.put(3, 30);
		
		var iterator=dictonary.iterator();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.remove();
		
		String s = "";
		for (var pair1 : dictonary) {
			for (var pair2 : dictonary) {
				s = s + pair1.getKey() + pair1.getValue() + pair2.getKey() + pair2.getValue();
			}
		}
		
		assertEquals("110110110220110330220110220220220330330110330220330330",s);
	}
	

}
