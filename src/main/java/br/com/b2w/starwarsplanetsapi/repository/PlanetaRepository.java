package br.com.b2w.starwarsplanetsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.b2w.starwarsplanetsapi.entities.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String>{
	
	Planeta findByNomeIgnoreCase(String nome);

}
