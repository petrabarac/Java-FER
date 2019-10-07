package hr.fer.zemris.java.hw07.observer2;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class in the Observer pattern design.
 * It holds private list of registered observers.
 * @author Petra Barać
 *
 */
public class IntegerStorage {

	private int numberOfChanges;
	private int value;
	private List<IntegerStorageObserver> observers; 	// use ArrayList here!!!
	
	public IntegerStorage(int initialValue) {
		this.value = initialValue;
		numberOfChanges = 0;
		observers = new ArrayList<>();
	}
	
	public void addObserver(IntegerStorageObserver observer) {
		if(!observers.contains(observer)) {
			observers.add(observer);
		}
	}
	public void removeObserver(IntegerStorageObserver observer) {
		observers.remove(observer);
	}
	public void clearObservers() {
		observers.removeAll(observers);
	}
	public int getValue() {
		return value;
	}
	
	public int getNumberOfChanges(){
		return numberOfChanges;
	}
	public void setNumberOfChanges(int num) {
		this.numberOfChanges = num;
	}
	
	public void setValue(int value) {

		if(this.value!=value) {

			this.value = value;
			
			IntegerStorageChange isChange = new IntegerStorageChange(this, this.value, value);
			
			if(observers!=null) {
				
				List<IntegerStorageObserver> copyObservers = new ArrayList<>();
				copyObservers.addAll(observers);
				
				for(IntegerStorageObserver observer : copyObservers) {
					observer.valueChanged(isChange);
				}
			}
		}
	}
	
	
}
