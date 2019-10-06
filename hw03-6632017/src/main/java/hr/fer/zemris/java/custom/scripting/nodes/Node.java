package hr.fer.zemris.java.custom.scripting.nodes;

import  hr.fer.zemris.java.custom.collections.*;


import hr.fer.zemris.java.custom.collections.ArrayIndexedCollection;

/**
 * aBse class for all graph nodes.
 * @author Petra Barać
 *
 */

public class Node {
	
	private ArrayIndexedCollection collectionOfChildren;
	
	/**
	 * Method adds given node in collection
	 * @param child given node
	 */
	
	public void addChildNode(Node child) {
		
		if(collectionOfChildren == null) {
			collectionOfChildren = new ArrayIndexedCollection();
		}
		collectionOfChildren.add(child);				
	}
	
	/**
	 *  Get number of children in collection that is represent by size of collection
	 * @return size
	 */
	public int numberOfChildren() {
		
		if(collectionOfChildren == null) {
			return 0;
		}
		
		return collectionOfChildren.size();
	}
	
	/**
	 * Find child in collection on given index and returns it as Node
	 * @param index index od child in collection
	 * @return child Node at given index
	 */
	
	public Node getChild(int index) {
		
		if( index > collectionOfChildren.size()) {
			throw new IndexOutOfBoundsException("Child with given index does not exist");
		}
		
		return (Node)collectionOfChildren.get(index);
		
	}

}
