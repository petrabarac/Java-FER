package hr.fer.zemris.java.custom.collections;

/**
 * Class represents some general collection of objects.
 * 
 * @author Petra Barać
 *
 */

public class Collection {

	/**
	 * Default protected constructor.
	 */
	protected Collection() {
	}
	
	/**
	 * @return true if collection contains any objects, otherwise false..
	 */
	
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @return number of currently stored objects in this collections
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * Adds the given object into this collection
	 * @param value object to add in collection.
	 */
	public void add(Object value) {
	}
			
	/**
	 * Check if collection contain sent object
	 * @param value object that will be check if collection contains
	 * @return true only if the collection contains given value ,
	 */
	public boolean contains(Object value) {
		return false;
	}
	/**
	 * Remove object from collection
	 * @param value value that will be removed from collection
	 * @return true if value is successfully removed, else return false.
	 */
	public boolean remove( Object value) {
		return false;		
	}
	
	/**
	 * Allocates new array with size equals to the size of this collections,
	 *  fills it with collection content and
	 *  returns the array.
	 * @return UnsupportedOperationException
	 */
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Method calls processor.process(.) 
	 * for each element of this collection
	 * @param processor processor to which elements will be send
	 */
	public void forEach(Processor processor) {
	}
	
	/**
	 * Method adds into the current collection
	 *  all elements from the given collection
	 * @param other collection which elements will be added to this collection
	 */
	public void addAll(Collection other) {
		
		/**
		 * local processor class whose method process
		 *  will add each item into the current collection
		 *  
		 * @author Petra Barać
		 *
		 */
		class LocalProcessor extends Processor {
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
	public void clear() {
		
	}

}





