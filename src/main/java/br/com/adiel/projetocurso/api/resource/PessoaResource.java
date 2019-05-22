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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.adiel.projetocurso.api.event.RecursoCriadoEvent;
import br.com.adiel.projetocurso.api.model.Pessoa;
import br.com.adiel.projetocurso.api.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public List<Pessoa> listar(){
		List<Pessoa> lista = service.listar();
		return lista;
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		
		Pessoa pessoaSalva =  this.service.criar(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getId()));
		return  ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  buscarPeloId(@PathVariable Integer id) {
		Pessoa pessoa = this.service.buscarPeloId(id);
		if(pessoa==null || pessoa.getId()==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		this.service.remover(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id,@Valid  @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = this.service.atualizar(id, pessoa);
		return  ResponseEntity.ok(pessoaSalva);
	}
	@PutMapping("/{id}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer id,@RequestBody Boolean ativo) {
		Pessoa pessoaSalva = this.service.atualizarPropriedadeAtivo(id, ativo);
		return  ResponseEntity.ok(pessoaSalva);
	}
}
