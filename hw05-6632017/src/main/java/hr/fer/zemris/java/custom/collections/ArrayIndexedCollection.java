package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;



/**
 * Resizable array-backed collection of objects which implements List
 * @author Petra Barać
 *
 */


public class ArrayIndexedCollection<T> implements List<T> {
	
	public static final int defaultCapacity = 16; 

	private int capacity;
	private int size;
	private T[] elements;
	
	public long modificationCount = 0;
	
	/**
	 * Private static class for getting objects from collection
	 * and checking if there is any object left in collection
	 * @author Petra Barać
	 *
	 */
	private static class ArrayElementsGetter<T> implements ElementsGetter<T> {
		
		private int collectionSize;
		private int current = 0;
		private T[] arrayElements;
		private final long savedModificationCount;
		private ArrayIndexedCollection<T> parent;
		
			public ArrayElementsGetter(ArrayIndexedCollection<T> parent) {
				collectionSize = parent.size;
				arrayElements = parent.elements;
				savedModificationCount = parent.modificationCount;
				this.parent = parent;
			}
			
			public void isStructureChainged() {
				if(savedModificationCount != parent.modificationCount) {
					throw new ConcurrentModificationException();
				}
			}
			
			public boolean hasNextElement() {
				isStructureChainged();
				if(current < collectionSize) {
					return true;
				}
				return false;
			}
			
			public T getNextElement() {
				if(!hasNextElement()) {
					throw new NoSuchElementException();
				} else {
					return arrayElements[current++];
				}
			}
	}
	/**
	 * Method that creates ElementsGetter object
	 * @return created object
	 */
	

	@Override
	public ElementsGetter<T> createElementsGetter() {
		
		ElementsGetter<T> getter = new ArrayElementsGetter<>(this);
		
		return getter;
	}
		
	/**
	 * Constructor
	 * Set capacity of collection to defaultCapacity
	 */
	public ArrayIndexedCollection() {
		this(defaultCapacity);
	}
	
	/**
	 * Constructor that preallocate the elements array to size of given capacity.
	 * @param initialCapacity capacity of created collection
	 * @throws IllegalArgumentException if given capacity is less than 1
	 */
	
	@SuppressWarnings("unchecked")
	public ArrayIndexedCollection(int initialCapacity) {
				
		if(initialCapacity < 1) {
			throw new IllegalArgumentException(); 
		} 
		this.capacity = initialCapacity;
		this.elements = (T[])new Object[this.capacity];
	}
	
	/**
	 * Constructor accept other collection to add to constructed collection
	 * @param collection collection which elements are copied into this newly
	 * constructed collection;
	 */
	public ArrayIndexedCollection(Collection<T> collection) {
		this(collection, collection.size());
	}

	/**
	 * Constructor that accept two additional parameter explained billow.
	 * @param collection collection which elements are copied into this newly
	 * constructed collection;
	 * @param initialCapacity capacity of created collection
	 */
	public ArrayIndexedCollection(Collection<T> collection, int initialCapacity) {
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
	 * Used for get private variable size of collection
	 * @return size of collection
	 */
	@Override
	public int size() {
		return this.size;
	}
	/**
	 * Method that is used for allocating more space if it's necessary.
	 */
	public void reallocate() {
		capacity = capacity*2;
		@SuppressWarnings("unchecked")
		T[] reallocatedElements =(T[])new Object[capacity];
		for(int i = 0; i < size; i++) {
			reallocatedElements[i] = elements[i];
		}		
		elements = reallocatedElements;
		modificationCount++;
	}
	/**
	 * Adds the given object into this collection
	 * If the elements array is full, it is reallocated by doubling its size.
	 * @throws NullPointerException if given value is null
	 */
	@Override
	public void add(T value) {
		if (value == null) {
			throw new NullPointerException();
		}
		
		if(size == capacity) {
			reallocate();
		}
		elements[size++]= value;
		modificationCount++;
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
	 * Clear all objects from collection
	 */
	@Override
	public void clear() {
		for(int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
		modificationCount++;
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in array
	 * @param value value of object that will be inserted
	 * @param position position in collection where object will be inserted
	 */
	public void insert(T value, int position) {
		
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
		modificationCount++;
		  
	}
	/**
	 * Searches the collection and returns the index of the first
	 *  occurrence of the given value or -1 if the value is not found
	 * @param value value which index will be returned
	 * @return index of given value, or -1 if collection does not contain given value
	 */
	public int indexOf(T value) {
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
	     modificationCount++;
	}
	/**
	 * Remove given value from list by findig index of value in list and 
	 * sanding it to remove(int index) method
	 * @return true if given index is valid
	 */
	@Override
	public boolean remove(T value) {
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
		modificationCount++;
	}

	/**
	 * Check if given value is in collection
	 * @return true if collection contains given value, otherwise false
	 */
	@Override
	public boolean contains(T value) {
		if(indexOf(value) == -1) {
			return false;
		}
		return true;
	}
	
	/**
	 * Creates an array of the elements 
	 * in this collection in the correct order
	 * @return created array
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		Object[] array = new Object[size];
		for(int i = 0; i < size; i++) {
			array[i] = elements[i];
		}
		return (T[])array;		
		
	}	

}
