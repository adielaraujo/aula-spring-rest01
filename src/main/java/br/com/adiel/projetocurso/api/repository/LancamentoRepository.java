package br.com.adiel.projetocurso.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adiel.projetocurso.api.model.Lancamento;
import br.com.adiel.projetocurso.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>, LancamentoRepositoryQuery {
	
	
}
