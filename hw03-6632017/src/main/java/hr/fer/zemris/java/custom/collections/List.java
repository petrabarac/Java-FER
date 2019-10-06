package hr.fer.zemris.java.custom.collections;


/**
 * Interface with abstract methods list bellow;
 * @author Petra Barać
 *
 */

public interface List extends Collection{
	/**
	 *abstract method for getting element on position index
	 *@param index index that represent position in collection
	 */
	Object get(int index);
	
	/**
	 * MEthod for inserting new object in collection on chosen valid index
	 * @param value value of object that will be inserted
	 * @param position position in collection where object will be inserted
	 */
	void insert(Object value, int position);
	
	/**
	 * Method for returning index position of object from collection
	 * @param value value of object that index will be return
	 * @return index index of object
	 */
	int indexOf(Object value);
	
	/**
	 * Method for removing object on given valid index
	 * @param index index that represent position of object in collection
	 */
	void remove(int index);

}
