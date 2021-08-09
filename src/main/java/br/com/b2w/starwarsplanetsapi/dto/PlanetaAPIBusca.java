package br.com.b2w.starwarsplanetsapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaAPIBusca {
	
	 private int count;
	 private PlanetaAPI[] results;
	 
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public PlanetaAPI[] getResults() {
		return results;
	}
	public void setResults(PlanetaAPI[] results) {
		this.results = results;
	}
	 
	 

}
