package br.com.adiel.projetocurso.api.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.adiel.projetocurso.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);
	
}
