package hr.fer.zemris.java.hw07.observer2;

/**
 * /**
 * Class that encapsulates a reference to the IntegerStorage,
 *  the value of stored integer before the change has occurred,
 *  and the new value of currently stored integer.
 * @author Petra Barać
 *
 */

public class IntegerStorageChange {

	private IntegerStorage inStorage;
	
	private int oldValue;
	
	private int currentValue;

	public IntegerStorageChange(IntegerStorage inStorage, int oldValue, int currentValue) {
		super();
		this.inStorage = inStorage;
		this.oldValue = oldValue;
		this.currentValue = currentValue;
	}

	public IntegerStorage getInStorage() {
		return inStorage;
	}

	public int getOldValue() {
		return oldValue;
	}

	public int getCurrentValue() {
		return currentValue;
	}
	
	
	
}
