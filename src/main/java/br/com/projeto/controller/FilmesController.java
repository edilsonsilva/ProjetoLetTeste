package br.com.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.model.Filmes;
import br.com.projeto.model.Ranking;
import br.com.projeto.model.Usuario;
import br.com.projeto.repository.FilmeRepository;
import br.com.projeto.repository.RankingRepository;
import br.com.projeto.repository.UsuarioRepository;
import br.com.projeto.util.JwtUtil;

@RestController
@RequestMapping("/filmes")
public class FilmesController {
	
	int rodada=0;
	int ponto=0; 
	Long p1;
	Long p2;
		
	Map<Long, Double> opcao = new HashMap<Long,Double>();
	



	@Autowired
	private FilmeRepository filmeRepository;
	
	
	@Autowired
	private RankingRepository rankingRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	
	@GetMapping("/listar")
	public List<Filmes> listar(){
		return filmeRepository.findAll();
	}
	
	@GetMapping("/apresentarfilmes")
	public Object apresentarFilmes(){
		
		p1 = Long.parseLong(String.valueOf(Math.round(Math.random()*20)));
		p2 = Long.parseLong(String.valueOf(Math.round(Math.random()*20)));
		
		if(p1==p2) {
			p2 = Long.parseLong(String.valueOf(Math.random()*20));
		}
		
		Optional<Filmes> f1 = filmeRepository.findById(p1);
		Optional<Filmes> f2 = filmeRepository.findById(p2);
		
		opcao.put(p1, Double.parseDouble(f1.get().getVote_average()));
		opcao.put(p2,Double.parseDouble(f2.get().getVote_average()));
		
				
		String titulo="Escolha um dos filmes que tem maior voto. Digite o Id do filme";
		
		return new Object[] {titulo,f1,f2};
	}
	
	@GetMapping("/escolha/{id}")
	public String escolha(@PathVariable Long id) {
		String texto = "";
		if (opcao.get(id)>opcao.get(p2)) {
			ponto++;
			rodada++;
			texto = "Acertou";
		}		
		if(opcao.get(id)==opcao.get(p2)) {
			ponto++;
			rodada++;
			texto = "Acertou";
		}
			
		return texto;
	}
	
	@GetMapping("/sair")
	public String sair() {
		
		Ranking rk = new Ranking();
		
		
		
		rk.setIdUsuario((long)usuarioRepository.findByNomeUsuario("pedro").getIdUsuario());
		rk.setPonto((long) ponto);
		rankingRepository.save(rk);
		
		return "Logout";
				
	}
	
}











