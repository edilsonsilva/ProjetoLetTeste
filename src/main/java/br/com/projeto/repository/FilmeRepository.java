package br.com.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.projeto.model.Filmes;

public interface FilmeRepository extends JpaRepository<Filmes, Long> {

	
}
