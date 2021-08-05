package br.com.b2w.starwarsplanetsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b2w.starwarsplanetsapi.entities.Planeta;
import br.com.b2w.starwarsplanetsapi.repository.PlanetaRepository;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	public Planeta findByNome(String nome) {
		return planetaRepository.findByNome(nome);
	}

}
