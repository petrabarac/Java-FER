package hr.fer.zemris.java.hw07.demo4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDemo {
	public static final String SEPARATOR = "=========";
	
	public static void main(String[] args) {
	
		List<String> lines = new ArrayList<>();
		try {
			lines = Files.readAllLines(	Paths.get("./studenti.txt"),StandardCharsets.UTF_8);
		}catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		List<StudentRecord> records = convert(lines);
		
		System.out.println("Zadatak 1");
		System.out.println(SEPARATOR);
		System.out.println(vratiBodovaViseOd25(records));
		
		System.out.println("Zadatak 2");
		System.out.println(SEPARATOR);
		System.out.println(vratiBrojOdlikasa(records));
		
		System.out.println("Zadatak 3");
		System.out.println(SEPARATOR);
		vratiListuOdlikasa(records).forEach(System.out::println);
				
		System.out.println("Zadatak 4");
		System.out.println(SEPARATOR);
		vratiSortiranuListuOdlikasa(records).forEach(System.out :: println);
		
//		System.out.println("Zadatak 5");
//		System.out.println(SEPARATOR);
//		vratiPopisNepolozenih(records).forEach(System.out ::prinln);
//		
//		
//		System.out.println("Zadatak 6");
//		System.out.println(SEPARATOR);
//		System.out.println(razvrstajStudentePoOcjenama);
//		
//		System.out.println("Zadatak 7");
//		System.out.println(SEPARATOR);
//		System.out.println(vratiBrojStudenataPoOcjenama);
//		
//		System.out.println("Zadatak 8");
//		System.out.println(SEPARATOR);
//		System.out.println(razvrstajProlazPad);

		
	}
	
	
	private static List<StudentRecord> convert(List<String> lines) {
		List<StudentRecord> records = new ArrayList<>();
				
		for(String line : lines) {
			String[] info = line.split("\t");	
			String jbag = info[0];
			String prezime = info[1];
			String ime = info[2];
			double medjuispit = Double.parseDouble(info[3]);
			double ispit = Double.parseDouble(info[4]);
			double labVjezbe = Double.parseDouble(info[5]);
			int ocjena = Integer.parseInt(info[6]);

			records.add(new StudentRecord(jbag, prezime, ime, medjuispit, ispit, labVjezbe, ocjena));
		}
		return records;
	}
	
	private static long vratiBodovaViseOd25(List<StudentRecord> records) {
		return records.stream()
				.filter(record -> record.getMiZiLabSum() > 25)
				.count();		
	}
	
	private static long vratiBrojOdlikasa(List<StudentRecord> records) {
		return records.stream()
				.filter(record -> record.getOcjena() == 5)
				.count();
	}
	
	private static List<StudentRecord> vratiListuOdlikasa(List<StudentRecord> records){
		return records.stream()
				.filter(record -> record.getOcjena() == 5)
				.collect(Collectors.toList());
	}
	
	private static List<StudentRecord>vratiSortiranuListuOdlikasa(List<StudentRecord> records){
		
		return records.stream().filter(record -> record.getOcjena() == 5)
				.sorted((rec1, rec2) -> Double.compare(rec2.getOcjena(), rec1.getOcjena()))
				.collect(Collectors.toList());
	}		
}
	
	