package br.com.adiel.projetocurso.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.adiel.projetocurso.api.exception.PessoaInexistenteOuInativaException;
import br.com.adiel.projetocurso.api.model.Lancamento;
import br.com.adiel.projetocurso.api.model.Pessoa;
import br.com.adiel.projetocurso.api.repository.LancamentoRepository;
import br.com.adiel.projetocurso.api.repository.filter.LancamentoFilter;

@Service
public class LancamentoService {

	
	@Autowired
	LancamentoRepository repository;
	
	@Autowired
	PessoaService pessoaService; 

	public List<Lancamento> pesquisar(LancamentoFilter lancamentoFilter) {
		List<Lancamento> lista = repository.filtar(lancamentoFilter);
		return lista;
	}

	public Lancamento salvar(Lancamento lancamento) {
		
		Pessoa pessoa = this.pessoaService.buscarPeloId(lancamento.getPessoa().getId());
		if(pessoa==null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException(); 
		}
		
		Lancamento lancamentoSalva = this.repository.save(lancamento);
		return lancamentoSalva;
	}

	public Lancamento buscarPeloId(Integer id) {
		Lancamento lancamento = this.repository.findById(id).orElse(null);
		return lancamento;
	}

	public void remover(Integer id) {
		this.repository.deleteById(id);
	}

	public Lancamento atualizar(Integer id, Lancamento lancamento) {

		Lancamento lancamentoSalva = this.buscarPeloId(id);
		if (lancamentoSalva == null || lancamentoSalva.getId() == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(lancamento, lancamentoSalva, "id");
		this.repository.save(lancamentoSalva);

		return lancamentoSalva;
	}
	
	
}
