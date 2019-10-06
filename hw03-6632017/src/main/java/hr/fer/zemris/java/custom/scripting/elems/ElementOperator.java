package hr.fer.zemris.java.custom.scripting.elems;

/**
 * inherits Element  and has single read-only String property: symbol 

 * @author Petra Barać
 *
 */

public class ElementOperator extends Element {
	
	private String symbol;
	
	/**
	 * Default constructor used for setting element symbol to given symbol
	 * @param symbol is given symbol
	 * @throws IllegalArgumentException because given symbol can't be null
	 */
	
	public ElementOperator(String symbol) {
		if(symbol == null) {
			throw new IllegalArgumentException("Symbol can't be null"); 
		}
		
		this.symbol = symbol;
	}
	/**
	 * Method for getting symbol
	 * @return symbol
	 */

	public String getValue() {
		return symbol;
	}
	/**
	 * Override method asText()
	 * @return symbol property
	 */
	
	@Override
	public String asText() {
		return symbol;
	}

}
