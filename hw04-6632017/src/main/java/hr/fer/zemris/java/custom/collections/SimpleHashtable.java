package hr.fer.zemris.java.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Razred predstavlja tablicu raspršenog 
 * adresiranja koja omogućava pohranu uređenih parova (ključ, vrijednost).
 * 
 * @author Petra Barać
 *
 * @param <K> tip kljuca
 * @param <V> tip vrijednosti
 */
public class SimpleHashtable<K, V> implements Iterable<SimpleHashtable.TableEntry<K,V>>{
	
	/**
	 * Defoltna velicina slotova u tablici
	 */
	private static final int SLOT_SIZE = 16;
	/**
	 * Kontrolna varijabla za iskorištenost slotova u tablici
	 */
	private static final double LIMIT_USAGE = 0.75;
	/**
	 * Lokalna pohrana tablice
	 */
	private TableEntry<K,V>[] table;
	/**
	 * Broj uredjenih parova u tablici
	 */
	private int size;
	/**
	 * Trenutna kolicina slotova u tablici
	 */
	private int numberOfSlots;
	/**
	 * Poveca se svaki put kada je u novi slot stavljen uredjeni par
	 */
	private int usedSlots;
	
	/**
	 *Varijabla koja se poveća se svaki put kada dodje do modifikacije u tablici
	 */
	private int modificationCount;
	
	/**
	 * Razred modelira jedan slot tablice
	 * 
	 * @author Petra Barać
	 *
	 * @param <K> tip kljuca
	 * @param <V> tip vrijednosti
	 */
	public static class TableEntry<K,V> {
		/**
		 *Varijabla pamti predani kljuc
		 */
		private K key;
		/**
		 *Varijabla pamti predanu vrijednost
		 */
		private V value;
		/**
		 * Varijabla pokazuje pokazuje na sljedeći primjerak razreda TableEntry<K,V> 
		 * koji se nalazi u istom slotu tablice
		 */
		private TableEntry<K, V> next;
		
		
		/**
		 * Konstruktor koji postavlja vrijedsnoti kljuca,vrijednosti i sljedeceg primjerka razreda 
		 * na dobivene vrijednosti.
		 * @param key predana vrijednost kljuca
		 * @param value vredana vrijednost
		 * @param next sljedeci primjerak razreda
		 */
		public TableEntry(K key, V value, TableEntry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		/**
		 * Getter za kljuc
		 * @return kljuc
		 */
		public K getKey() {
			return key;
		}
		
		/**
		 * Getter za vrijednost
		 * @return vrijednost
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * Setter za vrijednost
		 * @param value vrijrdnost koju zelimo postaviti
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		/**
		 * 
		 * @return vraca string vrijednost uredjenog para
		 */
		public String toString() {
			return(key + "=" + value);
		}
	}
	/**
	 * Defoltni konstruktor koji stvara tablicu  velicine 
	 * 16 slotova
	 */
	public SimpleHashtable() {
		this(SLOT_SIZE);		
	}
	
	/**
	 *Konstruktor koji prima željeni početni kapacitet tablice i 
	 *stvara tablicu veličine koja je potencija broja 2 koja je prva veća ili jednaka predanom broju.
	 *
	 * @param capacity broj koji predstavlja željeni početni kapacitet tablice
	 * @exception IllegalArgumentException ako je predani kapacitet manji od 1
	 */
	public SimpleHashtable(int capacity) {
		if(capacity<1) {
			throw new IllegalArgumentException("capacity need to be biger than one.");
		}
		int i = 0;
		while(numberOfSlots < capacity) {
			numberOfSlots = (int)Math.pow(2, i);
			i++;
		}	
		table =	new TableEntry[numberOfSlots];
	}
	
	/**
	 * Metoda racuna slot za uredjeni par. Potrebno kako
	 * bi se znalo u koji slot ce se pohranit uredjeni par
	 * 
	 * @param key kljuc para ciji slot treba izracunat
	 * @return izracunata vrijednost slota
	 */
	private int getSlot(Object key) {
		return Math.abs(key.hashCode())% numberOfSlots;
	}
	
