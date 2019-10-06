package hr.fer.zemris.java.custom.scripting.lexer;

import hr.fer.zemris.java.hw03.prob1.*;


/**
* Class used for dividing input text and generating 
* smaller lexical parts to generate tokens for Parser.
* Lexer have to states listed in enumeration SmartScriptLexerState
 * @author Petra Barać
 *
 */

public class SmartScriptLexer {
	/**
	 * Represents given text stored in char array
	 */
	private char[] data;
	/**
	 * index of current element
	 */
	private int currentIndex;
	/**
	 * Variable to store  tokens.
	 */
	private SmartScriptToken token;
	
	/**
	 * State of Lexer. Can be TEXT OR TAG
	 */
	private SmartScriptLexerState state;
	
	/**
	/**
	 * Public constructor that has text as argument. 
	 * Given text will be lexically analyzed
	 * @param text given text to analyze
	 * @exception NullPointerException given text can't be null
	 */
	public SmartScriptLexer(String text) {
		if(text == null) {
			throw new NullPointerException("There is no input text");
		}
		data = text.toCharArray();
		currentIndex= 0;
		state = SmartScriptLexerState.TEXT;
	}

	/**
	 * Check state of Lexer and extract first next token from given text
	 * @return extracted token from given text
	 * @exception SmartScriptLexerException if given text is incorrect or
	 * if Parser try extract and there is no token left
	 */
	public SmartScriptToken nextToken() {
		
		
		if(token != null && token.getType() == SmartScriptTokenType.EOF) {
			throw new SmartScriptLexerException("No tokens available.");
		}
		skipBlanks();
		
		if(currentIndex == data.length && state == SmartScriptLexerState.TAG) {
			throw new SmartScriptLexerException("Missing closing tag \"$}\"");
		}

		
		if(currentIndex == data.length) {
			token = new SmartScriptToken(SmartScriptTokenType.EOF, null);
			return token;
		}
		
		switch(state) {
			case TEXT :
				token = textState();
				break;
			case TAG: token = tagState();
				break;					
		}

		
		return token;

	}
	
	/**
	 * Method put all given text in one TEXT type token until Lexer state changed to TAG
	 * or there is no more text
	 * @return extracted token
	 */
		
	public SmartScriptToken textState() {
		

		StringBuilder sb = new StringBuilder();
		
		while(currentIndex < data.length && !openTag()) {

			sb.append(data[currentIndex]);
			currentIndex++;
		}

		if(sb.length() > 0) {
			token = new SmartScriptToken(SmartScriptTokenType.TEXT, sb.toString());
			 return token;
		} else {
			state = SmartScriptLexerState.TAG; 		
			currentIndex = currentIndex + 2;
			token = new SmartScriptToken(SmartScriptTokenType.TAG, "{$"); 
			return token;
		}
	}
	/**
	 * Extract next token when Lexer state is TAG. Token can be type of variable,number, 
	 * operator,function, tag,or string. Lexer is in this state while next token is
	 * between "{$" and "$}"
	 * @return extracted token
	 */
	public SmartScriptToken tagState() {
			
			if(Character.isLetter(data[currentIndex])) {
				return VariableToken();
			} else if(Character.isDigit(data[currentIndex])) {
				return DigitToken();
			} else if(isOperator(data[currentIndex])) {
				return OperatorToken();
			} else if (data[currentIndex] == '@') {
				return FunctionToken();
			} else if(data[currentIndex] == '=') {
				currentIndex++;
				return  new SmartScriptToken(SmartScriptTokenType.TAG, "=");
			} else if(closeTag()) {
				state = SmartScriptLexerState.TEXT; 		
				currentIndex = currentIndex + 2;
				return  new SmartScriptToken(SmartScriptTokenType.TAG, "$}"); 
			} else if(data[currentIndex] == '\"'){
				return StringToken();				
			} else {
				throw new SmartScriptLexerException("Can't parse given text");
			}

	}
	
	/**
	 * If data on current index is quotation mark, Lexer put 
	 * given text in string type token.
	 * @return extracted token
	 */
	public SmartScriptToken StringToken() {
		StringBuilder sb = new StringBuilder();
		currentIndex++;
		
		while( currentIndex < data.length && data[currentIndex] != '"') {
			
			if(data[currentIndex] == '\\') {
			
				escapingSequence();
				
			}
				sb.append(data[currentIndex]);
				currentIndex++;
		}
		currentIndex++;
		return new SmartScriptToken(SmartScriptTokenType.STRING, sb.toString());
		
		
	}
	
