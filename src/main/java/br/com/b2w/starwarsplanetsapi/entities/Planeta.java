package br.com.b2w.starwarsplanetsapi.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Planeta {
	
	@Id
	private String id;
	private String nome;
	private String clima;
	private String terreno;
	
	@Transient
	private int filmes;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getClima() {
		return clima;
	}
	
	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getFilmes() {
		return filmes;
	}

	public void setFilmes(int filmes) {
		this.filmes = filmes;
	}
	
}
