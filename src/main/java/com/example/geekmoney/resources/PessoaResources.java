package com.example.geekmoney.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.geekmoney.event.RecursoCriadoEvent;
import com.example.geekmoney.model.Pessoa;
import com.example.geekmoney.repository.PessoaRepository;
import com.example.geekmoney.service.PessoaService;

@RestController
@RequestMapping("/pessoas") // url necessario para requests
public class PessoaResources {

	@Autowired // injeta uma instancia de PessoaRepository sem precisar de NEW
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaService pessoaService;

	@GetMapping // atende requisiçoes do tipo get PEGAR dados
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@PostMapping // realiza inserção de um dado novo // hibernate reconhece que o objeto é do
					// tipo pessoa
	public ResponseEntity<Pessoa> cria(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) { // Requestbody reconhece que o dado que esta
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		// mudar o header de resposta para conter o endereço do novo cadastro
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@GetMapping("/{codigo}") // atende requisiçoes tipo get para o codigo anotado com pathvariable retorna 1
	public ResponseEntity<?> buscaPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		return (pessoa == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(pessoa);
	}
	
	
	@DeleteMapping("/{codigo}") // deleta 1 item, caso codigo nao existe lança ecxecao que é tratada pelo EcxeptionHandler
	@ResponseStatus(HttpStatus.NO_CONTENT) // status 204
	public void remove(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}") // atualiza o dado 
	public ResponseEntity<Pessoa> atualiza(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualzia(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	public ResponseEntity<Pessoa> atualizaAtivo(@PathVariable Long codigo, @Valid @RequestBody Boolean ativo) {
		Pessoa pessoaSalva = pessoaService.atualziaAtivo(codigo, ativo);
		return ResponseEntity.ok(pessoaSalva);
	}
}
