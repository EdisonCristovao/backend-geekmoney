package com.example.geekmoney.repository.lancamento;

import java.util.List;

import com.example.geekmoney.model.Lancamento;
import com.example.geekmoney.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
