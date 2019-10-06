package hr.fer.zemris.java.custom.scripting.lexer;

/**
 * 
 * test for given package
 *
 */

public class Demo {
	public static void main(String[] args) {
		
		//SmartScriptLexer lexer = new SmartScriptLexer("{$= \"Joe \\\"Long\\\" \\\\ Smith\"$}") ;
		SmartScriptLexer lexer = new SmartScriptLexer("{$= i i * @sin \"0.000\" @decfmt $}");
	
		
	SmartScriptToken token = lexer.nextToken();
		System.out.println(token.getType());
		System.out.println(token.getValue());
	SmartScriptToken token2 = lexer.nextToken();
	System.out.println(token2.getType());
	System.out.println(token2.getValue());
	
	SmartScriptToken token3 = lexer.nextToken();
	
	System.out.println(token3.getType());
	System.out.println(token3.getValue());
	


	
	
	}
}
