package com.dare.adekunle.primegen.generators;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SlowPrimeGenerator implements PrimeGenerator {

	public static final String NAME = "slow";
	private long durationMillis;

	@Override
	public List<Integer> getPrimes(int min, int max) {
		long start = System.currentTimeMillis();

		if (max < 1 || max < min) {
			return Collections.emptyList();
		}
		
		if (min < 1) {
			min = 1;
		}		

		List<Integer> primes = IntStream.rangeClosed(Math.max(min, 2), max).boxed().collect(Collectors.toList());

		for (int i = 2; i < max; i++) {
			for (Integer k = min; k <= max; k++) {
				if (k >= i * 2 && k % i == 0) {
					primes.remove(k);
				}
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
