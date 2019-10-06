package hr.fer.zemris.java.custom.collections;

/**
 * Resizable array-backed collection of objects which extends class Collection
 * @author Petra Barać
 *
 */

public class ArrayIndexedCollection extends Collection{

	private int capacity;
	private int size;
	private Object[] elements;
	
	/**
	 * Default constructor
	 * Set capacity of collection to 16
	 */
	public ArrayIndexedCollection() {
		this(16);
	}
	
	/**
	 * Constructor that preallocate the elements array to size of given capacity.
	 * @param initialCapacity capacity of created collection
	 * @throws IllegalArgumentException if given capacity is less than 1
	 */
	
	public ArrayIndexedCollection(int initialCapacity) {
				
		if(initialCapacity < 1) {
			throw new IllegalArgumentException(); 
		} 
		this.capacity = initialCapacity;
		this.elements = new Object[this.capacity];
	}
	
	/**
	 * Constructor accept other collection to add to constructed collection
	 * @param collection collection which elements are copied into this newly
	 * constructed collection;
	 */
	public ArrayIndexedCollection(Collection collection) {
		this(collection, collection.size());
	}

	/**
	 * Constructor that accept two additional parameter explained billow.
	 * @param collection collection which elements are copied into this newly
	 * constructed collection;
	 * @param initialCapacity capacity of created collection
	 */
	public ArrayIndexedCollection(Collection collection, int initialCapacity) {
		this(initialCapacity);
		this.addAll(collection);
	}
	/**
	 * Get capacity of collection
	 * @return capacity of collection
	 */
	public int capacity() {
		return this.capacity;
	}
	/**
	 * @see package hr.fer.zemris.java.custom.collections#size();
	 */
	@Override
	public int size() {
		return this.size;
	}
	/**
	 * 
	 */
	public void reallocate() {
		capacity = capacity*2;
		Object[] reallocatedElements = new Object[capacity];	
		for(int i = 0; i < size; i++) {
			reallocatedElements[i] = elements[i];
		}		
		elements = reallocatedElements;
	}
	/**
	 * Adds the given object into this collection
	 * If the elements array is full, it is reallocated by doubling its size.
	 * @throws NullPointerException if given value is null
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}
		
		if(size == capacity) {
			reallocate();
		}
		elements[size++]= value;
	}
	
	/**
	 * @see package hr.fer.zemris.java.custom.collections#forEach();
	 */
	@Override
	public void forEach(Processor processor) {
		for(int i = 0; i < size; i++) {
			processor.process(elements[i]);
		}
	}
	/**
	 * Returns the object that is stored in backing array at position index
	 * @param index position of element that will be return
	 * @return element at position index in collection
	 */
	public Object get(int index) {
		if(index < 0 || index > size-1) { 
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}
	
	/**
	 * @see package hr.fer.zemris.java.custom.collections#clear();
	 */
	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in array
	 * @param value value of object that will be inserted
	 * @param position position in collection where object will be inserted
	 */
	public void insert(Object value, int position) {
		
		if(position < 0 || position > size) {
			throw new IndexOutOfBoundsException();
		}		
		if(size + 1 > capacity) {
			reallocate();
		}
		for(int i = size; i > position; i--) {
			elements[i]=elements[i-1];
		  }
		elements[position]=value;
		size=size + 1;
		  
	}
	/**
	 * Searches the collection and returns the index of the first
	 *  occurrence of the given value or -1 if the value is not found
	 * @param value value which index will be returned
	 * @return index of given value, or -1 if collection does not contain given value
	 */
	public int indexOf(Object value) {
		for(int i = 0; i < size; i++) {
			if(elements[i].equals(value)) {
				return i;
			}
		} 
		return -1;		
	}
	
	/**
	 * Removes element at specified index from collection
	 * @param index index of element that will be removed from collection
	 * @throws IndexOutOfBoundsException in case of invalid index throw an appropriate
	 */

	public void remove(int index) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}		  
		for(int i = index; i < size-1; i++) {
			elements[i] = elements[i+1];	  
		  }
		 elements[size-1]=null;
		 size = size - 1;		
	}
	/**
	 * @see package hr.fer.zemris.java.custom.collections#remove();
	 */
	@Override
	public boolean remove(Object value) {
		int index = indexOf(value);
		if(index == -1) {
			return false;
		}
		remove(index);
		return true;	
		
	}
	/**
	 * Help method that remove last element from collection
	 */
	public void removeLast() {
		elements[size - 1] = null;
		size--;
	}

	/**
	 * @see package hr.fer.zemris.java.custom.collections#contains();
	 */
	@Override
	public boolean contains(Object value) {
		if(indexOf(value) == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * @see package hr.fer.zemris.java.custom.collections#toArray();
	 */
	
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		for(int i = 0; i < size; i++) {
			array[i] = elements[i];
		}
		return array;		
		
	}	
	
	
}
