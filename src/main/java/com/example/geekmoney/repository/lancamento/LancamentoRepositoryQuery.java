package com.example.geekmoney.repository.lancamento;

import com.example.geekmoney.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.geekmoney.model.Lancamento;
import com.example.geekmoney.repository.filter.LancamentoFilter;
import org.springframework.data.jpa.repository.Query;

public interface LancamentoRepositoryQuery {

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
