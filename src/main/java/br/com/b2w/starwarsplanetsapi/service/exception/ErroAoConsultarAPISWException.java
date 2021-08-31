package br.com.b2w.starwarsplanetsapi.service.exception;

public class ErroAoConsultarAPISWException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ErroAoConsultarAPISWException(String message) {
		super(message);
	}

}
