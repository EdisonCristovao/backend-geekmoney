package com.example.geekmoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.geekmoney.model.Lancamento;
import com.example.geekmoney.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

}
