package hr.fer.zemris.java.hw05.db;

import java.util.List;

/**
 * The class that is used to perform validation checks over elements of this
 * database.
 * 
 * @author Petra Barać
 *
 */

public class QueryFilter implements IFilter{
	
	/**
	 * A list of expressions which were obtained by parsing the input text 
	 */
	
	List<ConditionalExpression> expressions;

	/**
	 * A single public constructor 
	 * @param expressions list of ConditionalExpression objects
	 */
	public QueryFilter(List<ConditionalExpression> expressions) {
		this.expressions = expressions;
	}
	
	/**
	 * Method that checks if given query is true by validating conditional expression
	 * that was obtained from that query
	 * @return true if it is, otherwise false
	 */
	public boolean accepts(StudentRecord record ) {
		
		for(ConditionalExpression expression : expressions) {
			if(!(expression.getComparisonOperator()
					.satisfied(record.getstudebtDbElement(expression.getValueGetter()),expression.getStringLiteral())))
				{
				return false;

				}
		}

		return true;
	}
	
}
