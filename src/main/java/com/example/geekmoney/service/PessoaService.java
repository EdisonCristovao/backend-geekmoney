package com.example.geekmoney.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.geekmoney.model.Pessoa;
import com.example.geekmoney.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualzia(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscaPessoaPorCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo"); // fonte, destino, ignorar
		return pessoaRepository.save(pessoaSalva);
	}

	public Pessoa atualziaAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscaPessoaPorCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		return pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa buscaPessoaPorCodigo(Long codigo) {
		Pessoa pessoaSalva = pessoaRepository.findOne(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
