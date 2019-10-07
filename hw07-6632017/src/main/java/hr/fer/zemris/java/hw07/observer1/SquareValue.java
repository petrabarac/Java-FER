package hr.fer.zemris.java.hw07.observer1;

/**
 * Concrete observer.
 * Write a square of the integer stored in the IntegerStorage to the standard output.
 * 
 * @author Petra Barać
 *
 */

public class SquareValue implements IntegerStorageObserver {

	@Override
	public void valueChanged(IntegerStorage istorage) {
		int value = istorage.getValue();
		System.out.println("Provided new value: " + value + ", square is " + (value * value));
	}

}
