package br.com.adiel.projetocurso.api.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.adiel.projetocurso.api.model.Lancamento;
import br.com.adiel.projetocurso.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public Page<Lancamento> filtar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
