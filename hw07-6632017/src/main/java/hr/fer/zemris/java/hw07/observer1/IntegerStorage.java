package hr.fer.zemris.java.hw07.observer1;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class in the Observer pattern design.
 * It holds private list of registered observers.
 * @author Petra Barać
 *
 */
public class IntegerStorage {

	/**
	 * variable that stores how many times given value have changed
	 */
	private int numberOfChanges;

	private int value;
	/**
	 * Private list of observers
	 */
	private List<IntegerStorageObserver> observers; 	// use ArrayList here!!!
	
	
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		numberOfChanges = 0;
		observers = new ArrayList<>();
	}
	/**
	 * add observer into list
	 * @param observer observer
	 */
	public void addObserver(IntegerStorageObserver observer) {
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}
	
	/**
	 * remove observer from the list
	 * @param observer observer
	 */
	public void removeObserver(IntegerStorageObserver observer) {
		observers.remove(observer);
	}
	
	/**
	 * remove all observers
	 */
	public void clearObservers() {
		observers.removeAll(observers);
	}
	
	/**
	 * return stored value
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Setter for value.
	 * Method get new value and notify all registered observers that value have changed
	 * @param value new value
	 */
	public void setValue(int value) {
	// Only if new value is different than the current value:
		if(this.value!=value) {
	// Update current value
			this.value = value;
	// Notify all registered observers
			if(observers!=null) {
				
				List<IntegerStorageObserver> copyObservers = new ArrayList<>();
				copyObservers.addAll(observers);
				
				for(IntegerStorageObserver observer : copyObservers) {
					observer.valueChanged(this);
				}
			}
		}
	}
	
	
	/**
	 * Getter for numberOfchanges
	 * @return
	 */
	public int getNumberOfChanges(){
		return numberOfChanges;
	}
	
	/**
	 * setter for numberOfChanges
	 * @param num new value for numberOfChanges
	 */
	public void setNumberOfChanges(int num) {
		this.numberOfChanges = num;
	}
	

	
}
