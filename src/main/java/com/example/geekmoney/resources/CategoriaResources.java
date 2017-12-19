package com.example.geekmoney.resources;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.geekmoney.model.Categoria;
import com.example.geekmoney.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResources {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public List<Categoria> listar() {
		List<Categoria> categorias = categoriaRepository.findAll();
		return categorias;
		// return !categorias.isEmpty() ? ResponseEntity.ok(categorias) :
		// ResponseEntity.notFound().build(); retorna 204 ResponseEntity<?>
		// return !categorias.isEmpty() ? ResponseEntity.ok(categorias) :
		// ResponseEntity.noContent().build(); retorna 404 ResponseEntity<?>
	}

	@PostMapping
	//@ResponseStatus(HttpStatus.CREATED) o created() ja diz o status
	public ResponseEntity<Categoria> criar(@RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscaPeloCodigo(@PathVariable Long codigo) {
		Categoria categoria = categoriaRepository.findOne(codigo);
		return (categoria == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(categoria);
		//retona status 404 se nao tiver valor
		//retorna status 200 ok se tiver valor
	}
}
