package hr.fer.zemris.java.hw05.db;

/**
 * Class that is used for modeling the complete conditional expression.
 * 
 * @author Petra Barać
 *
 */
public class ConditionalExpression {
	
	/**
	 * For example:
	 * ConditionalExpression expr = new ConditionalExpression(
	 * FieldValueGetters.LAST_NAME,	"Bos*",	ComparisonOperators.LIKE );
	 * 
	 * than   valueGetter is LAST_NAME
	 * 	    stringLiteral is Bos*
	 *comparisonOperator is LIKE
	 */
	private IFieldValueGetter valueGetter;
	private String stringLiteral;
	private IComparisonOperator comparisonOperator;
	
	/**
	 * Constructor that gets three arguments listed bellow
	 * @param getter a reference to IFieldValueGetter strategy
	 * @param literal a reference to string literal
	 * @param operator a reference to IComparisonOperator strategy
	 */
	public ConditionalExpression(IFieldValueGetter getter, String literal, IComparisonOperator operator) {
		this.valueGetter = getter;
		this.stringLiteral = literal;
		this.comparisonOperator = operator;
	}


	public IFieldValueGetter getValueGetter() {
		return valueGetter;
	}


	public String getStringLiteral() {
		return stringLiteral;
	}


	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}
	
	

}
