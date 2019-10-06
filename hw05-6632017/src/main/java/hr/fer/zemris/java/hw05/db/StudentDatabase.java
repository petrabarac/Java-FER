package hr.fer.zemris.java.hw05.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that creates an internal list of student records.
 *
 * @author Petra Barać
 *
 */

public class StudentDatabase {

	/**
	 * List of student records
	 */
	private List<StudentRecord> records;
	/**
	 * An index-jmbag map for fast retrieval of student records when jmbag is known
	 */
	private Map<String, Integer> map;
	
	int i = 0;
	
	/**
	 *Constructor gets a list of String objects (the content of 
     *database.txt , each string represents one row of the database file)
     *and create an internal list of students.
	 *
	 * @param lines list of lines in database.txt
	 * @exception IllegalArgumentException if given grade is out of range
	 * or there are two equal jmbags
	 */

	public StudentDatabase(List<String> lines) {
		 records = new ArrayList<>();
		 map = new HashMap<>();

			for(String line : lines) {
			
				String[] oneRecord = line.split("\t");
				
				String jmbag = oneRecord[0];
				String lastName = oneRecord[1];
				String firstName = oneRecord[2];


				
				try {
					int finalGrade = Integer.parseInt(oneRecord[3]);
					
					if(finalGrade < 1 || finalGrade > 5) {
						throw new IllegalArgumentException("Given grade is out of interval.");
					}
					
					
					if(map.get(jmbag) == null) {
						records.add(new StudentRecord(lastName, firstName, jmbag, finalGrade));	
						map.put(jmbag, Integer.valueOf(i++));
					} else {
						throw new IllegalArgumentException("there are duplicated jmbags");
					}

				} catch (NumberFormatException ex) {
					System.out.println(ex.getMessage());
				}
	
			}

		}
	
	/**
	 * Method uses index to obtain requested record; if record does not exists, the method returns null
	 * @param jmbag jmbag of requested record
	 * @return null if record does not exist or requested record
	 */
	public StudentRecord forJMBAG(String jmbag) {
		
		if(map.get(jmbag) == null) {
			return null;
		}

		return records.get(map.get(jmbag));
		
		
	}
	
	/**
	 * The method filter in StudentDatabase loops through all student records in its internal list; it calls 
	 * accepts method on given filter-object with current record; each record for which accepts returns true is 
	 * added to temporary list and this list is then returned by the filter method.
	 * @param filter filter
	 * @return list of records for which accepts returns true
	 */
	
	public List<StudentRecord> filter(IFilter filter) {
		
		List<StudentRecord> temporary = new ArrayList<>();
		for(StudentRecord record : records) {
			if(filter.accepts(record)) {
				temporary.add(record);
			}
		}
		
		return temporary;
	}
	

	
}
