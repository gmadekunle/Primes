package com.dare.adekunle.primegen.cli;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.dare.adekunle.primegen.generators.PrimeGenerator;
import com.dare.adekunle.primegen.generators.PrimeGeneratorRegister;

public class PrimeGeneratorCLI {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
			try (Scanner scanner = new Scanner(System.in)) {
				boolean exit = false;
				
				int x = -1, y = -1;
				String strategy;
				
				do {				
				
				System.out.println("Enter an integer as (inclusive) lower limit");
				x = scanner.nextInt();

				System.out.println("Enter an integer (inclusive) as upper bound");
				y = scanner.nextInt();

				while (y < x) {
					System.out.println("Invalid upper bound. Please enter an integer greater or equal to " + x);
					y = scanner.nextInt();
				}

				System.out
						.println("Enter the name of the desired strategy: fast, moderate or slow");
				System.out
				.println(" or enter any other key for the default (fast) strategy");
		
				strategy = scanner.next();

				Class<? extends PrimeGenerator> gen = PrimeGeneratorRegister.getGenerator(strategy);
				if (gen == null) {// if not a valid generator strategy, fall back to the default generator
									// strategy
					gen = PrimeGeneratorRegister.getGenerator(PrimeGeneratorRegister.DEFAULT_GENERATOR_STRATEGY);
				}

				PrimeGenerator primeGen = gen.newInstance();

				List<Integer> primes = primeGen.getPrimes(x, y);

				System.out.println();
				System.out.println(primes);
				System.out.println(primeGen.getDurationMillis() + " ms");

				System.out.println();
				System.out.println("Enter x to exit or any other key to continue");
				exit = "x".equalsIgnoreCase(scanner.next().toLowerCase());
				
				} while (!exit);
			}
		
	}
}
