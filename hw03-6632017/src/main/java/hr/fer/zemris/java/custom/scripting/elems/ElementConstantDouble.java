package hr.fer.zemris.java.custom.scripting.elems;

/**
 *Inherits Element and has single read-only double property: value
 * @author Petra Barać
 *
 */

public class ElementConstantDouble extends Element {
	
	private double value;
	
	/**
	 * Default constructor
	 * used for setting element value to given value
	 * @param value value of element 
	 */
	public ElementConstantDouble(int value) {
		
		this.value = value;
	}

	/**
	 * Method for getting value of element
	 * @return value of element
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Override method asText()
	 * @return string representation of value property.
	 */
	@Override
	public String asText() {
		return Double.toString(value);
	}
}


