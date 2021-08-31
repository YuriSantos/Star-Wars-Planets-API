package br.com.b2w.starwarsplanetsapi.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.starwarsplanetsapi.dto.PlanetaAPIBusca;
import br.com.b2w.starwarsplanetsapi.service.exception.ErroAoConsultarAPISWException;
import br.com.b2w.starwarsplanetsapi.service.exception.PlanetaNaoEncontradoException;

@Service
public class SwApiService {
	
	@Value("${swapi.url}")
	private  String URL;
	private static final String URL_SEARCH = "/?search=";
	
	@Autowired
	private MessageSource messageSource;
	
	
	public PlanetaAPIBusca consultarPlanetaSWApi(String nome) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		PlanetaAPIBusca planeta = new PlanetaAPIBusca();
		
		try {
			planeta =	restTemplate.getForObject(
						URI.create(URL + URL_SEARCH + java.net.URLEncoder.encode(nome, "UTF-8")),
						PlanetaAPIBusca.class);
		} catch (RestClientException e) {
			throw new ErroAoConsultarAPISWException(messageSource.
					getMessage("swapi.erro", null, Locale.getDefault()));
		} catch (UnsupportedEncodingException e) {
			throw new ErroAoConsultarAPISWException(messageSource.
					getMessage("swapi.erro", null, Locale.getDefault()));
		}
		
		return planeta;
		
	}
	
	public PlanetaAPIBusca consultarTodosSWApi() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		return	restTemplate.getForObject(
					URI.create(URL), PlanetaAPIBusca.class);
	}

}
