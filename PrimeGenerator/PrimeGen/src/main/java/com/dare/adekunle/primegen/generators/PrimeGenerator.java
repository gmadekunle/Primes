package com.dare.adekunle.primegen.generators;

import java.util.List;

public interface PrimeGenerator {

	public List<Integer> getPrimes(int min, int max);
	
	public long getDurationMillis();

}
