package com.dare.adekunle.primegen.generators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FastPrimeGenerator implements PrimeGenerator {

	public static final String NAME = "fast";
	private long durationMillis;

	@Override
	public List<Integer> getPrimes(int min, int max) {
		long start = System.currentTimeMillis();

		if (min < 1) {
			min = 1;
		}

		if (max < 1 || max < min) {
			return Collections.emptyList();
		}

		List<Integer> primes = new ArrayList<>();

		int highestFactor = (int) Math.sqrt(max);

		boolean[] nonPrimes = new boolean[max + 1];

		for (int i = 2; i <= highestFactor; i++) {
			if (!nonPrimes[i]) {
				for (int j = i * i; j <= max; j += i) {
					nonPrimes[j] = true;
				}
			}
		}

		for (int k = 2; k <= max; k++) {
			if (!nonPrimes[k]) {
				primes.add(k);
			}
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
