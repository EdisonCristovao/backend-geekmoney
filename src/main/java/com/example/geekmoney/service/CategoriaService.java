package com.example.geekmoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.geekmoney.model.Categoria;
import com.example.geekmoney.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria atualziaCategoria(Long codigo, String nome) {
		Categoria categoriaSalva = categoriaRepository.findOne(codigo);
		if (categoriaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		categoriaSalva.setNome(nome);
		return categoriaRepository.save(categoriaSalva);
	}

}
