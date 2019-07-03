package com.dare.adekunle.primegen.services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/services")
public class PrimeServiceConfig extends ResourceConfig {
	public PrimeServiceConfig() {
		packages("com.dare.adekunle.primegen.services");
	}
}
