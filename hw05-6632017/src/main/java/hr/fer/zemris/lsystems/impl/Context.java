package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.*;

/**
 * Razred čiji primjerci omogućavaju izvođenje
 * postupka prikazivanja fraktala te nude stog na koji je
 * moguće stavljati i dohvaćati stanja kornjače
 * (što je kornjača objašnjeno je u TurtleState razredu)
 * 
 * @author Petra Barać
 *
 */

public class Context {
	

	private ObjectStack<TurtleState> stack;
	
	
	public Context() {
		stack = new ObjectStack<TurtleState>();
	}
	
	
	/**
	 * Javna metoda koja vraća stanje s vrha stoga
	 *  bez uklanjanja istog
	 * @return stanje sa vrha stoga
	 */
	public TurtleState getCurrentState() {
		return stack.peek();
	}
	
	/**
	 *Javna metoda koja na vrh stoga gura predano stanje
	 * @param state je predano stanje
	 */
	public void pushState(TurtleState state) {
		stack.push(state);
	}
	
	/**
	 *Javna metoda koja briše jedno stanje s vrha stofa
	 */
	public void popState() {
		stack.pop();
	}
	
}
