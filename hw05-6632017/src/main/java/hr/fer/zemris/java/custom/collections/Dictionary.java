package hr.fer.zemris.java.custom.collections;

/**
 * Parametrizirani razred Dictionary koji se ponaša kao adapter
 * oko ArrayIndexedCollection.
 * Razredu koji pod zadanim ključem pamti predanu vrijednost.
 * 
 * @author Petra Barać
 *
 * @param <K> tip ključa
 * @param <V> tip vrijednosti.
 */

public class Dictionary<K,V>  {
		
		ArrayIndexedCollection<Entry<K, V>> dictionary;

		public Dictionary() {
			dictionary = new ArrayIndexedCollection<Entry<K,V>>();
		}
		
			
		/**
		 * Privatni razred koji se omogucava pamcenje jednog zapisa(tj. uredjenog para)
		 * @author Petra Barać
		 *
		 * @param <K> t.ip ključa
		 * @param <V> tip vrijednosti.
		 */
	
		@SuppressWarnings("hiding")
		private class Entry<K, V> {
			private K key;
			private V value;
			
			
			/**
			 * Getter za kljuc
			 * @return kljuc
			 */
			public K getK() {
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
			 * Metoda za unos novog para.
			 * @param key parametar kljuc
			 * @param value parametar vrijednost
			 * @throws NullPointerException predani kljuc ne smije biit null
			 */
			public Entry(K key, V value) {
				if(key == null) {
					throw new NullPointerException("Key can't be null.");
				}
				this.key = key;
				this.value= value;				
			}
			
		}
	
	/**
	* Metoda provjerava je li dictionary prazan
    * tako što poziva metodu iz ArrayIndexedCollectiona
    * @return true ako je prazan, inace false
	*/
	public boolean isEmpty() {	
		return dictionary.isEmpty();
		
	}
	
	/**
	 * Metoda vraca broj uredjenih parova u dictionaryu
	 * @return velicinu dictionarya
	 */
	public int size() {
		return dictionary.size();
	}
	
	/**
	 * Brise sve podatke iz dictionarya
	 */
	public void clear() {
		dictionary.clear();

	}
	
	/**
	 * Metoda unosi novi par (kljuc, vrijednost) u dictionary.
	 * U slucaju da posali kljuc vec postoji u dictionary, metoda anzurira 
	 * vrijednost na danom kljucu.
	 * @param key  kljuca koji se unosi
	 * @param value vrijednost koja se unosi 
	 */
	public void put(K key, V value) {
		Entry<K, V> newEntry = new Entry<>(key, value);
		ElementsGetter<Entry<K, V>> getter = dictionary.createElementsGetter();
		
		while(getter.hasNextElement()) {
			Entry<K, V> nextElement = getter.getNextElement();
			if(nextElement.getK() == key) {
				int index = dictionary.indexOf(nextElement);
				dictionary.remove(index);
				dictionary.insert(newEntry, index);
				return;
			}
		}
		dictionary.add(newEntry);
	}
	
	/**
	 * Metoda pronalazi pohranjenu vrijednost uz predani kljuc te je vraca.
	 * Ako u dictionaryu ne postoji predani kljuc, vraca null
	 * @param key vrijednost kljuca
	 * @return vrijednost koja je pohranjena u dictionaryu uz predani kljuc
	 */
	
	public V get(Object key) {
		ElementsGetter<Entry<K, V>> getter = dictionary.createElementsGetter();
		
		while(getter.hasNextElement()) {
			Entry<K, V> nextElement = getter.getNextElement();
			if(nextElement.getK() == key) {
				return nextElement.getValue();
				
			}
		}
			return null;
		
	}


}
