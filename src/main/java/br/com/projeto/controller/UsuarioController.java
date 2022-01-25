package br.com.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.AuthRequest;
import br.com.projeto.model.Usuario;
import br.com.projeto.repository.UsuarioRepository;
import br.com.projeto.util.JwtUtil;

@RestController

public class UsuarioController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	 
	
	@Autowired
	private UsuarioRepository ur;
	
	
	@PostMapping("/cadastro")
	public String cadastro(@RequestBody Usuario usuario) {
		ur.save(usuario);
		return "Cadastro realizado";
	}
	
	@GetMapping("/listar")
	public List<Usuario> listar(){
		return ur.findAll();
	}
//	@GetMapping("/")
//	public String welcome() {
//		return "Welcome to Java";
//	}
	
	@PostMapping("/autenticar")
	public String gerarToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getNomeUsuario(), authRequest.getSenhaUsuario())); 
		}
		catch(Exception ex) {
			throw new Exception("Usuário ou senha inválidos");
		}
		return jwtUtil.generateToken(authRequest.getNomeUsuario());
	}
}
