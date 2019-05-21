package br.com.adiel.projetocurso.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adiel.projetocurso.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
