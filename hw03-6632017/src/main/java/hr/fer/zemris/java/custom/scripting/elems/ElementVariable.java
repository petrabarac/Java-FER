package hr.fer.zemris.java.custom.scripting.elems;

/**
 *Inherits Element and has single read-only double property: name
 * @author Petra Barać
 *
 */

public class ElementVariable extends Element{
	
	private String name;
	
	/**
	 * Default constructor used for setting variable name to given name
	 * @param name name of variable
	 * @throws IllegalArgumentException if given name is null
	 */
	
	
	public ElementVariable(String name) {
		if(name == null) {
			throw new IllegalArgumentException("Name can't be null"); 
		}
		this.name = name;
	}
	
	/**
	 * Method for getting name of variable
	 * @return name
	 */
	
	public String getName() {
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
