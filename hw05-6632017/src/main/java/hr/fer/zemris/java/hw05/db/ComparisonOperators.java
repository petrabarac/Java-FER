package hr.fer.zemris.java.hw05.db;

/**
 * Class that implement concrete strategies 
 * for each comparison operator
 * 
 * @author Petra Baraać
 *
 */
public class ComparisonOperators {
	
	public static final IComparisonOperator LESS = 
			(v1, v2) -> v1.compareTo(v2) < 0 ;
			
	public static final IComparisonOperator LESS_OR_EQUALS = 
			(v1, v2) -> v1.compareTo(v2) <= 0 ;
	public static final IComparisonOperator GREATER = 
			(v1, v2) -> v1.compareTo(v2) > 0 ;

	public static final IComparisonOperator GREATER_OR_EQUALS = 
			(v1, v2) -> v1.compareTo(v2) >= 0 ;

	public static final IComparisonOperator EQUALS = 
			(v1, v2) -> v1.compareTo(v2) == 0 ;
			
	public static final IComparisonOperator NOT_EQUALS = 
			(v1, v2) -> v1.compareTo(v2) != 0 ;

	public static final IComparisonOperator LIKE = new IComparisonOperator() {
		
		@Override
		public boolean satisfied(String value1, String value2) {
			if(value2.contains("*")) {
				
				
				
				if(value2.indexOf("*") != value2.lastIndexOf("*")) {
					throw new IllegalArgumentException("character '*' can appear only once.");
				}
				
				if(value2.startsWith("*")) {
					String subString = value2.substring(1);
					return value1.endsWith(subString);
				} else if(value2.endsWith("*")) {
					 String  subString = value2.substring(0, value2.length() -1);
					 return value1.startsWith(subString);
				} else {
					int indexWildcard = value2.indexOf("*");
					
					return value1.startsWith(value2.substring(0,indexWildcard)) && value1.substring(indexWildcard).endsWith(value2.substring(indexWildcard+1));	
				}
				
				
			}
			return value1.equals(value2);
		}
			
			
	};
}
