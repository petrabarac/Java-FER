package hr.fer.zemris.java.hw05.db;

import java.util.LinkedList;
import java.util.List;

/**
 * Class which represents a parser of query statement and it 
 * gets query string through constructor( it get everything user
 * entered after query keyword)
 * 
 * @author Petra Barać
 *
 */
public class QueryParser {
	/**
	 * User input
	 */
	 String input;
	 /**
	  * list of expressions parsed from user input
	  */
	 List<ConditionalExpression> expressions ;
	
	
	public QueryParser(String s) {
		expressions = new LinkedList<>();
		input = s.trim().replaceAll("\\s+", "");	
		
	}
	
	/**
	 * Method return true if query was of of the form jmbag="xxx"
	 * @return true if query was of of the form jmbag="xxx" 
	 * otherwise false
	 */
	public boolean isDirectQuery() {
		if(input.length() > 18) {
			return false;
		}
		
		if(input.startsWith("jmbag") && input.charAt("jmbag".length()) == '=') {
			
			String jmbag = input.substring(input.indexOf("\"") +1, input.lastIndexOf("\""));
			
			if(jmbag.length() == 10) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Method return the string  which was given in equality comparison in
	 * direct query.
	 * @return return the string  which was given in equality comparison in
	 * direct query.
	 * @exception IllegalStateException if the query was not a direct one
	 */
	public String getQueriedJMBAG() {
		if(!isDirectQuery()) {
			throw new IllegalStateException("Given query wasn't direc one.");
		}
		
		return  input.substring(input.indexOf("\"") +1, input.lastIndexOf("\""));
		
	}
	
	/**
	 * For all queries, method return a list of conditional expressions from query; 
	 * @return a list of conditional expressions from query
	 */
	public List<ConditionalExpression> getQuery() {
		return expressions;
	}
	
	/**
	 * Method that parse user input.
	 * Method goes throw whole input which is there after word "query" and check used getter, operator and string literal.
	 * If input query is valid, method put query in expressions list.
	 * @exception IllegalArgumentException if illegal argument is used in query.
	 */
	public void parse() {
		IComparisonOperator comparisonOperator = null;
		IFieldValueGetter valueGetter;
		String stringLitteral = null;
		
		String operator;
		
		
		
		String[] queries = input.split("AND");
		 
		for(String query : queries) {
			

			stringLitteral = query.substring(query.indexOf("\"") + 1, query.lastIndexOf("\""));
			



			if(query.startsWith("jmbag")) {				
				valueGetter = FieldValueGetters.JMBAG;
				operator = query.substring("jmbag".length(),query.indexOf("\""));
				try {
				comparisonOperator = checkComparisonOperator(operator); 
				} catch(IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				  }		
				
			} else if(query.startsWith("firstName")) {
				valueGetter = FieldValueGetters.FIRST_NAME;
				operator = query.substring("firstName".length(), query.indexOf("\""));
				try {
				comparisonOperator = checkComparisonOperator(operator); 
				} catch(IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				}
				
			} else if(query.startsWith("lastName")) {
				valueGetter = FieldValueGetters.LAST_NAME;
				operator = query.substring("lastName".length(), query.indexOf("\""));
				try {
				comparisonOperator = checkComparisonOperator(operator); 
				} catch(IllegalArgumentException ex) {
					System.out.println(ex.getMessage());
				}
				
			} else {
				throw new IllegalArgumentException("Invalid query input. You can get only firsName, lastName or jmbag.");
			  }

			expressions.add(new ConditionalExpression(valueGetter, stringLitteral, comparisonOperator));
		}		
	}
	
	/***
	 * Help method that check comparison operator that user used. If user used valid one
	 * method return IComparisonOperator for used operator
	 * @param operator string operator that user used
	 * @return IComparisonOperator
	 * @exception IllegalArgumentException if user used unsupported operator
	 */
	
	private IComparisonOperator checkComparisonOperator(String operator) {
		switch (operator) {
		case "<":
			return ComparisonOperators.LESS;
			
		case ">" :
			return ComparisonOperators.GREATER;
			
		case ">=" :
			return ComparisonOperators.GREATER_OR_EQUALS;
			
		case "<=" :
			return ComparisonOperators.LESS_OR_EQUALS;
			
		case "=" :
			return ComparisonOperators.EQUALS;
			
		case"!=" :
			return ComparisonOperators.NOT_EQUALS;
			
		case "LIKE" :
			return ComparisonOperators.LIKE;
		
		default :
			throw new IllegalArgumentException("You used an unsupported operator.");
		
		}
		
	}
}
