package br.com.b2w.starwarsplanetsapi.service.exception.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.b2w.starwarsplanetsapi.service.exception.ErroAoConsultarAPISWException;
import br.com.b2w.starwarsplanetsapi.service.exception.PlanetaJaInseridoException;
import br.com.b2w.starwarsplanetsapi.service.exception.PlanetaNaoEncontradoException;

@ControllerAdvice
public class PlanetaExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler({PlanetaNaoEncontradoException.class})
	public ResponseEntity<Object> handlePlanetaNaoEncontradoException(PlanetaNaoEncontradoException ex,
			WebRequest request) {
		
		return handleExceptionInternal(ex, 
				messageSource.getMessage("planeta.nao-encontrado", null, LocaleContextHolder.getLocale()),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({PlanetaJaInseridoException.class})
	public ResponseEntity<Object> handlePlanetaJaInseridoException(PlanetaJaInseridoException ex,
			WebRequest request) {
		
		return handleExceptionInternal(ex, 
				messageSource.getMessage("planeta.ja-cadastrado", null, LocaleContextHolder.getLocale()),
				new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler({ErroAoConsultarAPISWException.class})
	public ResponseEntity<Object> handleErroAoConsultarAPISWException(ErroAoConsultarAPISWException ex,
			WebRequest request) {
		
		return handleExceptionInternal(ex, 
				messageSource.getMessage("swapi.erro", null, LocaleContextHolder.getLocale()),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}
