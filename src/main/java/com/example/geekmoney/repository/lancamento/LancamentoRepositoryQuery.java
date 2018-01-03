package com.example.geekmoney.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.geekmoney.model.Lancamento;
import com.example.geekmoney.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<Lancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
