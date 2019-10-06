package hr.fer.zemris.java.custom.collections;

/**
 * Interface  for getting each element stored in collection
 * Contains one default method explained bellow
 * 
 * @author Petra Barać
 *
 */
public interface ElementsGetter<T>  {
	/**
	 *Method that checks if collection contains any more elements.
	 *Check if next elements exist.
	 */
	boolean hasNextElement();
	
	/**
	 *Method that returns first next element from collection that has not been returned yet.
	 */
	T getNextElement();
	
	/**
	 * This method call given processor for the rest elements in collection
	 * @param p represents called processor
	 */
	default void processRemaining(Processor<T> p) {
		while(this.hasNextElement()) {
			p.process(this.getNextElement());
		}
		
	}
}

