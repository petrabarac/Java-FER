package hr.fer.zemris.java.custom.collections;

/**
 * Class implements simple stack.with methods for working with stack.
 * 
 * @author Petra Barać
 *
 */
public class ObjectStack {
	
	/**
	 * Array that will store elements of stack.
	 */

	private ArrayIndexedCollection adaptee = new ArrayIndexedCollection();
	
	/**
	 * Default constructor 
	 */
	public ObjectStack() {
		adaptee = new ArrayIndexedCollection();
	}
	
	/**
	 * Check if there are elements in the stack. Return false if it does, otherwise true.
	 */
	
	public boolean isEmpty() {
		return adaptee.isEmpty();
	}
	/**
	 * Return current number of objects on stack
	 * @return current number of objects on stack
	 */
	public int size() {
		return adaptee.size();
	}
	
	/**
	 * Pushes given object on the stack
	 * @param value value that should e pushed on tack
	 */
	public void push(Object value) {
		adaptee.add(value);
	}
	
	/**
	 * Removes last value pushed on stack from stack and returns it
	 * @return last value pushed on stack.
	 * @throws EmptyStackException if stack is empty
	 */
	
	public Object pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}		
		Object popObject = adaptee.get(size() - 1);
		adaptee.removeLast();
		return popObject;
	}
	
	/**
	 * Returns last element placed on stack but does not delete it from stack
	 * @return last element pushed on stack
	 * @throws EmptyStackException if stack is empty
	 */
	
	public Object peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}		
		return adaptee.get(size() - 1);
	}
	
	/**
	 * Removes all elements from stack.
	 */
	public void clear() {
		adaptee.clear();
	}
	
	
	
	
	
	
	
	
	
	
}
