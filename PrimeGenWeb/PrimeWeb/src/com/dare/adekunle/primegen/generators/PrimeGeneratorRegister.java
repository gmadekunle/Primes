package com.dare.adekunle.primegen.generators;

import java.util.HashMap;
import java.util.Map;

public class PrimeGeneratorRegister {
	private static final Map<String, Class<? extends PrimeGenerator>> STRATEGY_MAP;
	public static final String DEFAULT_GENERATOR_STRATEGY = ModeratePrimeGenerator.NAME;
	private static final Class<? extends PrimeGenerator> DEFAULT_GENERATOR = ModeratePrimeGenerator.class;
	
	static {
		STRATEGY_MAP = new HashMap<>();
		STRATEGY_MAP.put(SlowPrimeGenerator.NAME.toLowerCase(), SlowPrimeGenerator.class);
		STRATEGY_MAP.put(FastPrimeGenerator.NAME.toLowerCase(), FastPrimeGenerator.class);
		STRATEGY_MAP.put(ModeratePrimeGenerator.NAME.toLowerCase(), ModeratePrimeGenerator.class);
		STRATEGY_MAP.put(DEFAULT_GENERATOR_STRATEGY.toLowerCase(), DEFAULT_GENERATOR);//default
	}
	
	public static final Class<? extends PrimeGenerator> getGenerator(String strategy) {
		return STRATEGY_MAP.get(strategy != null ? strategy.toLowerCase() : null);
	}

	public static final void registerGenerator(String strategy, Class<? extends PrimeGenerator> clazz) {
		STRATEGY_MAP.put(strategy.toLowerCase(), clazz);
	}

	public static final void deregisterGenerator(String strategy) {
		STRATEGY_MAP.remove(strategy.toLowerCase());
	}
}
