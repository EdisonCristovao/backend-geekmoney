package com.example.geekmoney.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.geekmoney.model.Usuario;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioSistema(Collection<? extends GrantedAuthority> authorities, Usuario usuario) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
