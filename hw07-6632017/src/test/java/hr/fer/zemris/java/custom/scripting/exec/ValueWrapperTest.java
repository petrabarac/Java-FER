package hr.fer.zemris.java.custom.scripting.exec;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ValueWrapperTest {
	
	@Test
	public void addTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.add(v2.getValue());
		
		assertEquals(0, v1.getValue());
		assertTrue(v1.getValue() instanceof Integer);
		assertNull(v2.getValue());
		
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.add(v4.getValue());
		
		assertEquals(13.0, v3.getValue());
		assertTrue(v3.getValue() instanceof Double);
		assertEquals(1, v4.getValue());
		assertTrue(v4.getValue() instanceof Integer);
		
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(1));
		v5.add(v6.getValue());
		
		assertEquals(13, v5.getValue());
		assertTrue(v5.getValue() instanceof Integer);
		assertEquals(1, v6.getValue());
		assertTrue(v6.getValue() instanceof Integer);
		
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		assertThrows(RuntimeException.class, () -> v7.add(v8.getValue()));
}
	@Test
	public void subtractTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.subtract(v2.getValue());
		
		assertEquals(0, v1.getValue());
		assertTrue(v1.getValue() instanceof Integer);
		assertNull(v2.getValue());
		
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(1));
		v3.subtract(v4.getValue());
		
		assertEquals(11.0, v3.getValue());
		assertTrue(v3.getValue() instanceof Double);
		assertEquals(1, v4.getValue());
		assertTrue(v4.getValue() instanceof Integer);
		
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(1));
		v5.subtract(v6.getValue());
		
		assertEquals(11, v5.getValue());
		assertTrue(v5.getValue() instanceof Integer);
		assertEquals(1, v6.getValue());
		assertTrue(v6.getValue() instanceof Integer);
		
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		assertThrows(RuntimeException.class, () -> v7.subtract(v8.getValue()));
}
	@Test
	public void multiplyTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		v1.multiply(v2.getValue());
		
		assertEquals(0, v1.getValue());
		assertTrue(v1.getValue() instanceof Integer);
		assertNull(v2.getValue());
		
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(2));
		v3.multiply(v4.getValue());
		
		assertEquals(24.0, v3.getValue());
		assertTrue(v3.getValue() instanceof Double);
		assertEquals(2, v4.getValue());
		assertTrue(v4.getValue() instanceof Integer);
		
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(2));
		v5.multiply(v6.getValue());
		
		assertEquals(24, v5.getValue());
		assertTrue(v5.getValue() instanceof Integer);
		assertEquals(2, v6.getValue());
		assertTrue(v6.getValue() instanceof Integer);
		
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		assertThrows(RuntimeException.class, () -> v7.multiply(v8.getValue()));
}
	
	@Test
	public void divideTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		assertThrows(ArithmeticException.class, () -> v1.divide(v2.getValue()));
		
		ValueWrapper v11 = new ValueWrapper(null);
		ValueWrapper v22 = new ValueWrapper(12);
		v11.divide(v22.getValue());
		assertEquals(0, v11.getValue());
		assertTrue(v11.getValue() instanceof Integer);
		
		ValueWrapper v3 = new ValueWrapper("1.2E1");
		ValueWrapper v4 = new ValueWrapper(Integer.valueOf(2));
		v3.divide(v4.getValue());
		
		assertEquals(6.0, v3.getValue());
		assertTrue(v3.getValue() instanceof Double);
		assertEquals(2, v4.getValue());
		assertTrue(v4.getValue() instanceof Integer);
		
		ValueWrapper v5 = new ValueWrapper("12");
		ValueWrapper v6 = new ValueWrapper(Integer.valueOf(2));
		v5.divide(v6.getValue());
		
		assertEquals(6, v5.getValue());
		assertTrue(v5.getValue() instanceof Integer);
		assertEquals(2, v6.getValue());
		assertTrue(v6.getValue() instanceof Integer);
		
		ValueWrapper v7 = new ValueWrapper("Ankica");
		ValueWrapper v8 = new ValueWrapper(Integer.valueOf(1));
		assertThrows(RuntimeException.class, () -> v7.divide(v8.getValue()));
	}
	
	@Test
	public void compareTest() {
		ValueWrapper v1 = new ValueWrapper(null);
		ValueWrapper v2 = new ValueWrapper(null);
		ValueWrapper v3 = new ValueWrapper(15);
		ValueWrapper v4 = new ValueWrapper(-2);
		
		assertTrue(v1.numCompare(v2.getValue()) == 0);
		assertTrue(v3.numCompare(v4.getValue()) > 0);
		assertTrue(v4.numCompare(v3.getValue()) < 0);
	}
	
	@Test
	public void objectMultistackTest() {
		ObjectMultistack ms = new ObjectMultistack();
		
		ms.push("key1", new ValueWrapper(Integer.valueOf(3)));
		ms.push("key1", new ValueWrapper(Double.valueOf(2.0)));
		ms.push("key1", new ValueWrapper("test"));
		ms.push("key2", new ValueWrapper("test2"));
		
		assertEquals("test", ms.peek("key1").getValue());
		assertEquals("test", ms.pop("key1").getValue());
		assertEquals(2.0, ms.pop("key1").getValue());
		assertEquals(3, ms.pop("key1").getValue());
		assertTrue(ms.isEmpty("key1"));
		assertFalse(ms.isEmpty("key2"));
}
	
	

}
