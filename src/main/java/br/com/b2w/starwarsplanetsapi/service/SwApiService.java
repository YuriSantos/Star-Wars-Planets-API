package br.com.b2w.starwarsplanetsapi.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.b2w.starwarsplanetsapi.dto.PlanetaAPIBusca;

@Service
public class SwApiService {
	
	@Value("${swapi.url}")
	private  String URL;
	private static final String URL_SEARCH = "/?search=";
	
	
	public PlanetaAPIBusca consultarPlanetaSWApi(String nome) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		return	restTemplate.getForObject(
					URI.create(URL + URL_SEARCH + nome), PlanetaAPIBusca.class);
		
	}
	
	public PlanetaAPIBusca consultarTodosSWApi() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		return	restTemplate.getForObject(
					URI.create(URL), PlanetaAPIBusca.class);
	}

}