	/**
	 * Metoda provjerava je li ova tablica sadrzi poslani kljuc
	 * @param key vrijednost kljuca
	 * @return true ako sadrzi, inace false
	 */
	public boolean containsKey(Object key) {
		
		return getTableEntry(key) != null;
	}
	
	/**
	 * Metoda za dani kljuc provjerava je li postoji uredjeni par u tablici.
	 * Ako postoji onda ga vrati.
	 * @param key kljuc para
	 * @return uredjeni par ako postoji, inace null
	 */
	private TableEntry<K, V> getTableEntry(Object key) {
		int slot = getSlot(key);
		TableEntry<K, V> current = table[slot];
		
		while(current != null) {
			if(current.getKey().equals(key)) {
				return current;
			}
			current = current.next;
		}
		return null;
	
	}
	
	/**
	 * Getter za varijablu size
	 * @return
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Metoda provjerava je tablica sadrzi ijedan uredjeni par
	 * @return true ako tablica nije prazna, inace false
	 */
	public boolean isEmpty() {		
		return size == 0;		
	}
	
	/**
	 * Unosi novi entry u tablicu. Novi par se uvijek unosi
	 * na kraj.
	 * @param key vrijednost kljuca
	 * @param value predana vrijednost
	 * @exception NullPointerException dani kljuc ne smije biti null.
	 */
	public void put(K key, V value) {
		if(key == null) {
			throw new NullPointerException("Key can not be null.");
		}
		
		TableEntry<K, V> checkValue = getTableEntry(key);
		
		if(checkValue != null) {
			checkValue.setValue(value);
			return;
		}
		int newEntrySlot = getSlot(key);

		
		TableEntry<K, V> current = table[newEntrySlot];
		
		if(current == null) {
			usedSlots++;
			table[newEntrySlot] = new TableEntry<K, V>(key, value, null);
			size++;
			checkUsage();
			modificationCount++;
			return;
		}
		while(current.next != null) {
			current = current.next;
		}
		
		current.next= new TableEntry<K, V>(key, value, null);
		size++;
		modificationCount++;
		checkUsage();
		
	}
	
	/**
	 * Provjera iskoristenosti slotova. Ako vise od limitUsage slotova imaju jedan ili vise
	 * uredjenih parova, onda se radi realokacija, povecavanjem broja slotova.
	 */
	public void checkUsage() {
		if(usedSlots / (numberOfSlots * 1.0) >= LIMIT_USAGE) {
			reallocate();
		}
	}
	
	/**
	 * Metoda realociran nove slotove, te sadrzaj tablice ispocetka 
	 * svrstaje po slotovima.
	 */
	public void reallocate() {
		TableEntry<K, V>[] savedData = new TableEntry[size];
		
		int j = 0;
		for(int i = 0; i < numberOfSlots; i++) {
			TableEntry<K, V> current = table[i];
			
			while(current != null) {
				savedData[j] = current;
				current = current.next;
				j++;
			}
		}
		clear();
		
		numberOfSlots = numberOfSlots * 2;		
		table = new TableEntry[numberOfSlots];
		
		for(int i = 0; i < savedData.length; i++) {
			put(savedData[i].getKey(), savedData[i].getValue());
		}
	}
	
