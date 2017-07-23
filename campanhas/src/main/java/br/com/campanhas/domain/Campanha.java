package br.com.campanhas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Campanha {

	@Id
	@GeneratedValue
	private Long id;

	private Long idTimeDoCoracao;
	
	@ManyToOne
	private Vigencia vigencia;
	
	public Campanha(){};
	
	public Campanha(Long idTimeDoCoracao, Vigencia vigencia) {
		super();
		this.idTimeDoCoracao = idTimeDoCoracao;
		this.vigencia = vigencia;
	}

}
