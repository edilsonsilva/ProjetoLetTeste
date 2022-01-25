package br.com.projeto.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ranking {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idRanking;
	
	@Column
	private Long idUsuario;
	
	@Column
	private Long ponto;

	public Ranking() {
	}

	public Ranking(Long idRanking, Long idUsuario, Long ponto) {
		this.idRanking = idRanking;
		this.idUsuario = idUsuario;
		this.ponto = ponto;
	}

	public Long getIdRanking() {
		return idRanking;
	}

	public void setIdRanking(Long idRanking) {
		this.idRanking = idRanking;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getPonto() {
		return ponto;
	}

	public void setPonto(Long ponto) {
		this.ponto = ponto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRanking);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ranking other = (Ranking) obj;
		return Objects.equals(idRanking, other.idRanking);
	}

	@Override
	public String toString() {
		return "Ranking [idRanking=" + idRanking + ", idUsuario=" + idUsuario + ", ponto=" + ponto + "]";
	}
	
	
	
	
}
