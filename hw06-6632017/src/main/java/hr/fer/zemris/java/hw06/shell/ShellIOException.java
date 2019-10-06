package hr.fer.zemris.java.hw06.shell;

/**
 * Creates exception that will be throw every time when there is invalid used of shell
 * 
 * @author Petra Barać
 *
 */

public class ShellIOException extends RuntimeException {
	
	public ShellIOException() {
		super();
	}
	public ShellIOException(String message) {
		super(message);
	}

}
