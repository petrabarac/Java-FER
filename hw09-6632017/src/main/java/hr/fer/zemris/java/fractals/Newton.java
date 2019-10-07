package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.java.fractals.viewer.FractalViewer;
import hr.fer.zemris.math.*;

public class Newton {
	
	public static void main(String[] args) {
		System.out.println("Welcome to Newton-Raphson iteration-based fractal viewer.\n" + 
				"Please enter at least two roots, one root per line. Enter 'done' when done.");
		
		Scanner sc = new Scanner(System.in);
		int i = 1;
		List<Complex> roots = new ArrayList<Complex>();
		
		while(true) {
			System.out.print("Root " + i + "> ");
			String input = sc.nextLine();
			
			try {
				roots.add(parseInput(input));
			} catch (NumberFormatException | NullPointerException  e){
				System.out.println(e.getMessage());
			  };
			  
			  if(input.equals("done")) {
				  break;
			  }
			  i++;
		}
			
		System.out.println("Image of fractal will appear shortly. Thank you.");
		sc.close();
		
		final Complex constant = Complex.ONE;
		
		ComplexRootedPolynomial rootedPolynomial = new ComplexRootedPolynomial(constant, roots.toArray(new Complex[roots.size()]));
          FractalViewer.show(new FractalProducer(rootedPolynomial));
	}
	
	private static Complex parseInput(String s) {
		
		if(!s.contains("i")) {
			return new Complex(Double.parseDouble(s), 0);
		}
		
		String[] args = s.split(" ");
		
		if(args.length == 1) {
			args[0] = args[0].replace("i", "");
			if(args[0].equals("") || args[0].equals("-")) {
				args[0]=args[0] + "1";
			}
			return new Complex(0, Double.parseDouble(args[0]));
		}
		if(args.length == 2) {
			args[1]=args[1].replace("i", "");
			if(args[1].equals("")) {
				args[1]="1";
			}
			args[1]= "-" + args[1];
			return new Complex(0, Double.parseDouble(args[1]));
		}
		if(args.length == 3) {
			
			if(args[1].equals("-")) {
				args[2] = "-" + args[2];
			}
			args[2] = args[2].replace("i", "");
			if(args[2].equals("") || args[2].equals("-")) {
				args[2]=args[2] + "1";
			}
			
			return new Complex(Double.parseDouble(args[0]), Double.parseDouble(args[2]));
		}
		throw new NumberFormatException("Too much arguments for root");
	}
	
}
