package hr.fer.zemris.java.hw05.db;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * When started, program reads the data from current directory from file database.txt .
 * If in provided file there are duplicate jmbags, or if finalGrade is not a 
 * number between 1 and 5, program would terminate with appropriate message to user.
 *  
 * @author Petra Barać
 *
 */


public class StudentDB {
	
	private static Scanner sc;
	private static StudentDatabase studentDatabaBase;
	private static String input;
	private static QueryParser parser;
	private static List<StudentRecord> selected;
	
	private static int nameLength = 0;
	private static int lastNameLength = 0;
	private static int jmbagLength = 0;
	private static int gradeLength = 0;
	
	public static void main(String[] args) {
			List<String> lines = null;
			sc = new Scanner(System.in);
			
			input = " ";
			
			try {
				lines = Files.readAllLines(
				Paths.get("./database.txt"),
				StandardCharsets.UTF_8
				);
			}catch(IOException ex) {
				System.out.println(ex.getMessage());
			}

			
			try {
				studentDatabaBase = new StudentDatabase(lines);
			}catch (IllegalArgumentException ex) {
				System.out.println(ex.getMessage());
			}
			
			while (!input.toLowerCase().equals("exit")) {
				System.out.print("> ");
				input = sc.nextLine();
				
				if(input.equals("exit")) {
					break;
				}
				
				if(!input.startsWith("query")) {
					System.out.println("Query must start with word \"query\"");
					continue;
				} else {
					input = input.substring("query".length());
				}
				
				try {
					selected = new LinkedList<>();
					 parser = new QueryParser(input);
					 parser.parse();
				} catch (IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
					continue;
				}
				
				if(parser.isDirectQuery()) {
					executeDirectQuery();
				} else {
		
					executeQuery();
				}	
				
			}
			System.out.println("Goodbye!");
			sc.close();

			
		}
	
	private static void executeDirectQuery() {
		
		
		selected.add(studentDatabaBase.forJMBAG(parser.getQueriedJMBAG()));
		
		printSelected(selected);
		
	
	}

	private static void executeQuery() {	
		selected = studentDatabaBase.filter(new QueryFilter(parser.expressions));
		printSelected(selected);
	}
	
	private static void printSelected(List<StudentRecord> printRecords) {

		createForm(printRecords);
		printForm(printRecords);
		

		
		for(StudentRecord record : printRecords) {
			StringBuilder sb = new StringBuilder();
			sb.append("| " + record.getJmbag() + " | " + record.getLastName());
			int i = record.getLastName().length();

			while(i < lastNameLength) {
				sb.append(" ");
				i++;				
			}
			sb.append(" | " + record.getFirstName());
			
			int j = record.getFirstName().length();
			while(j< nameLength) {
				sb.append(" ");
				j++;				
			}
			sb.append(" | " + record.getFinalGrade() + " |");
			System.out.println(sb.toString());
		}
		printForm(printRecords);
		System.out.println("Records selected: " + selected.size());
	}
	
	
	private static void printForm(List<StudentRecord> printRecords) {
		if(!printRecords.isEmpty()) {
			
			int sizeOfJmbag = jmbagLength + 2;
			int sizeOfname = nameLength + 2;
			int sizeOfLastName = lastNameLength + 2;
			int sizeOfGrade = gradeLength + 2;
			
			System.out.print("+");
			while(sizeOfJmbag > 0) {
				System.out.print("=");
				sizeOfJmbag--;
			}
			 
			System.out.print("+");
			while(sizeOfLastName > 0) {
				System.out.print("=");
				sizeOfLastName--;
			}
			
			System.out.print("+");
			while(sizeOfname > 0) {
				System.out.print("=");
				sizeOfname--;
			}
			
			System.out.print("+");
			while(sizeOfGrade > 0) {
				System.out.print("=");
				sizeOfGrade--;
			}
			
			System.out.print("+");
			System.out.println();
		}
				
	}
	
	private static void createForm(List<StudentRecord> printRecords) {
		if(!printRecords.isEmpty()) {
			StudentRecord first = printRecords.get(0);
		
		
			nameLength = first.getFirstName().length();
			lastNameLength = first.getLastName().length();
			jmbagLength = first.getJmbag().length();
			gradeLength = 1;
		
			for(StudentRecord record : printRecords) {
				if(record.getFirstName().length() > nameLength) {
					nameLength = record.getFirstName().length();
				}
			
				if(record.getLastName().length() > lastNameLength) {
					lastNameLength = record.getLastName().length();
				}
			
			}
		}
	}
	
	
}



	



