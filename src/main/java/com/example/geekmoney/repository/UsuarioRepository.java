package com.example.geekmoney.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

 import com.example.geekmoney.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public Optional<Usuario> findByEmail(String email);
}