	/**
	 * Brise sve uredjene parove iz tablice.
	 */
	public void clear() {
		
		for(int i = 0; i < numberOfSlots; i++) {
			table[i] = null;
		}
		size = 0;
		usedSlots = 0;
		
	}
	/**
	 * Metoda vraca pohranjenu vrijednost koja se nalazi uz predani kljuc
	 * @param key vrijednost kljuca
	 * @return vrijednost koja je pohranjena za predanom kljucu, ili
	 * null ako kljuc ne postoji u tablici.
	 */
	public V get(Object key) {
		TableEntry<K, V> entry = getTableEntry(key);
		
		if(entry != null) {
			return entry.getValue();
		} 
		return null;
	}
	/**
	 * Provjerava je li se dana vrijednost nalazi u tablici
	 * @param value vrijednost 
	 * @return true ako je pronadjena predana vrijednost, inace false
	 */
	public boolean containsValue( Object value) {

		for(int i = 0; i < numberOfSlots; i++) {
			TableEntry<K, V> current = table[i];
			while(current != null) {
				if(current.getValue() == null) {
					if(value == null)  {
						return true;
					}					
				} else 	if(current.getValue().equals(value)) {
					return true;
				}
				current = current.next;
			}
		}
		return false;
	}
	/**
	 * Metoda za danu vrijednost kljuca brise uredjeni par
	 * @param key vrijednost kljuca
	 */
	public void remove(Object key) {
		if(key != null) {
			int slotNumber = getSlot(key);
			TableEntry<K, V> current = table[slotNumber];
			if(current.getKey().equals(key)) { //prvi je 
				if(current.next == null) { //prvi i jedini
					table[slotNumber] = null;
				
				} else { //prvi i nije jedini
					table[slotNumber] = current.next;
				}
				size--;
				modificationCount++;
				return;
			}
			
			while(current.next != null) {
				if(current.next.getKey().equals(key) ) {
					current.next = current.next.next;
					size--;
					modificationCount++;
					return;
				}
				current = current.next;				
			}
			
		}

	
	}
	/**
	 * Metoda generira popis uređenih parova koji su pohranjeni u kolekciji
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		

		for(int i = 0; i < numberOfSlots; i++) {
			TableEntry<K, V> current = table[i];
			
			while(current.next != null) {
				sb.append(current.toString() + ", ");
				current = current.next;
				
			}
			
		}
		
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString();		
	}
	
	/**
	 * 
	 */
	public Iterator<SimpleHashtable.TableEntry<K,V>> iterator() { 
		return new iteratorImpl();
	}
	
	private class iteratorImpl implements Iterator<SimpleHashtable.TableEntry<K,V>> {
		
		private int counter;
		private int slotNumber;
		private TableEntry<K, V> last;
		
		private TableEntry<K, V> current;
		private boolean removeIsAllowed;
		
		private int savedModificationCount;


		
		public iteratorImpl() {		
			while(table[slotNumber] == null) {
				slotNumber++;
			}
			current = table[slotNumber];
			removeIsAllowed = false;
			savedModificationCount = modificationCount;
		}
		
		private boolean checkModCount() {
			return modificationCount == savedModificationCount;
		}
		
		public boolean hasNext() {		
			if(!checkModCount()) {
				System.out.println(savedModificationCount + " " + modificationCount);
				throw new ConcurrentModificationException();
			}
			return counter < size;
		}
		
		public SimpleHashtable.TableEntry<K, V> next() {
			if(hasNext()) {		
				TableEntry<K, V> nextElement = current;
				counter++;
				if(counter == size) {
					removeIsAllowed = true;
					last = nextElement;
					return nextElement;					
				}
				
				if(current.next != null) {
					current = current.next;
				} else {
					slotNumber++;
					while(table[slotNumber] == null) {
						slotNumber++;
					}
					current = table[slotNumber];
				}
				
				removeIsAllowed = true;
				last = nextElement;
				return nextElement;
			}
			throw new NoSuchElementException("No more elements.");
		}
		
		public void remove() {
			if(!checkModCount()) {
				throw new ConcurrentModificationException();
			}			
			if(removeIsAllowed) {
				SimpleHashtable.this.remove(last.getKey());
				counter--;
				savedModificationCount++;
				removeIsAllowed = false;
				return;
			}

			throw new IllegalStateException("It is not allowd to remove two elements in a row.");
		}
		
		
	}
	

}
