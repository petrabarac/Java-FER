package hr.fer.zemris.java.hw07.observer1;

/**
 * Representation of Observer interface .
 * All actions must implement this interface.
 * Every time the objects' state changes, the object will invoke method "valueChanged" on all of the registered actions.
 * 
 * @author Petra Barać
 *
 */

public interface IntegerStorageObserver {
	public void valueChanged(IntegerStorage istorage);
}
