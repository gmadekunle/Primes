package com.dare.adekunle.primegen.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModeratePrimeGenerator implements PrimeGenerator {

	public static final String NAME = "moderate";
	private long durationMillis;

	@Override
	public List<Integer> getPrimes(int min, int max) {
		long start = System.currentTimeMillis();
		
		if (max < 2 || max < min) {
			return Collections.emptyList();
		}
		
		List<Integer> primes = new ArrayList<>();
		if(min <= 2) {
			primes.add(2);
			min = 3;
		}
		
		if(min % 2 == 0) {
			min++;
		}
		
		outer: for (int i = min; i <= max; i+=2) {// Numerator loop (odd numbers)
			for (int j = 2; j <= max/2; j++) {// Denominator loop
				if (i != j && i % j == 0) {
					continue outer;
				}
			}
			primes.add(i);
		}

		long end = System.currentTimeMillis();
		durationMillis = end - start;

		return primes;
	}

	@Override
	public long getDurationMillis() {
		return durationMillis;
	}
}
