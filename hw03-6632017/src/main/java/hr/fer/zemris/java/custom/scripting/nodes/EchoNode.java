package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.Element;

/**
 *A node representing a command which generates some textual output dynamically.
 * It inherits from Node class.

 * @author Petra Barać
 *
 */

public class EchoNode extends Node{
	
	private Element[] elements; 
	
	/**
	 * Default constructor
	 * @param elements 
	 */
	
	public EchoNode(Element[] elements) {
		this.elements = elements;
	}
	
	/**
	 * Method for getting private variable
	 * @return elements 
	 */
	public Element[] getElements() {
		return elements;
	}
		
}
