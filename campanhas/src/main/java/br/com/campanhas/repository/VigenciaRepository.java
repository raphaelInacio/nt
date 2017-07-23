package br.com.campanhas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.campanhas.domain.Vigencia;

public interface VigenciaRepository extends JpaRepository<Vigencia, Long>  {
	public List<Vigencia> findAllByFim(Date fim);
}
