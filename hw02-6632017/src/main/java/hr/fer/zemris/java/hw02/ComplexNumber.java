package hr.fer.zemris.java.hw02;

import java.util.Objects;

/**
 * Class implement a support for working with complex numbers
 * @author Petra Barać
 *
 */
public class ComplexNumber {
	/**
	 * Constant that determines what will be maximum difference between two equal numbers
	 */
	private static double epsilon = 1e-6;
	/**
	 * Real part of complex number
	 */
	private double real;
	/**
	 * Imaginary part of complex number
	 */
	private double imaginary;
	
	/**
	 * Creates new complex number with real and imaginary parts given as arguments
	 * @param real real part of complex number
	 * @param imaginary imaginary part of complex number
	 */
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	/**
	 * Get real part of complex number
	 * @return real part of complex number
	 */
	public double getReal() {
		return this.real;
	}
	/**
	 * Get imaginary part of complex number
	 * @return imaginary part of complex number
	 */
	public double getImaginary() {
		return this.imaginary;
	}
	
	/**
	 * Calculate magnitude of complex number which is used
	 * for trigonometry form of complex number
	 * @return calculated magnitude
	 */
	public double getMagnitude(){
		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
	}
	
	/**
	 * Calculate angle of complex number which is used
	 * for trigonometry form of complex number
	 * @return angle of complex number
	 */

	public double getAngle(){

		double angle = Math.atan2(imaginary, real);
		if(angle < 0) {
			angle += 2*Math.PI;
		}
		return angle;
	}
	/**
	 * Create complex number with given real part of complex number
	 * @param real real part of complex number
	 * @return new created complex number
	 */
	public static ComplexNumber fromReal(double real) {
		ComplexNumber number = new ComplexNumber(real, 0.0);
		return number;
	}
	/**
	 * Create complex number with given imaginary part of complex number
	 * @param imaginary imaginary part of complex number
	 * @return new created complex number
	 */
	public static ComplexNumber fromImaginary(double imaginary) {
		ComplexNumber number = new ComplexNumber(0.0, imaginary);
		return number;
		
	}
	
	/**
	 * Create standard form of complex number by given magnitude 
	 * and angle from trigonometry form of complex number
	 * @param magnitude magnitude of complex number
	 * @param angle angle of complex number
	 * @return new created complex number
	 */
	public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
		double real = magnitude * Math.cos(angle);
		double imaginary = magnitude * Math.sin(angle);
		
