package hr.fer.zemris.java.hw03.prob1;


/**
 * Thrown if any exception happens in Lexer work
 * @author Petra Barać
 *
 */

public class LexerException extends RuntimeException {
	
	public LexerException() {
		super();
	}
	public LexerException(String message) {
		super(message);
	}

}
