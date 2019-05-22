package br.com.adiel.projetocurso.api.repository.lancamento;

import java.util.List;

import br.com.adiel.projetocurso.api.model.Lancamento;
import br.com.adiel.projetocurso.api.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtar(LancamentoFilter lancamentoFilter);

}
