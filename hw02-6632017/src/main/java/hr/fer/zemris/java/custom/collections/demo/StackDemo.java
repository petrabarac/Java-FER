package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Command-line application which accepts a single command-line argument.
 * Expression must be a postfix representation
 * @author Petra Barać
 *
 */

public class StackDemo {
	public static void main(String[] args) {
		
		ObjectStack stack = new ObjectStack();
		String[] splited = args[0].split("\\s+");
		
		for (String string : splited) {
			if(isNumber(string)) {
				stack.push(Integer.parseInt(string));
			} else {
				try {
					int number2 = (int)stack.pop();
					int number1 = (int)stack.pop();
					try {
						int resoult = arithmetic(number2, number1, string);
						stack.push(resoult);
					} catch(ArithmeticException ex) {
						System.out.println(ex.getMessage());
						System.exit(1);
					} catch(IllegalArgumentException ex) {
						System.out.println(ex.getMessage());
						System.exit(1);			
					}
					
				} catch (EmptyStackException ex) {
					System.out.println(ex.getMessage());
					System.exit(1);	
					}
			}		
		}
		if(stack.size() == 1) {
		System.out.println(stack.peek());
		} else {
			System.out.println("Input postfix is not correct");
		}
	}
	
	/**
	 * Calculate operations between two given values and returns resoult
	 * @param number2 second value in operation
	 * @param number1 first value in operation
	 * @param string string that  determines which operation should be calculated
	 * @return result of operation
	 * @throws IllegalArgumentException if character does not represent any operation
	 * @throws  ArithmeticException if dividing with zero
	 */
	
	private static int arithmetic(int number2, int number1, String string) {
		
		switch (string) {
			case "+": 
				return (number1 + number2);		
			case "-":
				return (number1 - number2);		
			case "*":
				return (number1 * number2);
			case "/":
				if(number2 == 0) {
					throw new ArithmeticException("Fale, you can't divide with zero");
				}else {
					return (number1/number2);
				}
			case "%":
				if(number2 == 0) {
					throw new ArithmeticException("Fale, you can't divide with zero");
				}
				return (number1%number2);
			default:
				throw new IllegalArgumentException("Input postfix is not correct");
		}
		
	}
	
	/**
	 * Return true if given string can be parsed to integer
	 * @param string string that will be parsed to integer
	 * @return true if string is parsed to integer, else false.
	 */

	private static boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
		
	}
}
	
	
	
	
	