		ComplexNumber number = new ComplexNumber(real, imaginary);
		return number;
	}
	
	/**
	 * Parse string form complex number into ComplexNumber number
	 * @param s string form of complex number
	 * @return new created complex number
	 * @note not a good method (without exception)
	 */
	public static ComplexNumber parse(String s) {	
		
		String string = s.replaceAll(" ", "");
		double real;
		double imaginary;
		String unsignedFirst= string.substring(1, string.length());
		String[] parts = new String[5];
		
		if(unsignedFirst.indexOf('+') != -1) {
			if(string.charAt(0) == '+' || string.charAt(0) == '-') {
				parts = unsignedFirst.split("\\+");
				if(string.charAt(0) == '-') {
					parts[0] = "-" + parts[0];
				}
			} else {
				parts = string.split("\\+");				
			}
			
			parts[1] = parts[1].replaceAll("i", "");
			//parts[1] = parts[1].trim();
			real = Double.parseDouble(parts[0]);
			imaginary = Double.parseDouble(parts[1]);
			
		} else if(unsignedFirst.indexOf('-') != -1) {
			if(string.charAt(0) == '+' || string.charAt(0) == '-') {
				parts = unsignedFirst.split("-");
				parts[1] = "-" + parts[1];
				if(string.charAt(0) == '-') {
					parts[0] = "-" + parts[0];
				}
			} else {
				parts = string.split("-");
				parts[1] = "-" + parts[1];
			}
			parts[1] = parts[1].replaceAll("i", "");
			parts[1] = parts[1].trim();
			real = Double.parseDouble(parts[0]);
			imaginary = Double.parseDouble(parts[1]);
		} else if(string.indexOf('i') != -1) { 
			  string = string.replaceAll("i", "");
			  //string = string.trim();
			  real = 0.0;
			  imaginary = Double.parseDouble(string);
		  } else {
			  real = Double.parseDouble(string);
			  imaginary = 0.0;
		  }
		
		ComplexNumber number = new ComplexNumber(real, imaginary);
		return number;
	}
	
	/**
	 * Add complex number with given complex number c
	 * @param c given complex number that will be added 
	 * @return result of adding
	 */
	public ComplexNumber add(ComplexNumber c) {
		ComplexNumber add = new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
		return add;
	}
	
	/**
	 * Subtract complex number with given complex number c
	 * @param c given complex number that will be subtracted
	 * @return result of subtracting
	 */
	
	public ComplexNumber sub(ComplexNumber c) {
		ComplexNumber sub = new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
		return sub;
	}
	
	/**
	 * Multiply complex number with given complex number c
	 * @param c given complex number that will be multiply
	 * @return result of multiplication
	 */
	public ComplexNumber mul(ComplexNumber c) {
		ComplexNumber mul = new ComplexNumber(0.0,0.0);
		mul.real = (this.real * c.real) - (this.imaginary * c.imaginary);
		mul.imaginary = (this.real * c.imaginary) + (this.imaginary * c.real);
		return mul;
	}
	
	/**
	 * Divide complex number with given complex number c
	 * @param c given complex number that will be divided by
	 * @return result of division
	 */
	
	public ComplexNumber div(ComplexNumber c) {
		
		double divReal;
		double divImaginary;
		double cReal = c.real;
		double cImaginary = c.imaginary;
		
		if(cReal == 0 && cImaginary == 0) {
			throw new IllegalArgumentException("Dividing with zero");
		}
		divReal = ((this.real * cReal) + ((this.imaginary *(-1) *cImaginary)*(-1))) / (Math.pow(cReal, 2) + Math.pow(cImaginary, 2));
		divImaginary = ((this.real * (-1) * cImaginary) + (this.imaginary * cReal)) / (Math.pow(cReal, 2) + Math.pow(cImaginary, 2));
		
		ComplexNumber div = new ComplexNumber(divReal, divImaginary);
		return div;
	}
	
	/**
	 * Raises complex number to the power of a given integer value
	 * @param n power to complex number will be raised to
	 * @return result of power operation
	 * @throws IllegalArgumentException given power must be greater than 0 
	 */
	
	public ComplexNumber power(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Argument must be >= 0");
		}
		
		if(n == 0) {
			ComplexNumber power = new ComplexNumber(1, 0);
			return power;
		}
		double r = this.getMagnitude();
		double angle = this.getAngle();
		
		double powerReal = Math.pow(r, n) * Math.cos(n * angle);
		double powerImaginary = Math.pow(r, n) * Math.sin(n * angle);
		
		
		ComplexNumber power = new ComplexNumber(powerReal, powerImaginary);
		return power;
	}
	
	/**
	 * Returns roots of complex number 
	 * @param n degree of the root
	 * @return n roots of complex number
	 * @throws IllegalArgumentException power must be greater than -1
	 */
	
	public ComplexNumber[] root(int n) {
		if(n <=0) {
			throw new IllegalArgumentException("Argument must be > 0");
		}
		
		double r = this.getMagnitude();
		double angle = this.getAngle();
		double rootReal, rootImaginary;
		ComplexNumber[] roots = new ComplexNumber[n];
		
		
		for(int k = 0; k < n; k++) {
			rootReal = Math.pow(r, 1.0/n) * Math.cos((angle + 2*k*Math.PI) / n);
			rootImaginary = Math.pow(r, 1.0/n) * Math.sin((angle + 2*k*Math.PI) / n);
			roots[k] = new ComplexNumber(rootReal, rootImaginary);		
		}
		
		return roots;
	}
	
	/**
	 * Return complex number parsed to string
	 */
	
	public String toString() {
		String real = String.valueOf(this.real);
		String imaginary = String.valueOf(this.imaginary) + "i";
		if(this.imaginary > 0 ) {
			return real + "+" + imaginary;
		} else {
			return real + imaginary ;
		}
		
	}
	/**
	 * Auto-generated hashCode method
	 */
	@Override
	public int hashCode() {
		return Objects.hash(imaginary, real);
	}
	/**
	 * Return true if two coplex numbers are equal
	 * Complex numbers are equal if their difference is less than default epsilon 
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ComplexNumber))
			return false;
		ComplexNumber other = (ComplexNumber) obj;
		return Math.abs(this.real - other.real) < epsilon && Math.abs(this.imaginary - other.imaginary) < epsilon;
	}
		
	
}
