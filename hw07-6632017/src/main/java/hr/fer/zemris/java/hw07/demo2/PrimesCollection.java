package hr.fer.zemris.java.hw07.demo2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that stores prive numbers. Nextt prime is calculated only when needed.
 * @author Petra Barać
 *
 */
public class PrimesCollection implements Iterable<Integer> {
	
	/**
	 * A number of consecutive primes that must be in this collection.
	 */
	private static  int numOfPrimes;


	public PrimesCollection(int n) {
		numOfPrimes = n;
	}
	
	/**
	 * "Factory" of iterators. Every iterator is a new next prime number.
	 */
	@Override
	public Iterator<Integer> iterator() {
	
		return new PrimeNumberIterator(numOfPrimes);
	}
	
	/**
	 * 
	 * @param br current number
	 * @return next number that is a prime number
	 */
	
	public static Integer CalculateNextPrime(int br) {
		while(true) {
			if(isPrime(br)) {
				break;
			}
			br++;
		}
		return br;
	}
	
	/**
	 * Check if given number is a prime number
	 * @param br number
	 * @return true if br is prime number, otherwise false
	 */
	
	private static boolean isPrime(int br) {
		for(int i = 2; i < br / 2 + 1; i++) {
			if(br % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Implementation of iterator.
	 * 
	 * @author Petra Barać
	 *
	 */
	
	private static class PrimeNumberIterator implements Iterator<Integer> {
		
		private int n;
		int currentPrime;
		int returnPrime;
		

		public PrimeNumberIterator(int n){
			super();
			this.n = n;
			currentPrime = 2;
			
		}
		/**
		 * Check if there is need for calculating next prime number
		 */
		
		@Override
		public boolean hasNext() {
			return n > 0;
		}
		
		/**
		 * Calculate next prime number
		 * @return prime number
		 */
		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException("There are no more prime numbers");	
			} 
			n--;
			currentPrime= CalculateNextPrime(currentPrime);
			returnPrime = currentPrime;
			currentPrime++;
			return CalculateNextPrime(returnPrime);
	
		}		
	}

}
