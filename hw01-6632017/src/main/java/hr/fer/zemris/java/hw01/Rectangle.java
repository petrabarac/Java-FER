package hr.fer.zemris.java.hw01;

import java.util.Scanner;

/**
 * Program preko unesene širine i visine
 * računa površinu i opseg pravokutnika. 
 * 
 * @author Petra
 * @version 1.0
 */


public class Rectangle {

/**
 *  Metoda izračunava površinu i opseg pravokutnika.
 *  
 *  površinaPravokutnika = visina + širina
 *  opsegPravokutnika = 2 + (visina + širina)
 *  
 * @param w argument koji sadži širinu
 * @param h argument koji sadrži visinu
 */
	
	public static void Calculate(String w, String h) {
		
		double width=Double.parseDouble(w);		
		double height=Double.parseDouble(h);
		
		double area = width * height;
		double circumfence = 2*(width + height);
		
		System.out.print("Pravokutnik širine " + width + " i visine " + height + " ima površinu " + area + " te opseg " + circumfence + ".");
		System.exit(1);
		
	}
 /**
  * Metoda provjerava je li unesen valjan argument visine i širine.
  * Argument ne smije biti negativan broj niti string.
  * 
  * @param s argument nad kojim se vrši provjera
  * @return povratna vrijednost daje informciju je li provjereni argument ispravan
  */
	
	public static boolean provjera(String s) {
		int flag=1;
		if(flag==1) {
			try {
				int intValue = Integer.parseInt(s);
				if(intValue < 0) {
					System.out.format("Unijeli ste negativnu vrijednost%n");
					return false;
				}flag=0;
				} catch (NumberFormatException e) {
					flag=1;
				  } 
			try {
				double doubleValue = Double.parseDouble(s);
				flag=0;
			} catch (NumberFormatException e) {
				flag=1;
			} 
		}
		
		if(flag==0) {
			return true;
		} else {
			System.out.format(" '%s' se ne može protumačiti kao broj%n", s);
			return false;
		}	
}
/**
 * Metoda od koje kreće izvođenje programa.
 * @param args argumenti komandne linije koji se mogu i nemoraju koristiti.
 */

	public static void main( String ... args) {
		
		switch(args.length) {
			case 0: {
				Scanner sc = new Scanner(System.in);
				while(true) {
					System.out.print("Unesi širinu > ");
					String width = sc.next();
					if(provjera(width)) {
						while(true) {
							System.out.print("Unesi visinu > ");
							String height = sc.next();
							if(provjera(height)) {
								Calculate(width, height);
							}
						}
					}
			
				}
			} 
			
			case 2: {
				String height2 = args[0];
				String width2 = args[1];
				if(provjera(height2)) {
					if(provjera(width2)) {
						Calculate(height2,width2);
						
					}
					System.exit(1);
				}
				System.exit(1);
			}
				
			
			default: {
				System.out.println("Unjeli ste neispravan broj argumenata");
			}
		}
	}
}
