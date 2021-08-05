package br.com.b2w.starwarsplanetsapi.resources;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.starwarsplanetsapi.entities.Planeta;
import br.com.b2w.starwarsplanetsapi.repository.PlanetaRepository;
import br.com.b2w.starwarsplanetsapi.service.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {

	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private PlanetaService planetaService;
	
	@GetMapping
	public ResponseEntity<List<Planeta>> listar(){
		List<Planeta> lista = planetaRepository.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Planeta> buscarPorNome(@PathVariable("id") String nome){
		Planeta planeta = planetaService.findByNome(nome);
		return ResponseEntity.ok(planeta);
	}
	
	@PostMapping
	public ResponseEntity<Planeta> salvar(@RequestBody Planeta planeta){
		Planeta planetaNovo = planetaRepository.save(planeta);
		return ResponseEntity.ok(planetaNovo);
		
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") String id) {
		planetaRepository.deleteById(id);
		
		return ResponseEntity.ok(new Response());
		
	}
}
