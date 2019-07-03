package com.dare.adekunle.primegen.cli;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dare.adekunle.primegen.db.PrimeDataService;
import com.dare.adekunle.primegen.entities.PrimeLog;
import com.dare.adekunle.primegen.generators.PrimeGenerator;
import com.dare.adekunle.primegen.generators.PrimeGeneratorRegister;

public class PrimeGeneratorCLI {
	//TODO: add command line args
	//TODO: add restart or quit
	//TODO: add exception handling
	//TODO: add validation
	
	 private static final Logger LOGGER = Logger.getLogger(PrimeGeneratorCLI.class.getName());
	 
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		int x, y;
		String strategy;
		
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Enter a positive integer as (inclusive) lower bound");
			x = scanner.nextInt();

			System.out.println("Enter a positive integer (inclusive) as upper bound");
			y = scanner.nextInt();

			if (y < x) {
				System.out.println("Invalid upper bound. Please enter a value greater or equal to " + x);
			}
			
			System.out.println("Enter the name of the desired strategy (or any key for the default strategy) ");
			strategy = scanner.next();
		}

		Class<? extends PrimeGenerator> gen = PrimeGeneratorRegister.getGenerator(strategy);
		if(gen == null) {//if not a valid generator strategy, fall back to the default generator strategy
			gen = PrimeGeneratorRegister.getGenerator(PrimeGeneratorRegister.DEFAULT_GENERATOR_STRATEGY);
		}
		
		PrimeGenerator primeGen = gen.newInstance();
		
		List<Integer> primes = primeGen.getPrimes(x, y);		
		
		System.out.println();
		System.out.println(primes);
		System.out.println(primeGen.getDurationMillis() + " ms");
		
		PrimeLog primeLog = new PrimeLog(new Date(), x, y, strategy, primes.size(), primeGen.getDurationMillis());
		
		try {
			PrimeDataService.getInstance().savePrimeLog(primeLog);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception saving PrimeLog instance", e);
		}
	}
}
