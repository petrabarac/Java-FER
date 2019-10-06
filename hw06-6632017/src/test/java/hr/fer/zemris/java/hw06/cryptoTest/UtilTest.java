package hr.fer.zemris.java.hw06.cryptoTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import hr.fer.zemris.java.hw06.crypto.*;
import jdk.jfr.Experimental;


public class UtilTest {
	
	@Test 
	public void hextobyteTest() {
		String array = "01aE22";
		
		byte[] calucatedArray = Util.hextobyte(array);
		
		assertEquals(1, calucatedArray[0]);
		assertEquals(-82, calucatedArray[1]);
		assertEquals(34, calucatedArray[2]);

	}
	
	@Test
	public void bytetohexTest() {
		byte[] array = {1, -82, 34};
		
		String calculatedArray = Util.bytetohex(array);
		
		assertEquals("01aE22".toLowerCase(), calculatedArray);
		
	}
}
