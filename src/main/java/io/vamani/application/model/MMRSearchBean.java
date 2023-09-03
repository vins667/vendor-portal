package io.vamani.application.model;

import java.time.Instant;

public class MMRSearchBean {
	private Instant monthYear;
	private String factory;
	
	public Instant getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(Instant monthYear) {
		this.monthYear = monthYear;
	}

	public String getFactory() {
		return factory;
	}
	
	public void setFactory(String factory) {
		this.factory = factory;
	}
	
}
