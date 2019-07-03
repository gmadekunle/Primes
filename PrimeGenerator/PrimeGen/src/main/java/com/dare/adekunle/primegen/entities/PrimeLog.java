package com.dare.adekunle.primegen.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PrimeLog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private Date requestTimestamp;
	
	private Integer upperLimit;
	
	private Integer lowerLimit;

	private Long duration;

	private String strategy;

	private Integer numberOfPrimes;

	private UUID uuid = UUID.randomUUID(); // Use this for equals() and hashcode(). Guaranteed not null.
	
	public PrimeLog() {}
	
	public PrimeLog(Date requestTimestamp, Integer lowerLimit, Integer upperLimit, String strategy, Integer numberOfPrimes, Long duration) {
		this.requestTimestamp = requestTimestamp;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.duration = duration;
		this.strategy = strategy;
		this.numberOfPrimes = numberOfPrimes;		
	}
	
	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public Date getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Date requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Integer getNumberOfPrimes() {
		return numberOfPrimes;
	}

	public void setNumberOfPrimes(Integer numberOfPrimes) {
		this.numberOfPrimes = numberOfPrimes;
	}

	public UUID getUuid() {
		return uuid;
	}

	protected void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Integer getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}

	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	@Override
	public int hashCode() {
		return uuid.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof PrimeLog)) {
			return false;
		}
		final PrimeLog other = (PrimeLog) obj;
		return Objects.equals(this.uuid, other.uuid);
	}

	@Override
	public String toString() {
		return String.format("%s[uuid=%s]", getClass().getSimpleName(), getUuid());
	}

}
