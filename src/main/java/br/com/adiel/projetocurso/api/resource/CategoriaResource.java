package br.com.adiel.projetocurso.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.adiel.projetocurso.api.event.RecursoCriadoEvent;
import br.com.adiel.projetocurso.api.model.Categoria;
import br.com.adiel.projetocurso.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Categoria> listar(){
		
		List<Categoria> lista = repository.findAll();
		return lista;
	}
	
//	@GetMapping
//	public ResponseEntity<?> listar(){
//		
//		List<Categoria> lista = repository.findAll();
//		if(lista==null || lista.size()<1)
//			return ResponseEntity.noContent().build();
//		return ResponseEntity.ok(lista);
//	}
	
	
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public void criar(@RequestBody Categoria categoria, HttpServletResponse response) {
//		
//		Categoria categoriaSalva =  this.repository.save(categoria);
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getId()).toUri();
//		response.setHeader("Location", uri.toASCIIString());
//		 
//	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		
		Categoria categoriaSalva =  this.repository.save(categoria);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getId()));		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  BuscarPeloId(@PathVariable Integer id) {
		Categoria categoria = this.repository.findById(id).orElse(null);
		if(categoria==null || categoria.getId()==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		this.repository.deleteById(id);
	}
}
