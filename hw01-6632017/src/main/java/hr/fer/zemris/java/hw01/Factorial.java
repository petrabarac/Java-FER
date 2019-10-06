package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Program provjerava je li unesen
 * cijeli broj u rasponu od 3 do 20. Ako je - izračunava faktorijelu
 * unesenog cijelog broja, 
 * ako nije - ispisuje prikladnu poruku.
 * Program se prekida kada korisnik unese "kraj".
 * 
 * @author Petra
 * @version 1.0
 */


public class Factorial {

/**
	 * Metoda rekurzivno računa faktorijelu cijelog broja .
	 * 
	 * @param argument n je cijeli broj kojemu se računa faktorijela
	 * @return n rezultat izračunate faktorijele broja
	 * @throws IllegalArgumentException ako se kao argument preda 
	 * broj za kojeg se ne može izračunati faktorijela
	 */

	public static long numberFactorial(long n) {
		if (n >20 && n < 0) {
			throw new IllegalArgumentException("Faktorijela ovog broja se ne može izračunati");
		} else if(n > 1) {
			return ( n * numberFactorial(n-1));
		  }
		return 1;
	}
/**
 *  Metoda od koje kreće izvođenje programa.
 * @param args argumenti komandne linije koji se ne koriste.
 */
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.print("Unesite broj > ");
			if(sc.hasNextInt()) {
				long number = sc.nextLong();
				if(number < 3 || number > 20) {
					System.out.format("%d nije broj u dozvoljenom rasponu%n", number);
				} else {
					System.out.format("%d! =  %d%n",number, numberFactorial(number));
				}
			  } else {
				String word = sc.next();
					if(word.equals("kraj")) {
						System.out.println("Doviđenja");
						break;
					} else {
						System.out.format("'%s' nije cijeli broj%n", word);
					}
			  }	
			}
		sc.close();
		}
		
	
	}

