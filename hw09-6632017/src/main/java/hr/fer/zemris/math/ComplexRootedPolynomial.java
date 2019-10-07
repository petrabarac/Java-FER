package hr.fer.zemris.math;

public class ComplexRootedPolynomial {
	
	private Complex constant;
	private Complex[] roots;
	
	public ComplexRootedPolynomial(Complex constant, Complex ... roots) {
		this.constant = constant;
		this.roots = roots;
	}
	

	public Complex apply(Complex z) {
		Complex result = z;;
		
		for(int i = 0; i < roots.length; i++) {
			result = result.multiply((z.sub(roots[i])));
		}
		
		return result;
	}

	public ComplexPolynomial toComplexPolynom() {
		
		Complex[] first = new Complex[1];
		first[0] = constant;
		ComplexPolynomial current = new ComplexPolynomial(first);
		
		for(Complex root: roots) {
			Complex factors[] = new Complex[2];
			factors[1] = Complex.ONE;
			factors[0] = root.negate();
			ComplexPolynomial next = new ComplexPolynomial(factors);
			current = current.multiply(next);

		}
		return current;
		
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(constant).append("*");
		for(int i = 0; i < roots.length -1; i++) {
			sb.append("(z-").append(roots[i]).append(")*");
		}
    	sb.append("(z-").append(roots[roots.length -1]).append(")");
		
		return sb.toString();
	}
	

	public int indexOfClosestRootFor(Complex z, double treshold) {

		double minDistance = Double.MAX_VALUE;
		int closestRootIndex = -1;
		double currentDistance;

		for (int i = 0; i < roots.length; i++) {
			currentDistance = z.sub(roots[i]).module();
			
			if (currentDistance <= treshold && currentDistance < minDistance) {
				minDistance = currentDistance;
				closestRootIndex = i;
			}
		}
		return closestRootIndex;
	}

}
