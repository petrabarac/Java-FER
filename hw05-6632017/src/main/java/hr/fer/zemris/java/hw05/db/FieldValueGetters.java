package hr.fer.zemris.java.hw05.db;

/**
 * Class that contains three concrete strategies: 
 * one for each String field of StudentRecord class 

 * @author Petra Barać
 *
 */

public class FieldValueGetters {
	
	public static final IFieldValueGetter FIRST_NAME = 
			r -> r.getFirstName();
			
	public static final IFieldValueGetter LAST_NAME = 
			r -> r.getLastName();
			
	public static final IFieldValueGetter JMBAG = 
			r -> r.getJmbag();
	
}
