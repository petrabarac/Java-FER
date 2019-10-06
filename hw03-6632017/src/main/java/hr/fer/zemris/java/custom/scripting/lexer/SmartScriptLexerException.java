package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * Thrown if any exception happens in Lexer work
 * @author Petra Barać
 *
 */

public class SmartScriptLexerException extends RuntimeException {
	
	public SmartScriptLexerException() {
		super();
	}
	public SmartScriptLexerException(String message) {
		super(message);
	}

}