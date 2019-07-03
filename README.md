# Primes

The repository contains 3 Maven projects that you can build and deploy. 
The first is PrimeGen, which is a standalone command line application. 
The second is PrimeGenService which is a web service application that can be deployed into any Java EE application server. 
PrimeGenService is embedded with Eclipselink/JPA/HSQLDB for persistence and uses Jersey for it's JAX-RS web service implementation. 
The third is a JSF web application that relies on the PrimeGenService web service to for prime number generation, persistence and 
retrieval of prime number logs from previous prime number generations.
