package br.com.adiel.projetocurso.api.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.adiel.projetocurso.api.model.Pessoa;
import br.com.adiel.projetocurso.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository repository;

	public List<Pessoa> listar() {
		List<Pessoa> lista = repository.findAll();
		return lista;
	}

	public Pessoa criar(Pessoa pessoa) {

		Pessoa pessoaSalva = this.repository.save(pessoa);
		return pessoaSalva;
	}

	public Pessoa buscarPeloId(Integer id) {
		Pessoa pessoa = this.repository.findById(id).orElse(null);
		return pessoa;
	}

	public void remover(Integer id) {
		this.repository.deleteById(id);
	}

	public Pessoa atualizar(Integer id, Pessoa pessoa) {

		Pessoa pessoaSalva = this.buscarPeloId(id);
		if (pessoaSalva == null || pessoaSalva.getId() == null) {
			throw new EmptyResultDataAccessException(1);
		}

		BeanUtils.copyProperties(pessoa, pessoaSalva, "id");
		this.repository.save(pessoaSalva);

		return pessoaSalva;
	}
	
	public Pessoa atualizarPropriedadeAtivo(Integer id, Boolean ativo) {

		Pessoa pessoaSalva = this.buscarPeloId(id);
		
		if (pessoaSalva == null || pessoaSalva.getId() == null) {
			throw new EmptyResultDataAccessException(1);
		}

		pessoaSalva.setAtivo(ativo);
		this.repository.save(pessoaSalva);
		return pessoaSalva;
	}

}
