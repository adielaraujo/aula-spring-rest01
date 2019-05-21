package br.com.adiel.projetocurso.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.adiel.projetocurso.api.event.RecursoCriadoEvent;
import br.com.adiel.projetocurso.api.model.Pessoa;
import br.com.adiel.projetocurso.api.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> listar(){
		
		List<Pessoa> lista = repository.findAll();
		return lista;
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		
		Pessoa pessoaSalva =  this.repository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		return  ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  BuscarPeloId(@PathVariable Integer id) {
		Pessoa pessoa = this.repository.findById(id).orElse(null);
		if(pessoa==null || pessoa.getId()==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		this.repository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoa) {
		
		Pessoa pessoaSalva = this.repository.findById(id).orElse(null);
		if(pessoaSalva==null || pessoaSalva.getId()==null) {
			throw new EmptyResultDataAccessException(1);
		}
		
			
		
		BeanUtils.copyProperties(pessoa, pessoaSalva,"id");
		this.repository.save(pessoaSalva);
		
		return  ResponseEntity.ok(pessoaSalva);
	}
}
