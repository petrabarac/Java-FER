package hr.fer.zemris.java.hw07.observer2;

/**
 * Concrete observer.
 * Write a square of the integer stored in the IntegerStorage to the standard output.
 * 
 * @author Petra Barać
 *
 */

public class SquareValue implements IntegerStorageObserver {

	@Override
	public void valueChanged(IntegerStorageChange isChange) {
		
		int value = isChange.getCurrentValue();
		System.out.println("Provided new value: " + value + ", square is " + (value * value));
	}

}
