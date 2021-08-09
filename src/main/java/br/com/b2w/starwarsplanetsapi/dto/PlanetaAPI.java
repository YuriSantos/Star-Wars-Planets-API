package br.com.b2w.starwarsplanetsapi.dto;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaAPI {
	
	private String name;
	private URI[] films;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URI[] getFilms() {
		return films;
	}

	public void setFilms(URI[] films) {
		this.films = films;
	}
	
	public int getNumerosFilmes() {
		 return this.getFilms() == null ? 0 : this.getFilms().length;
	}

}
