package hr.fer.zemris.java.custom.collections;

import java.util.EmptyStackException;

/**
 * Class implements simple stack with methods for working with stack.
 * 
 * @author Petra Barać
 *
 */
public class ObjectStack<T> {
	
	/**
	 * Array that will store elements of stack.
	 */

	private ArrayIndexedCollection<T> adaptee = new ArrayIndexedCollection<T>();
	
	/**
	 * Default constructor 
	 */
	public ObjectStack() {
		adaptee = new ArrayIndexedCollection<T>();
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
	public void push(T value) {
		adaptee.add(value);
	}
	
	/**
	 * Removes last value pushed on stack from stack and returns it
	 * @return last value pushed on stack.
	 * @throws EmptyStackException if stack is empty
	 */
	
	@SuppressWarnings("unchecked")
	public T pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}		
		Object popObject = adaptee.get(size() - 1);
		adaptee.removeLast();
		return (T) popObject;
	}
	
	/**
	 * Returns last element placed on stack but does not delete it from stack
	 * @return last element pushed on stack
	 * @throws EmptyStackException if stack is empty
	 */
	
	@SuppressWarnings("unchecked")
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}		
		return (T) adaptee.get(size() - 1);
	}
	
	/**
	 * Removes all elements from stack.
	 */
	public void clear() {
		adaptee.clear();
	}

}
