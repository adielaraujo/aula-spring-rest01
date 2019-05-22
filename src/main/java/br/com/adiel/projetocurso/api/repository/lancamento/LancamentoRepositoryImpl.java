package br.com.adiel.projetocurso.api.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.com.adiel.projetocurso.api.model.Lancamento;
import br.com.adiel.projetocurso.api.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manger;

	@Override
	public List<Lancamento> filtar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = this.manger.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);

		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = this.criarRestricoes(lancamentoFilter, builder, root);

		criteria.where(predicates);

		TypedQuery<Lancamento> query = manger.createQuery(criteria);

		List<Lancamento> lista = query.getResultList();
		return lista;

	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (lancamentoFilter.getDescricao() != null && !StringUtils.isEmpty(lancamentoFilter.getDescricao()) ) {
			predicates.add(builder.like(builder.lower(root.get("descricao")), "%"+lancamentoFilter.getDescricao()+"%"));
		}
		if (lancamentoFilter.getDataVencimentoDe() != null) {
//			predicates.add(e);
		}
		if (lancamentoFilter.getDataVencimentoAte() != null) {
//			predicates.add(e);
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
