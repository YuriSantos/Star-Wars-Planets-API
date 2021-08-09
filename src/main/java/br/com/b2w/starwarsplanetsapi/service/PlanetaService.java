package br.com.b2w.starwarsplanetsapi.service;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.b2w.starwarsplanetsapi.dto.PlanetaAPI;
import br.com.b2w.starwarsplanetsapi.dto.PlanetaAPIBusca;
import br.com.b2w.starwarsplanetsapi.entities.Planeta;
import br.com.b2w.starwarsplanetsapi.repository.PlanetaRepository;
import br.com.b2w.starwarsplanetsapi.service.exception.PlanetaJaInseridoException;
import br.com.b2w.starwarsplanetsapi.service.exception.PlanetaNaoEncontradoException;

@Service
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private SwApiService swApiService;
	
	@Autowired
	private MessageSource messageSource;
	
	public Planeta findByNome(String nome) {
		return planetaRepository.findByNomeIgnoreCase(nome);
	}
	
	public Planeta salvar(Planeta planeta) {
		
		Planeta planetaVerificado = planetaRepository.findByNomeIgnoreCase(planeta.getNome());
		
		if(planetaVerificado != null) {
			throw new PlanetaJaInseridoException(
					messageSource.getMessage("planeta.ja-cadastrado", null, Locale.getDefault()));
		}
		
		return planetaRepository.save(planeta);	
		
	}
	
	public Planeta atualizar(Planeta planeta) {
		
		Optional<Planeta> planetaOpt = planetaRepository.findById(planeta.getId());
		
		if(planetaOpt.isEmpty()) {
			throw new PlanetaNaoEncontradoException(messageSource.
					getMessage("planeta.nao-encontrado", null, Locale.getDefault()));
		}
		
		return planetaRepository.save(planeta);	
		
	}
	
	public Planeta buscarPorNome(String nome) {
		
		Planeta planeta= planetaRepository.findByNomeIgnoreCase(nome);
		
		if(planeta == null) {
			throw new PlanetaNaoEncontradoException(messageSource.
					getMessage("planeta.nao-encontrado", null, Locale.getDefault()));
		}
		
		planeta.setFilmes(buscarFilmesAPI(planeta.getNome()));
		
		return planeta;
	}
	
	public Planeta buscarPorId(String id) {
		
		Optional<Planeta> planetaOpt = planetaRepository.findById(id);
		
		if(planetaOpt.isEmpty()) {
			throw new PlanetaNaoEncontradoException(messageSource.
					getMessage("planeta.nao-encontrado", null, Locale.getDefault()));
		}
		
		Planeta planeta = planetaOpt.get();
		
		planeta.setFilmes(buscarFilmesAPI(planeta.getNome()));
		
		return planeta;
	}
	
	public void excluir(String id) {
		
		Optional<Planeta> planeta = planetaRepository.findById(id);
		
		if(planeta.isEmpty()) {
			throw new PlanetaNaoEncontradoException(messageSource.
					getMessage("planeta.nao-encontrado", null, Locale.getDefault()));
		}
		
		planetaRepository.delete(planeta.get());	
		
	}
	
	public Page<Planeta> listarTodos(Pageable pageable) {
		Page<Planeta> planetas = planetaRepository.findAll(pageable);
		
		for(Planeta planeta : planetas) {
			
			planeta.setFilmes(buscarFilmesAPI(planeta.getNome()));
			
		}
		
		return planetas;
	}
	
	public PlanetaAPIBusca listarTodosSWApi() {
		
		return swApiService.consultarTodosSWApi();
	}
	
	private int buscarFilmesAPI(String nome) {
		
		PlanetaAPIBusca planetaAPIBusca = 
				swApiService.consultarPlanetaSWApi(nome);
		
		Optional<PlanetaAPI> planetaAPI = Arrays.stream(planetaAPIBusca.getResults())
                .filter(planeta -> nome.equals(planeta.getName()))
                .findFirst();
		
		return planetaAPI.get().getNumerosFilmes();
		
	}

}
