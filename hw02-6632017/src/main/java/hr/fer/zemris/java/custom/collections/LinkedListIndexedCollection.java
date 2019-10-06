package hr.fer.zemris.java.custom.collections;

/**
 * Linked list-backed collection of objects which extends class Collection
 * @author Petra Barać
 *
 */

public class LinkedListIndexedCollection extends Collection {	
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
		ListNode current = first;
		for(int i = 0; i < size; i++) {
			array[i] = current.value;
			current = current.next;
		}
		return array;		
	}
	
	/**
	 * @see package hr.fer.zemris.java.custom.collections#forEach();
	 */
	@Override
	public void forEach(Processor processor) {
		ListNode current = first;
		for(int i = 0; i < size; i++) {
			processor.process(current.value);
			current = current.next;
		}
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
	}
	
	/**
	 * Inserts (does not overwrite) the given value at the given position in linked-list
	 * @param value value value of object that will be inserted
	 * @param position position in collection where object will be inserted
	 */
	void insert(Object value, int position) {
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
	}
	
	/**
	 * Searches the collection and returns the index 
	 * of the first occurrence of the given 
	 * value or -1 if the value is not found
	 * @param value value which index will be returned
	 * @return index of given value, or -1 if collection does not contain given value
	 */
	
	int indexOf(Object value) {
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
