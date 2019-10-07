package hr.fer.zemris.java.hw07.observer2;

/**
 * Concrete observer. 
 * 
 * Counts (and writes to the standard output) the number of times the 
 * value stored has been changed since this observer’s registration.
 * 
 * @author Petra Barać
 *
 */
public class ChangeCounter implements IntegerStorageObserver {

	@Override
	public void valueChanged(IntegerStorageChange isChange) {
		IntegerStorage istorage = isChange.getInStorage();
		int num = istorage.getNumberOfChanges();
		num++;
		istorage.setNumberOfChanges(num);
		System.out.println("Number of value changes since tracking: " + num);
	}

}
