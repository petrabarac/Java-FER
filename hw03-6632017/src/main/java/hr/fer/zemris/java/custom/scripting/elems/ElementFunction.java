package hr.fer.zemris.java.custom.scripting.elems;

/**
 * inherits Element  and has single read-only String property: name
to return name property. . Override asText()
 * @author Petra Barać
 *
 */

public class ElementFunction extends Element{
	
	private String name;
	
	/**
	 * Default constructor
	 * used for setting function name to given name
	 * @param name name of function
	 * @throws IllegalArgumentException if given name is null 
	 */
	
	public ElementFunction(String name) {
		if(name == null) {
			throw new IllegalArgumentException("Name can't be null"); 
		}
		this.name = name;
		
	}
	/**
	 * Method for getting name of function
	 * @return name of function
	 */
	public String getValue() {
		return name;
	}
	/**
	 * Override method asText()
	 * @return name property
	 */
	@Override
	public String asText() {
		return name;
	}
	

}
