package hr.fer.zemris.java.hw07.observer2;

/**
 * Concrete observer.
 * Write to the standard output double value (i.e. “value * 2”) of the current value which is stored in subject, 
 * but only first n times since its registration with the subject (n is given in constructor).
 * 
 * @author Petra Barać
 *
 */


public class DoubleValue implements IntegerStorageObserver {
	
	private int n;
	public DoubleValue(int n) {
		this.n = n;
	}

	@Override
	public void valueChanged(IntegerStorageChange isChange) {

		n--;
		if(n > 0) {
			int value = isChange.getCurrentValue();
			System.out.println("Double value: " + (value * 2));
		} else {
			isChange.getInStorage().removeObserver(this);
		}
	}

}
