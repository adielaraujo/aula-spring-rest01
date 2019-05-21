package br.com.adiel.projetocurso.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adiel.projetocurso.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
