package com.example.geekmoney.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.geekmoney.model.Pessoa;
import com.example.geekmoney.repository.PessoaRepository;

@RestController 
@RequestMapping("/pessoas") // url necessario para requests
public class PessoaResources {
 
	@Autowired // injeta uma instancia de PessoaRepository sem precisar de NEW
	private PessoaRepository pessoaRepository;
	
	@GetMapping // atende requisiçoes do tipo get PEGAR dados
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	@PostMapping // realiza inserção de um dado novo
	public void cria(@RequestBody Pessoa pessoa) { // Requestbody reconhece que o dado que esta vindo e uma instancia de pessoa
		pessoaRepository.save(pessoa);
	}
}
