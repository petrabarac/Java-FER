package hr.fer.zemris.java.custom.collections;
/**
 * Thrown when application tries to get object from stack while stack is empty
 * 
 * @author Petra Barać
 *
 */
public class EmptyStackException extends RuntimeException {
	
	/**
	 * Constructs a EmptyStackException with no detailed message
	 */
	
	public EmptyStackException() {
		super();
	}
	/**
	 * Constructs a EmptyStackException with no detailed message
	 * @param invalidSyndax the detailed message
	 */
	public EmptyStackException(String invalidSyndax) {
		    super("Invalid syntax: " + invalidSyndax);
		  }
		}