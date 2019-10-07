package hr.fer.zemris.java.hw07.demo4;

public class StudentRecord {

	
	private String jbag;
	private String prezime;
	private String ime;
	private double mi;
	private double zi;
	private double lab;
	private int ocjena;
	
	public StudentRecord(String jbag, String prezime, String ime, double medjuispit, double ispit, double labVjezbe,
			int ocjena) {
		
		this.jbag = jbag;
		this.prezime = prezime;
		this.ime = ime;
		mi = medjuispit;
		zi = ispit;
		lab = labVjezbe;
		this.ocjena = ocjena;
	}
	
	@Override
	public String toString() {		
		return jbag + "\t" + prezime + "\t" + ime + "\t"  + mi + "\t"  + zi + "\t" + lab + "\t" + ocjena;
	}

	public String getJbag() {
		return jbag;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getIme() {
		return ime;
	}

	public double getMi() {
		return mi;
	}

	public double getZi() {
		return zi;
	}

	public double getLab() {
		return lab;
	}

	public int getOcjena() {
		return ocjena;
	}
	
	public double getMiZiLabSum() {
		return mi + zi + lab;
	}
	

}
