package hr.fer.zemris.java.hw03.prob1;

import hr.fer.zemris.java.custom.scripting.lexer.SmartScriptLexerState;

/**
* Class used for dividing input text and generating 
* smaller lexical parts to generate tokens.
* Lexer have to states listed in enumeration  LexerState.
* 
* @author Petra Barać
*/

public class Lexer {
	/**
	 * Represents given text stored in char array
	 */
	private char[] data; 
	/**
	 * Variable to store lexing tokens.
	 */
	private Token token; 
	/**
	 * index of current element
	 */
	private int currentIndex; 
		
	/**
	 * Start state of Lexer
	 */
	private LexerState state = LexerState.BASIC;
		
	/**
	 * Public constructor that has text as argument. 
	 * Given text will be lexically analyzed
	 * @param text given text to analyze
	 * @exception NullPointerException given text can't be null
	 */
	public Lexer(String text) { 
		if(text == null ) {
			throw new NullPointerException();
		}
		data = text.toCharArray();
		token = null;
		currentIndex = 0; 
	}
	
	/**
	 * Sets lexer's state. State can be BASIC or EXTENDED
	 * @param state state of this Lexer
	 */
	public void setState(LexerState state) {
		if(state ==  null && !(state instanceof LexerState)) {
			throw new NullPointerException();
		}
	this.state = state;
	}
	
	/**
	 * 	Depending on the state, lexer analyzes word
	 * until  according Token
	 * @return generated token
	 */
	public Token nextToken() {
			
		if(token != null && token.getType() == TokenType.EOF) {
			throw new LexerException("No tokens available.");
		}
			
		skipBlanks();

		if(currentIndex == data.length) {
			token = new Token(TokenType.EOF, null);
			return token;
		}
			
		switch(state) {
			case BASIC :
				token = basicState();
				break;
			case EXTENDED: token = extendedState();
				break;						
		}
		return token;

	}
	
	/**
	 * When lexer is in BASIC state, it generate tokens that can be word, number or symbol
	 * @return generated token
	 */
		
	public Token basicState() {
			
		if(Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\') {
			Object value = wordToken();
				
			token = new Token(TokenType.WORD, value);
			return token;
				
		} else if(Character.isDigit(data[currentIndex])) {
			Object value; 
			try {
				value = numberToken();
			} catch (NumberFormatException e) {
				throw new LexerException();
			}

			token = new Token(TokenType.NUMBER, value);
			return token;	
				
		} else {	
				
			token = new Token(TokenType.SYMBOL, data[currentIndex]);
			currentIndex++;
			return token;	
				
		}					
	}
	
	/**
	 * When lexer is in EXTENDED state, it generates everytnig between "#" symbols
	 * as word token, but without last "#" symbol. That is symbol for itself
	 * @return  generated token
	 */
		
	public Token extendedState() {
			
		skipBlanks();
		StringBuilder sb = new StringBuilder();
	
		while(data[currentIndex] != '#') {
				
			if(data[currentIndex] == ' ') {
				break;
			}
				
			sb.append(data[currentIndex]);
			currentIndex++;	
		}
		if(sb.length() > 0) {
			return new Token(TokenType.WORD, sb.toString());
		}
		else {
			currentIndex++;
			return new Token(TokenType.SYMBOL, '#');
		}
	}
		
		
	/**
	 * Getter method for token
	 * @return last generated token
	 */
		
	public Token getToken() {
		return token;
	}
	
	/**
	 * Used for skip all blank spaces 
	 */
		
	public void skipBlanks() {
			
		while(currentIndex < data.length) {
			if(Character.isWhitespace(data[currentIndex])) {
				currentIndex++;
			}else {
				break;
			}			
		}
			
	}
	/**
	* When lexer is in BASIC state, this method generated word token.
	* Also check, if there is any, if is escape sequence used correctly
	* @return string that will be value of generated token
	*/
		
	public String wordToken() {
		StringBuilder sb = new StringBuilder();
			
			
		while(currentIndex < data.length) {
			if(Character.isLetter(data[currentIndex]) || data[currentIndex] == '\\') {

				if(data[currentIndex] == '\\') {
						
					if (currentIndex >= data.length - 1) {
						throw new LexerException("Character  was expected after \\.");
					}
						
					if(!(data[currentIndex + 1] == '\\' || Character.isDigit(data[currentIndex + 1]))) {
						throw new LexerException();
					}
						
					currentIndex++;	
				}
				sb.append(data[currentIndex]);
				currentIndex++;
		
			} else {
				break;
			}
		}
		return sb.toString();
	}
				


	/**
	 * When lexer is in BASIC state, if given data character is number it creates number type token
	 * @return number that will be value of new generated token
	 * @throws NumberFormatException NumberFormatException if given number is to big
	 */
		
	public long numberToken() throws NumberFormatException {
		StringBuilder sb = new StringBuilder();
			
		while(currentIndex < data.length && Character.isDigit(data[currentIndex])) {
				
			sb.append(data[currentIndex]);
			currentIndex++;	
		}
			
			
		return Long.parseLong(sb.toString());
	}
		
			

}
