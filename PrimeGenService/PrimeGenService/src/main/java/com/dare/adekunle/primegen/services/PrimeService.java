package com.dare.adekunle.primegen.services;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dare.adekunle.primegen.db.PrimeDataService;
import com.dare.adekunle.primegen.entities.PrimeLog;
import com.dare.adekunle.primegen.generators.PrimeGenerator;
import com.dare.adekunle.primegen.generators.PrimeGeneratorRegister;

@Path("/primes")
public class PrimeService {
	
	 private static final Logger LOGGER = Logger.getLogger(PrimeService.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("get/{strategy}/{min}-{max}")
	public List<Integer> getPrimes(@PathParam("strategy") String strategy, @PathParam("min") int min,
			@PathParam("max") int max) throws InstantiationException, IllegalAccessException {

		//Instantiate requested strategy. If not found, instantiate default strategy
		Class<? extends PrimeGenerator> gen = PrimeGeneratorRegister.getGenerator(strategy);
		if(gen == null) {//if not a valid generator strategy, fall back to the default generator strategy
			gen = PrimeGeneratorRegister.getGenerator(PrimeGeneratorRegister.DEFAULT_GENERATOR_STRATEGY);
		}
		PrimeGenerator primeGen = gen.newInstance();
		
		//fetch list of primes
		List<Integer> primes = primeGen.getPrimes(min, max);
		long duration = primeGen.getDurationMillis();
		
		//Log prime request/response details
		PrimeLog primeLog = new PrimeLog(new Date(), min, max, strategy, primes.size(), duration);
		
		try {
			PrimeDataService.getInstance().savePrimeLog(primeLog);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception saving PrimeLog instance", e);
		}
		return primes;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getPrimeLogs")
	public List<PrimeLog> getPrimeLogs(){
		List<PrimeLog> primeLogs = null;
		try {
			primeLogs = PrimeDataService.getInstance().fetchAllPrimeLogs();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception saving PrimeLog instance", e);
		}
		return primeLogs;
	}
}