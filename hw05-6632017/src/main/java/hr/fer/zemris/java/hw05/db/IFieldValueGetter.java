package hr.fer.zemris.java.hw05.db;

/**
 * Class represents strategy which is responsible for obtaining 
 * a requested field value from given StudentRecord
 * 
 * @author Petra Barać
 *
 */

public interface IFieldValueGetter {
	public String get(StudentRecord record);
}
