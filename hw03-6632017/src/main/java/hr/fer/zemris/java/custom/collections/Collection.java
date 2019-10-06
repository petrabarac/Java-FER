package hr.fer.zemris.java.custom.collections;

/**
 * Interface represents general collection of objects.
 * 
 * @author Petra Barać
 *
 */

public interface Collection {

	/**
	 * Default protected constructor.
	 */
	//void Collection();
	
	/**
	 * @return true if collection contains any objects, otherwise false..
	 */
	
	default boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return number of currently stored objects in this collections
	 */
	int size();
	
	/**
	 * Adds the given object into this collection
	 * @param value object to add in collection.
	 */
	void add(Object value);			
	/**
	 * Check if collection contain sent object
	 * @param value object that will be check if collection contains
	 * @return true only if the collection contains given value ,
	 */
	boolean contains(Object value);
	/**
	 * Remove object from collection
	 * @param value value that will be removed from collection
	 * @return true if value is successfully removed, else return false.
	 */
	boolean remove( Object value);
	
	/**
	 * Allocates new array with size equals to the size of this collections,
	 *  fills it with collection content and
	 *  returns the array.
	 * @return UnsupportedOperationException
	 */
	Object[] toArray();
	

	/**
	 * Method adds into the current collection
	 *  all elements from the given collection
	 * @param other collection which elements will be added to this collection
	 */
	default void addAll(Collection other) {
		
		/**
		 * local processor class whose method process
		 *  will add each item into the current collection
		 *  
		 * @author Petra Barać
		 *
		 */
		class LocalProcessor implements Processor {
			@Override
			public void process(Object value) {
				add(value);							
			}	
		}
		LocalProcessor localProcessor= new LocalProcessor();
		other.forEach(localProcessor);
	}
	
	/**
	 * Removes all elements from this collection.
	 */
	void clear();
			
	ElementsGetter createElementsGetter();
	
	default void addAllSatisfying(Collection col, Tester tester) {
		ElementsGetter getter = col.createElementsGetter();
		Object value;
		
		while(getter.hasNextElement()) {
			value = getter.getNextElement();			
			
			if(tester.test(value)) {
				this.add(value);
			}
		}
		
	}
	
	/**
	 * Method calls processor.process(.) 
	 * for each element of this collection
	 * @param processor processor to which elements will be send
	 */
	
	default void forEach(Processor processor) {
		ElementsGetter getter = this.createElementsGetter();
		
		while(getter.hasNextElement()) {	
			processor.process(getter.getNextElement());
		}
	
	}
}





