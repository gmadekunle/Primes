package com.dare.adekunle.primegen.web.ui.beans;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dare.adekunle.primegen.entities.PrimeLog;

@SuppressWarnings("deprecation")
@ManagedBean
@ViewScoped
public class PrimeBean implements Serializable {

	private static final Logger LOGGER = Logger.getLogger(PrimeBean.class.getName());
	private static final long serialVersionUID = 1L;
	private int lowerLimit;
	private int upperLimit;
	private String strategy;
	private List<PrimeLog> primeLogs;
	private List<Integer> generatedPrimes;
	private static final String PRIME_GEN_SERVICE_URL = "http://localhost:8080/PrimeGenService-1.0.0/services/primes";
	
	@PostConstruct
	public void initialize() {}

	@SuppressWarnings("unchecked")
	private List<PrimeLog> fetchPrimeLogs() {
		// Build the service call URL
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(PRIME_GEN_SERVICE_URL);
		webTarget = webTarget.path("getPrimeLogs");

		// Execute call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

		// Check response code. 200 is OK
		if (response.getStatus() != 200) {
			String message = "Error invoking prime generator web service - "
					+ response.getStatusInfo().getReasonPhrase();
			LOGGER.log(Level.SEVERE, message);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Server Error",
					"Unable to fetch prime log.");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return null;
		}

		// Call was successful
		return response.readEntity(List.class);
	}

	public void generatePrimesAction() {
		generatePrimes();
	}

	
	@SuppressWarnings("unchecked")
	private void generatePrimes() {
		// Build the service call URL
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(PRIME_GEN_SERVICE_URL);
		webTarget = webTarget.path("get").path(strategy).path(lowerLimit + "-" + upperLimit);

		// Execute call
		Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

		// Check response code. 200 is OK
		if (response.getStatus() != 200) {
			String message = "Error invoking prime generator web service - "
					+ response.getStatusInfo().getReasonPhrase();
			LOGGER.log(Level.SEVERE, message);
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Server Error",
					"Unable to fulfil the request. Please try again in a short while.");
			FacesContext.getCurrentInstance().addMessage(null, facesMsg);
			return;
		}

		// Call was successful
		generatedPrimes = response.readEntity(List.class);
		primeLogs = null;//triggers a reload
		
	}

	public int getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public int getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public List<PrimeLog> getPrimeLogs() {
		if (primeLogs == null) {
			primeLogs = fetchPrimeLogs();
		}
		return primeLogs;
	}

	public void setPrimeLogs(List<PrimeLog> primeLogs) {
		this.primeLogs = primeLogs;
	}

	public List<Integer> getGeneratedPrimes() {
		return generatedPrimes;
	}

	public void setGeneratedPrimes(List<Integer> generatedPrimes) {
		this.generatedPrimes = generatedPrimes;
	}

}
