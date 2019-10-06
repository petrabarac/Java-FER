package hr.fer.zemris.java.custom.collections;

/**
 * Interface that represent Processor with one abstract method
 * @author Petra Barać
 *
 */
public interface Processor<T> {
	/**
	 * Method that process given value
	 * @param value value to be processed
	 */
	void process(T value);
}
