package hr.fer.zemris.java.custom.collections;

import java.util.NoSuchElementException;

/**
 * Linked list-backed collection of objects which implements List
 * @author Petra Barać
 *
 */

public class LinkedListIndexedCollection implements List {	
	/**
	 *reference to the first node of the linked list
	 */
	private ListNode first;
	/**
	 *reference to the last node of the linked list.
	 */
	private ListNode last;
	/**
	 * current size of collection
	 */
	private int size;	
	
	private long modificationCount = 0;

	
	/**
	 * Class that represents one node in the list.
	 * Stores value of an object, pointer to previous node and pointer to next node
	 * 
	 * @author Petra Barać
	 *
	 */
	private static class ListNode {
		/**
		 * additional reference for value storage
		 */
		Object value;
		/**
		 * pointer to previous ListNode
		 */
		ListNode previous;
		/**
		 * pointer to next ListNode
		 */
		ListNode next;		
	}
	/**
	 * Private class that represent ElementsGeter interface 
	 * that know how to get elements 
	 * @author Petra Barać
	 *
	 */
	private static class ListElementsGetter implements ElementsGetter{
		
		private ListNode listFirst;
		private ListNode listLast;
		private ListNode current;
		private int listSize;
		private long savedModificationCount;
		
		/**
		 * Constructor with parameters listed bellow
		 * @param first first elements in list
		 * @param last last element in list
		 * @param size size of list
		 * @param modificationCount check if any modification has happened
		 */
		public ListElementsGetter(ListNode first, ListNode last, int size,long modificationCount) {
			super();
			listFirst = first;
			listLast = last;
			listSize= size;
			 savedModificationCount = modificationCount;
			current = first;
		}
		/**
		 * Check if list has any more elements
		 */
		
		public boolean hasNextElement() {
			return current != null;
		}
		/**
		 * Get next element that has not been already got
		 */
		public Object getNextElement() {		
			
			if(!hasNextElement()) {
				throw new NoSuchElementException();
			} else {
				Object element = current.value;
				current = current.next;
				return element;
			}
		} 
	}
		
	/**
	 * Create new EllementsGetter
	 */
	
	@Override
	public ElementsGetter createElementsGetter() {
		
		ElementsGetter getter = new ListElementsGetter(first, last, size, modificationCount);
		
		return getter;
	}
		
	/**
	 * Default constructor
	 */
	public LinkedListIndexedCollection() {
		first = null;
		last = null;
		size = 0;
	}
	
	/**
	 * Constructor that accept additional parameter collection
	 * @param collection collection which elements are copied into this newly
	 * constructed collection;
	 */
	public LinkedListIndexedCollection(Collection collection) {
		this.addAll(collection);
	}
	
	/**
	 * Get first node
	 * @return first pointer to first node of list
	 */
	public ListNode first() {
		return first;
	}
	
	/**
	 * Get last node
	 * @return pointer to last node in list
	 */
	public ListNode last() {
		return last;
	}
	/**
	 * @see package hr.fer.zemris.java.custom.collections#size()
	 */
	@Override 
	public int size() {
		return size;
	}
	/**
	 * Adds the given object into this collection at the end of collection
	 * @throws NullPointerException if given value is null
	 */
	@Override
	public void add(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}
		ListNode newNode = new ListNode();
		newNode.value = value;
		
		if(first == null) {
			last = newNode;
			first = last;			
		} else {		
			newNode.previous = last;
			last.next = newNode;
			last = newNode;	
		}		
		size = size + 1;
		modificationCount++;
	}
	/**
	 * Returns the object that is stored in linked list at position index .
	 * @param index position of element that will be return 
	 * @return object stored at given index in linked list
	 * @throws IndexOutOfBoundsException if given index is invalid
	 */
	public Object get(int index) {
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		ListNode current;
		
		int middle = size/2 + 1;
		if(index <= middle) {
			current = first;
			for(int i = 0; i < index; i++) {
				current = current.next;
			} 
		} else {
			current = last;
			for(int i = size - 1; i > index; i--) {
				current = current.previous;
			}	
		}
		return current.value;			
	}
	/**
	 * Removes element at specified index from collection
	 * @param index position of element that will be removed
	 * index position of element that will be returned
	 * @throws IndexOutOfBoundsException if invalid index is given
	 */
	public void remove(int index) {
		if(index < 0 || index > size ) {
			throw new IndexOutOfBoundsException();
		}

			if(index == 0) {
				if(size == 1) {
					first = null;
					last = null;
				} else {
					first.next.previous = null;
					first = first.next;
				}				
			} else if(index ==  size - 1) {
				last.previous.next = null;
				last = last.previous;	
			} else {
				ListNode current=first;
				for(int i = 0; i < index; i++) {
					current = current.next;
				}		
				current.previous.next = current.next;
				current.next.previous = current.previous;
				current.next=current.previous = null;
			}
			size--;
			modificationCount++;
	}
	
	/**
	 * Remove given value from list by findig index of value in list and 
	 * sanding it to remove(int index) method
	 * @return true if given index is valid
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
	 * Check if given value is in collection
	 * @return true if collection contains given value, otherwise false
	 */
	@Override		
	public boolean contains(Object value) {
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
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		ListNode current = first;
		for(int i = 0; i < size; i++) {
			array[i] = current.value;
			current = current.next;
		}
		return array;		
	}
	
	
	/**
	 * Removes all elements from the collection. 
	 * Collection “forgets” about current linked list.
	 */
	
	@Override
	public void clear() {
		ListNode current=first.next;
		while(current != null) {
			ListNode node = current.next;
			current.previous=null;
			current.next=null;
			current=node;	
			
		}
		first.next=null;
		first.previous=null;
		
		first=null;
		last=null;
		size = 0;
		modificationCount++;
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in linked-list
	 * @param value value value of object that will be inserted
	 * @param position position in collection where object will be inserted
	 */
	public void insert(Object value, int position) {
		if(position < 0 || position > size ) {
			throw new IndexOutOfBoundsException();
		}
		ListNode newNode = new ListNode();
		newNode.value=value;
		
		if(position == 0) {
			first.previous=newNode;
			newNode.next=first;
			first=newNode;
		} else {
			ListNode current = first;
			for(int i = 0; i < position; i++) {
				current=current.next;
			}
			
			current.previous.next=newNode;
			newNode.previous=current.previous;
			newNode.next=current;
			current.previous=newNode;
		}
		modificationCount++;
	}
	
	/**
	 * Searches the collection and returns the index 
	 * of the first occurrence of the given 
	 * value or -1 if the value is not found
	 * @param value value which index will be returned
	 * @return index of given value, or -1 if collection does not contain given value
	 */
	
	public int indexOf(Object value) {
		ListNode current=first;
		for(int i = 0; i < size; i++) {
			if(current.value.equals(value)) {
				return i;
			}
			current=current.next;
		}
		return -1;
	}
	


}
