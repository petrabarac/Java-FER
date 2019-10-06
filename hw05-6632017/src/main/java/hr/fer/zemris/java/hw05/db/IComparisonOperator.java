package hr.fer.zemris.java.hw05.db;

/**
 * Strategy  with one method. Implementation of strategy
 * is written in class ComparisonOperators for each comparison operator
 * 
 * @author Petra Barać
 *
 */

public interface IComparisonOperator {
	public boolean satisfied(String value1, String value2);

}
