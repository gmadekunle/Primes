/**
 * 
 */
package com.dare.adekunle.primegen.generators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PrimeGeneratorTest {

	@ParameterizedTest
	@MethodSource("paramStream")
	public void testFastGeneratorGetPrimes(Integer[][] params) {
		FastPrimeGenerator fastPrimeGenerator = new FastPrimeGenerator();
		Integer[] args = params[0];
		List<Integer> expectedResult = Arrays.asList(params[1]);
		List<Integer> actualResult = fastPrimeGenerator.getPrimes(args[0], args[1]);
		assertEquals(expectedResult, actualResult);
	}

	@ParameterizedTest
	@MethodSource("paramStream")
	public void testModerateGeneratorGetPrimes(Integer[][] params) {
		ModeratePrimeGenerator moderatePrimeGenerator = new ModeratePrimeGenerator();
		Integer[] args = params[0];
		List<Integer> expectedResult = Arrays.asList(params[1]);
		List<Integer> actualResult = moderatePrimeGenerator.getPrimes(args[0], args[1]);
		assertEquals(expectedResult, actualResult);
	}

	@ParameterizedTest
	@MethodSource("paramStream")
	public void testSlowGeneratorGetPrimes(Integer[][] params) {
		SlowPrimeGenerator slowPrimeGenerator = new SlowPrimeGenerator();
		Integer[] args = params[0];
		List<Integer> expectedResult = Arrays.asList(params[1]);
		List<Integer> actualResult = slowPrimeGenerator.getPrimes(args[0], args[1]);
		assertEquals(expectedResult, actualResult);
	}

	public static Stream<Arguments> paramStream() {
		return Stream.of(Arguments.arguments((Object) new Integer[][]{ { -4, 1 }, {} }), 			/* min and max below 2, no primes */
				Arguments.arguments((Object) new Integer[][]{ { 50, 10 }, {} }), 					/* max > min, no primes */
				Arguments.arguments((Object)new Integer[][]{ { 0, 2 }, { 2 } }), 					/* min = 0, max = 2, one prime */
				Arguments.arguments((Object)new Integer[][]{ { 1, 10 }, { 2, 3, 5, 7 } }), 			/* min = 1, max = 10, 4 primes */
				Arguments.arguments((Object) new Integer[][]{ { 20, 40 }, { 23, 29, 31, 37 } })); 	/* min = 20, max = 40, four primes */
	}

}
