package hr.fer.zemris.java.custom.scripting.nodes;

/**
 * A node representing a piece of textual data. It inherits from Node class.
 * @author Petra Barać
 *
 */

public class TextNode extends Node{
	
	private String text;
	/**
	 * Default constructor
	 * @param text
	 * @exception IllegalAccessException given text cant be null
	 */
	public TextNode(String text) {
		if(text == null) {
			throw new IllegalArgumentException("Empty text");
		}
		this.text = text;
	}
	
	/**
	 * Getter method for String text
	 * @return text
	 */
	public String getText() {
		return text;
	}

}
