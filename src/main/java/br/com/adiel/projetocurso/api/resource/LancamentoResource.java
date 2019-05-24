package br.com.adiel.projetocurso.api.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.adiel.projetocurso.api.event.RecursoCriadoEvent;
import br.com.adiel.projetocurso.api.exception.PessoaInexistenteOuInativaException;
import br.com.adiel.projetocurso.api.execeptionhandler.ProjetoCursoExceptionHandler.Erro;
import br.com.adiel.projetocurso.api.model.Lancamento;
import br.com.adiel.projetocurso.api.repository.filter.LancamentoFilter;
import br.com.adiel.projetocurso.api.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		Page<Lancamento> lista = service.pesquisar(lancamentoFilter,pageable);
		return lista;
	}
	 

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
		
		Lancamento lancamentoSalva =  this.service.salvar(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalva.getId()));
		return  ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalva);
	}
	
	@ExceptionHandler({PessoaInexistenteOuInativaException.class})
	public ResponseEntity<Object> handlerEmptyResultDataAccessException(PessoaInexistenteOuInativaException ex) {
		List<Erro> erros = new ArrayList<>();
		String mensagemUsuario = "Pessoa inexistente ou inativa.";
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);
		erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  buscarPeloId(@PathVariable Integer id) {
		Lancamento lancamentoSalva = this.service.buscarPeloId(id);
		if(lancamentoSalva==null || lancamentoSalva.getId()==null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(lancamentoSalva);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		this.service.remover(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> atualizar(@PathVariable Integer id,@Valid  @RequestBody Lancamento lancamento) {
		Lancamento lancamentoSalva = this.service.atualizar(id, lancamento);
		return  ResponseEntity.ok(lancamentoSalva);
	}

}