	/**
	 * If Lexer is creating sring type token, end find in given text escape sequence
	 * it can accept it as part of string only if next data character is one of character that are in 
	 * if statement in this method.
	 * @throws SmartScriptLexerException if given data character after escape sequence is not one of allowed 
	 */
	
	public void escapingSequence() {
		
		if(data[currentIndex +1] == '\\' || data[currentIndex +1] == '"' || data[currentIndex +1] == '\r' || data[currentIndex +1] == '\n'  || data[currentIndex +1] == '\t') {
			currentIndex++;
			return;
		} else {
			throw new SmartScriptLexerException("Illigal use of escaping sequence");
		}
		
		
	}
	/**
	 * Extract function name from text.
	 * @return extracted token
	 * @exception SmartScriptLexerException function name can start only with a letter
	 */
	public SmartScriptToken FunctionToken() {
		
		currentIndex++;
		StringBuilder sb = new StringBuilder();
		if(!Character.isLetter(data[currentIndex])) {
			throw new SmartScriptLexerException("Invalid function name, it must start with leter.");
		}
		while(currentIndex < data.length && (Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex] == '_')) {
			sb.append(data[currentIndex]);
			currentIndex++;	
		}

		return new SmartScriptToken(SmartScriptTokenType.FUNCTION,sb.toString());
		
	}
	
	/**
	 * Extract operator token from given text. If given character is "-" method checks
	 *  if it is part of negative number or if is operator. If it is part of negative number it is diverted to
	 *  DigitToken() function.
	 * @return extracted token
	 */
	
	public SmartScriptToken OperatorToken() {
		int savedIndex = currentIndex;
		if(data[currentIndex] == '-' && Character.isDigit(data[currentIndex + 1])) {
			currentIndex++;
			return DigitToken();
		} else {
			currentIndex++;
			return new SmartScriptToken(SmartScriptTokenType.OPERATOR, data[savedIndex]);
		}
	}
	
	/**
	 * Extract digit as token from text. If digit contains "." it is double otherwise 
	 * it is Integer.
	 * @return
	 */
	public SmartScriptToken DigitToken() {
		
		StringBuilder sb = new StringBuilder();
		
		while(currentIndex < data.length && (Character.isDigit(data[currentIndex]) || data[currentIndex] == '.')) {
			
			if(data[currentIndex - 1] == '-') {
				sb.append(data[currentIndex - 1]);
			}
			if(data[currentIndex] == '.' && !Character.isDigit(data[currentIndex + 1])) {
				break;
			}
			sb.append(data[currentIndex]);
			currentIndex++;
			
		}
		if(sb.toString().contains(".")) {
			return new SmartScriptToken(SmartScriptTokenType.CONSTANT_DOUBLE, sb.toString());
		} else {
			return new SmartScriptToken(SmartScriptTokenType.CONSTANT_INTIGER, sb.toString());
		}
		
	}
	
	/**
	 * Extract variable as token.
	 * @return extracted token
	 * @exception SmartScriptLexerException variable can start only with letter

	 */
	public SmartScriptToken VariableToken() {
		
		StringBuilder sb = new StringBuilder();
		if(!Character.isLetter(data[currentIndex])) {
			throw new SmartScriptLexerException("invalid input for variable");
		}
		while(currentIndex < data.length && (Character.isLetter(data[currentIndex]) || Character.isDigit(data[currentIndex]) || data[currentIndex] == '_')) {
			sb.append(data[currentIndex]);
			currentIndex++;	
		}

		return new SmartScriptToken(SmartScriptTokenType.VARIABLE,sb.toString());
		
	}

	/**
	 * Method checks if given data operand is part of allowed ones
	 * @param o given operator
	 * @return true if given operator is allowed otherwise false
	 */
	
	public boolean isOperator(char o) {
		if (o == '%' || o == '/' || o ==  '*' ||  o == '+' ||  o == '-' ||  o == '^'){
	        return true;
	    } else{
	        return false;
	    }
	}
	/**
	 * Method checks is Lexer get to data character that will change
	 * Lexer state to TAG
	 * @return true if Lexer state is for now TAG
	 */
	public boolean openTag() {
		if(data[currentIndex] == '{' && data[currentIndex + 1] == '$' ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if Lexer get to data character that represent 
	 * that Lexer state TAG is over
	 * @return true if it is, otherwise false
	 */
	public boolean closeTag() {
		if(data[currentIndex] == '$' && data[currentIndex + 1] == '}' ) {
			return true;
		}
		return false;
	}
	
	/**
	 * Skip all blanks spaces
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
	

}
