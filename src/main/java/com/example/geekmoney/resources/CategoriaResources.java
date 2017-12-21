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
import com.example.geekmoney.model.Categoria;
import com.example.geekmoney.model.Pessoa;
import com.example.geekmoney.repository.CategoriaRepository;
import com.example.geekmoney.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public List<Categoria> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
		// return !categorias.isEmpty() ? ResponseEntity.ok(categorias) :
		// ResponseEntity.notFound().build(); retorna 204 ResponseEntity<?>
		// return !categorias.isEmpty() ? ResponseEntity.ok(categorias) :
		// ResponseEntity.noContent().build(); retorna 404 ResponseEntity<?>
	}

	
	//@ResponseStatus(HttpStatus.CREATED) o created() ja diz o status
	@PostMapping
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		// source this, quem gerou o evento, response ta no metodo e codigo é obtido pelo categoriaSalva
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoria.getCodigo()));
		
		//response.setHeader("Location", uri.toASCIIString()); essa linha fez aparecer 2 headers
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscaPeloCodigo(@PathVariable Long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo);
		return (categoria == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
		//retona status 404 se nao tiver valor
		//retorna status 200 ok se tiver valor
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long codigo) {
		categoriaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}") // atualiza o dado 
	public ResponseEntity<Categoria> atualizaCategoria(@PathVariable Long codigo, @Valid @RequestBody String nome) {
		Categoria categoriaSalva = categoriaService.atualziaCategoria(codigo, nome);
		return ResponseEntity.ok(categoriaSalva);
	}
}
