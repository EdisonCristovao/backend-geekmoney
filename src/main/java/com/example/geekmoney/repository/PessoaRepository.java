package com.example.geekmoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.geekmoney.model.Pessoa;

// extend outra interface e da entidade modelo com seu devido ID
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
