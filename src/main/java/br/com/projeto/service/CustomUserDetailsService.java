package br.com.projeto.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.projeto.model.Usuario;
import br.com.projeto.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {

		Usuario us = repository.findByNomeUsuario(nomeUsuario);
		return new org.springframework.security.core.userdetails.User( us.getNomeUsuario(),us.getSenhaUsuario(), new ArrayList<>());
	}

}
