package br.com.b2w.starwarsplanetsapi.service.exception;

public class PlanetaNaoEncontradoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public PlanetaNaoEncontradoException(String message) {
		super(message);
	}

}
