package hr.fer.zemris.java.hw02;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ComplexNumberTest {
	@Test
	public void fromReal() {
		ComplexNumber number = new ComplexNumber(1,0);
		ComplexNumber  test = ComplexNumber.fromReal(1);
		
		assertEquals(1, test.getReal());
	}

	@Test
	public void fromImaginary() {
		ComplexNumber number = new ComplexNumber(0,2.4);
		ComplexNumber  test = ComplexNumber.fromImaginary(2.4);
		
		assertEquals(2.4, test.getImaginary());
	}
	
	@Test
	public void fromMagnitudeAngle() {
		ComplexNumber  number = new ComplexNumber(1,1);
		ComplexNumber  test = ComplexNumber.fromMagnitudeAndAngle(Math.sqrt(2), Math.PI/4);;
		
		assertTrue(number.equals(test));
	}
	
	@Test 
	public void parse()  {
		ComplexNumber  number = new ComplexNumber(-1,-2);
		ComplexNumber  test = ComplexNumber.parse("-1-2i");
		assertTrue(number.equals(test));
	}
	
	@Test
	public void geReal() {
		ComplexNumber  number = new ComplexNumber(-1,-2);
		assertEquals(-1, number.getReal());
	}
	
	@Test
	public void getImaginary() {
		ComplexNumber  number = new ComplexNumber(-1,-2);
		assertEquals(-2, number.getImaginary());
	}
	
	@Test
	public void getMagnitude() {
		ComplexNumber  number = new ComplexNumber(4,3);
		assertEquals(5, number.getMagnitude());
	}
	
	@Test
	public void getAngle() {
		ComplexNumber  number = new ComplexNumber(1,1);
		assertEquals(Math.PI/4, number.getAngle());
	}
	
	@Test
	public void add() {
		ComplexNumber  number = new ComplexNumber(-5,3);
		ComplexNumber  number2 = new ComplexNumber(7,2);
		
		ComplexNumber  expectedResoult = new ComplexNumber(2,5);
		ComplexNumber result = number.add(number2);
		
		assertTrue(expectedResoult.equals(result));
	}
	
	@Test
	public void sub() {
		ComplexNumber  number = new ComplexNumber(-5,3);
		ComplexNumber  number2 = new ComplexNumber(7,2);
		
		ComplexNumber  expectedResoult = new ComplexNumber(-12,1);
		ComplexNumber result = number.sub(number2);
		
		assertTrue(expectedResoult.equals(result));
	}
	
	
	@Test
	public void mul() {
		ComplexNumber  number = new ComplexNumber(3,2);
		ComplexNumber  number2 = new ComplexNumber(1,4);
		
		ComplexNumber  expectedResoult = new ComplexNumber(-5,14);
		ComplexNumber result = number.mul(number2);
		
		assertTrue(expectedResoult.equals(result));
	}

	@Test
	public void div() {
		ComplexNumber  number = new ComplexNumber(3,2);
		ComplexNumber  number2 = new ComplexNumber(4,-3);
		
		double expectedReal = (double)6 / 25;
		double expectedImaginary = (double)17 / 25;
		
		ComplexNumber  expectedResoult = new ComplexNumber(expectedReal, expectedImaginary);
		ComplexNumber result = number.div(number2);
		
		assertTrue(expectedResoult.equals(result));
	}
	
	@Test
	public void power() {
		ComplexNumber  number = new ComplexNumber(1,Math.sqrt(3));
		ComplexNumber  expectedResoult = new ComplexNumber(-8, 0);
		
		number = number.power(3);
		assertTrue(number.equals(expectedResoult));
	}

	@Test
	public void root() {
		ComplexNumber[] roots = new ComplexNumber[5];
		ComplexNumber number = new ComplexNumber(0,1);
		roots = number.root(3);
		
		ComplexNumber expectedRootOne = ComplexNumber.fromMagnitudeAndAngle(1, Math.PI/6);
		ComplexNumber expectedRootTwo = ComplexNumber.fromMagnitudeAndAngle(1, (5 * Math.PI)/6);
		ComplexNumber expectedRootThree = ComplexNumber.fromMagnitudeAndAngle(1, (9 * Math.PI)/6);
		
		assertTrue(roots[0].equals(expectedRootOne));
		assertTrue(roots[1].equals(expectedRootTwo));
		assertTrue(roots[2].equals(expectedRootThree));
		
	}

	@Test
	public void toStringTest() {
		ComplexNumber  number = new ComplexNumber(-3,2);
		
		assertEquals("-3.0+2.0i", number.toString());		
	}

}
