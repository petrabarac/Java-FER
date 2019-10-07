package hr.fer.zemris.math;

public class ComplexPolynomial {
	
	Complex[] factors;
	public ComplexPolynomial(Complex ...factors) {
		this.factors = factors;
	}

	public short order() {
		return (short)(factors.length - 1);
	}

	public ComplexPolynomial multiply(ComplexPolynomial p) {
		
		int size = factors.length + p.factors.length -1;
		
		Complex[] multiplyFactors = new Complex[size];
		for(int i = 0; i < size; i++) {
			multiplyFactors[i] = Complex.ZERO;
		}
		
		
		for(int i = 0; i < factors.length; i++) {
			for(int j = 0; j < p.factors.length; j++) {
				multiplyFactors[i+j] = multiplyFactors[i+j].add(factors[i].multiply(p.factors[j]));
			}
		}
		
		return new ComplexPolynomial(multiplyFactors);
	}

	public ComplexPolynomial derive() {
		Complex[] deriveFactors =  new Complex[factors.length - 1];
		
		for(int i = 1; i < this.factors.length; i++) {
			deriveFactors[i-1] = factors[i].scale(i);
		}
		return new ComplexPolynomial(deriveFactors);
	}

	public Complex apply(Complex z) {
		Complex result = Complex.ZERO;
		
		for(int i = 0; i < factors.length; i++) {
			result = result.add(factors[i].multiply(z.power(i)));
		}
		return result;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = factors.length - 1; i > 0; i--) {
			sb.append("(").append(factors[i]).append(")");
			sb.append("z^").append(i);
			sb.append("+");
		}
		sb.append(factors[0]);
		return sb.toString();	
		
	}
}
