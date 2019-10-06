package hr.fer.zemris.java.hw05.db;

import java.util.Objects;

/**
 * Class whose instances represent records for each student.
 * @author Petra Barać
 *
 */
public class StudentRecord {
	private String lastName;
	private String firstName;
	private String jmbag;
	private int finalGrade;
	
	/**
	 * Constructor that creates a StudentRecord with receieved infomations
	 */
	public StudentRecord(String lastName, String firstName, String jmbag, int finalGrade) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.jmbag = jmbag;
		this.finalGrade = finalGrade;
	}


	@Override
	public int hashCode() {
		return Objects.hash(jmbag);
	}

	public String getLastName() {
		return lastName;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getJmbag() {
		return jmbag;
	}


	public int getFinalGrade() {
		return finalGrade;
	}

	/**
	 * methods that treats two students as equal if their jmbags are equal
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof StudentRecord))
			return false;
		StudentRecord other = (StudentRecord) obj;
		return Objects.equals(jmbag, other.jmbag);
	}
	
	/**
	 * Help method that return information about student 
	 * according to FieldValueGetters
	 * 
	 * @param getter FieldValueGetter
	 * @return information about student
	 */

	public String getstudebtDbElement(IFieldValueGetter getter) {
		if(getter.equals(FieldValueGetters.FIRST_NAME)) {
			return firstName;
		}
		if(getter.equals(FieldValueGetters.LAST_NAME)) {
			return lastName;
		}
		
		if(getter.equals(FieldValueGetters.JMBAG)) {
			return jmbag;
		} else {
			return null;
		}
		
	}
	
}
