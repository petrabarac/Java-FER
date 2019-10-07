package hr.fer.zemris.math;

import java.util.LinkedList;
import java.util.List;

public class Complex {
	
	/**
	 * Real part of complex number
	 */
	private double real;
	/**
	 * Imaginary part of complex number
	 */
	private double imaginary;
	

	private double angle;

	
	public static final Complex ZERO = new Complex(0,0);
	
	public static final Complex ONE = new Complex(1,0);
	
	public static final Complex ONE_NEG = new Complex(-1,0);
	
	public static final Complex IM = new Complex(0,1);
	
	public static final Complex IM_NEG = new Complex(0,-1);

	
	public Complex() {
		
	}
	public Complex(double re, double im) {
		real = re;
		imaginary = im;
		
  
		angle = Math.atan2(imaginary, real);
	    if(angle < 0) {
			angle += 2*Math.PI;
		}
		
	}

	public double module() {
		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));		
	}

	public Complex multiply(Complex c) {
		Complex mul = new Complex(0.0,0.0);
		mul.real = (this.real * c.real) - (this.imaginary * c.imaginary);
		mul.imaginary = (this.real * c.imaginary) + (this.imaginary * c.real);
		return mul;
	}

	public Complex divide(Complex c) {
		double divReal;
		double divImaginary;
		double cReal = c.real;
		double cImaginary = c.imaginary;
		
		if(cReal == 0 && cImaginary == 0) {
			throw new IllegalArgumentException("Dividing with zero");
		}
		divReal = ((this.real * cReal) + ((this.imaginary *(-1) *cImaginary)*(-1))) / (Math.pow(cReal, 2) + Math.pow(cImaginary, 2));
		divImaginary = ((this.real * (-1) * cImaginary) + (this.imaginary * cReal)) / (Math.pow(cReal, 2) + Math.pow(cImaginary, 2));
		
		Complex div = new Complex(divReal, divImaginary);
		return div;
		
	}

	public Complex add(Complex c) {
		Complex add = new Complex(this.real + c.real, this.imaginary + c.imaginary);
		return add;
	}

	public Complex sub(Complex c) {
		Complex sub = new Complex(this.real - c.real, this.imaginary - c.imaginary);
		return sub;
	}

	public Complex negate() {
		return new Complex(-1 * real, -1* imaginary);		
	}

	public Complex power(int n) {
		if(n < 0) {
			throw new IllegalArgumentException("Argument must be >= 0");
		}
		
		if(n == 0) {
			Complex power = new Complex(1, 0);
			return power;
		}
		double r = this.module();
		
		
		double powerReal = Math.pow(r, n) * Math.cos(n * angle);
		double powerImaginary = Math.pow(r, n) * Math.sin(n * angle);
		
		
		Complex power = new Complex(powerReal, powerImaginary);
		return power;
		
	}
	
	public Complex scale(int n) {
		return new Complex(real * n, imaginary* n);
	}

	public List<Complex> root(int n) {
		if(n <=0) {
			throw new IllegalArgumentException("Argument must be > 0");
		}
		
		double r = module();
	
		double rootReal, rootImaginary;
		List<Complex> roots = new LinkedList<Complex>();
		
		
		for(int k = 0; k < n; k++) {
			rootReal = Math.pow(r, 1.0/n) * Math.cos((angle + 2*k*Math.PI) / n);
			rootImaginary = Math.pow(r, 1.0/n) * Math.sin((angle + 2*k*Math.PI) / n);
			roots.add(new Complex(rootReal, rootImaginary));		
		}
		
		return roots;
		
	}
	
	@Override
	public String toString() {
		String sReal = String.valueOf(this.real);
		String sImaginary = "i" + String.valueOf(this.imaginary);
		
		double negativeImaginary = imaginary * (-1);
		String sNegativeImaginary =  "i" + String.valueOf(negativeImaginary);
		
		if(imaginary >= 0) {
		 return "(" + sReal + "+" + sImaginary + ")";
		} else {
			return "(" + sReal + "-" + sNegativeImaginary + ")" ;
		}
	}
}



