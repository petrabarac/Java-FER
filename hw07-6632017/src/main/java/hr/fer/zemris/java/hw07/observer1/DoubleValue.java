package hr.fer.zemris.java.hw07.observer1;

/**
 * Concrete observer.
 * Write to the standard output double value (i.e. “value * 2”) of the current value which is stored in subject, 
 * but only first n times since its registration with the subject (n is given in constructor).
 * 
 * @author Petra Barać
 *
 */

public class DoubleValue implements IntegerStorageObserver {
	/**
	 * Number that defines how many times this action will be done.
	 */
	private int n;
	
	public DoubleValue(int n) {
		this.n = n;
	}

	@Override
	public void valueChanged(IntegerStorage istorage) {
		n--;
		if(n > 0) {
			int value = istorage.getValue();
			System.out.println("Double value: " + (value * 2));
		} else {
			istorage.removeObserver(this);
		}
	}

}
