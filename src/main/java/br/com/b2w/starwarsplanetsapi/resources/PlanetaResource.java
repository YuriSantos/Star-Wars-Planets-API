package br.com.b2w.starwarsplanetsapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.starwarsplanetsapi.dto.PlanetaAPIBusca;
import br.com.b2w.starwarsplanetsapi.entities.Planeta;
import br.com.b2w.starwarsplanetsapi.service.PlanetaService;

@RestController
@RequestMapping("/planetas")
public class PlanetaResource {
	
	@Autowired
	private PlanetaService planetaService;
	
	
	@GetMapping
	public ResponseEntity<Page<Planeta>> listar(Pageable pageable){
	 
		return ResponseEntity.ok(planetaService.listarTodos(pageable));
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Planeta> buscarPorId(@PathVariable("id") String id){
		Planeta planeta = planetaService.buscarPorId(id);
		return ResponseEntity.ok(planeta);
	}
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<Planeta> buscarPorNome(@RequestAttribute("nomr") String nome){
		Planeta planeta = planetaService.buscarPorNome(nome);
		return ResponseEntity.ok(planeta);
	}
	
	@PostMapping
	public ResponseEntity<Planeta> salvar(@RequestBody Planeta planeta){
		Planeta planetaNovo = planetaService.salvar(planeta);
		return ResponseEntity.status(HttpStatus.CREATED).body(planetaNovo);
		
	}
	
	@PutMapping
	public ResponseEntity<Planeta> atualizar(@RequestBody Planeta planeta){
		Planeta planetaNovo = planetaService.atualizar(planeta);
		return ResponseEntity.ok(planetaNovo);
		
	}
	
	@DeleteMapping(value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		planetaService.excluir(id);
		
	}
	
	@GetMapping("listarTodosSW")
	public ResponseEntity<PlanetaAPIBusca> listarTodosSWApi() {
		
		return ResponseEntity.ok(planetaService.listarTodosSWApi());
	}
}
