package br.com.b2w.starwarsplanetsapi.service.exception;

public class PlanetaJaInseridoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public PlanetaJaInseridoException(String message) {
		super(message);
	}

}
