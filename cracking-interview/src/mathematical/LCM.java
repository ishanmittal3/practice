package mathematical;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCM {
	
	int lcm(int a, int b) {
		Map<Integer, Integer> primeCountA = getPrimeFactors(a);
		Map<Integer, Integer> primeCountB = getPrimeFactors(b);
		int result = 1;
		for(Map.Entry<Integer, Integer> entry : primeCountA.entrySet()) {
			int prime = entry.getKey();
			int countA = entry.getValue();
			int countB = primeCountB.get(prime);
			int count = Math.max(countA, countB);
			result *= Math.pow(prime, count);
		}
		for(Map.Entry<Integer, Integer> entry : primeCountB.entrySet()) {
			int prime = entry.getKey();
			int count = entry.getValue();
			if(!primeCountA.containsKey(prime)) {
				result *= Math.pow(prime, count);
			}
		}
		return result;
	}

	private Map<Integer, Integer> getPrimeFactors(int num) {
		Map<Integer, Integer> primeFactors = new HashMap<Integer, Integer>();
		List<Integer> primes = getPrimes(num);
		for(int prime : primes) {
			if(num % prime == 0) {
				primeFactors.put(prime, getPrimeCount(num, prime));
			}
		}
		return primeFactors;
	}

	private int getPrimeCount(int num, int prime) {
		int count = 0;
		int divisor = prime;
		while(num % divisor == 0) {
			count++;
			divisor *= prime;
		}
		return count;
	}

	// Assuming this is given
	// Get primes <= num
	private List<Integer> getPrimes(int num) {
		return null;
	}
}
