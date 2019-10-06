package hr.fer.zemris.java.custom.scripting.nodes;

import hr.fer.zemris.java.custom.scripting.elems.*;

/**
 *A node representing a single for-loop construct. It inherits from Node class
 * @author Petra Barać
 *
 */

public class ForLoopNode extends Node {
	/**
	 * keeps variable in for loop
	 */
	private ElementVariable variable;
	/***
	 *  keeps start expression of for loop
	 */
	private Element startExpression;
	/**
	 *  keeps end expression of for loop
	 */
	private Element endExpression;
	/**
	 * keep step expression of for loop
	 */
	private Element stepExpression;
	
		/**
		 * Default constructor for creating for loop node with valid parameters listed bellow
		 * @param variable 
		 * @param startExpression
		 * @param endExpression
		 * @param stepExpression 
		 */
	public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression, Element stepExpression) {
		
		if(variable == null || startExpression == null || endExpression == null) {
			throw new IllegalArgumentException("Only stepExpression can be null");
		}
		
		this.variable = variable;
		this.startExpression = startExpression;
		this.endExpression = endExpression;
		this.stepExpression = stepExpression;
		
	}
	
	/**
	 * Getter method for variable property
	 * @return variable
	 */ 
	
	public ElementVariable getElementVariable() {
		return variable;
	}
	/**
	 * Getter method for start expression property
	 * @return start expression
	 */
	
	public Element getStartExpression() {
		return startExpression;
	}
	
	/**
	 * Getter method for end expression property
	 * @return end expression
	 */
	
	public Element getEndeExpression() {
		return endExpression;
	}
	/**
	 * Getter method for step expression property
	 * @return step expression
	 */
	
	public Element getStepExpresion() {
		return stepExpression;
	}

}
