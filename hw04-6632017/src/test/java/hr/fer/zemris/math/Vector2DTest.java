package hr.fer.zemris.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class Vector2DTest {

	@Test
	public void getXandY() {
		Vector2D vector = new Vector2D(2, 3);
		assertEquals(2, vector.getX());
		assertEquals(3, vector.getY());
	}
	@Test
	public void constructorException() {

		assertThrows(NullPointerException.class, () -> new Vector2D(0, 0));		
	}
	@Test
	public void translate() {
		Vector2D vector = new Vector2D(2, 3);
		vector.translate(new Vector2D(20,30));
		
		assertEquals(22, vector.getX());
		assertEquals(33, vector.getY());
		
	}
	@Test
	public void translated() {
		Vector2D vector = new Vector2D(2, 3);
		Vector2D translatedVector  = vector.translated(new Vector2D(8,7));
		
		assertEquals(10, translatedVector.getX());
		assertEquals(10, translatedVector.getY());		
	}
	
	@Test
	public void rotate() {
		Vector2D vector = new Vector2D(2, 3);
		Vector2D expectedVector = new Vector2D(3, -2);
		

		vector.rotate(1.5 * Math.PI);		
		assertTrue(vector.equals(expectedVector));
	}
	
	@Test 
	public void rotated() {
		Vector2D vector = new Vector2D(2, 3);
		Vector2D expectedVector = new Vector2D(3, -2);
		

		Vector2D rotatedVector = vector.rotated(1.5 * Math.PI);		
		assertTrue(expectedVector.equals(rotatedVector));
		
	}
	
	@Test
	public void scale() {
		Vector2D vector = new Vector2D(2, 3);
		Vector2D expectedVector = new Vector2D(10, 15);
		
		vector.scale(5);
		assertEquals(10,vector.getX());
		assertEquals(15, vector.getY());		
	}
	
	@Test
	public void scaled() {
		Vector2D vector = new Vector2D(2, 3);
		
		Vector2D resoultVector = vector.scaled(10);
		assertEquals(20,resoultVector.getX());
		assertEquals(30, resoultVector.getY());
	}
	
	@Test
	public void copy() {
		Vector2D vector = new Vector2D(2, 3);
		
		Vector2D copyVector = vector.copy();
		
		assertEquals(vector.getX(),copyVector.getX());
		assertEquals(vector.getY(), copyVector.getY());
		
	}
	
	
	
	
}
