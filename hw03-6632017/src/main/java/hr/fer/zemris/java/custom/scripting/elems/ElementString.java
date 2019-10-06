package hr.fer.zemris.java.custom.scripting.elems;

/**
 *Inherits Element and has single read-only double property: value
 * @author Petra Barać
 *
 */
public class ElementString extends Element{
	
	private String value;
	
	/**
	 * Default constructor used for setting element string to given value
	 * @param value value of string
	 * @throws IllegalArgumentException if given string is null
	 */
	
	public ElementString(String value) {
		
		if(value == null) {
			throw new IllegalArgumentException("Value can't be null"); 
		}
		
		this.value = value;
	}
	
	/**
	 * Method for getting string element
	 * @return value
	 */


	public String getValue() {
		return value;
	}
	/**
	 * Override method asText()
	 * @return value property
	 */
	@Override
	public String asText() {
		return value;
	}
	
}
