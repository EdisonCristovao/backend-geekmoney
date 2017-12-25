package com.example.geekmoney.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.example.geekmoney.model.Lancamento;
import com.example.geekmoney.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImp implements LancamentoRepositoryQuery {

	@Autowired
	private EntityManager manager;

	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);

		Root<Lancamento> root = criteria.from(Lancamento.class);
		//
		Predicate[] predicates = criarRestriçoes(lancamentoFilter, root, builder);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestriçoes(LancamentoFilter lancamentoFilter, Root<Lancamento> root,
			CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {

		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoDe())) {

		}

		if (!StringUtils.isEmpty(lancamentoFilter.getDataVencimentoAte() )) {

		}

		return null;
	}

}
