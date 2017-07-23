package br.com.campanhas;

import org.joda.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.campanhas.domain.Campanha;
import br.com.campanhas.domain.Vigencia;
import br.com.campanhas.repository.CampanhaRepository;
import br.com.campanhas.repository.VigenciaRepository;

@SpringBootApplication
public class CampanhaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampanhaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CampanhaRepository campanhaRepositorio, VigenciaRepository vigenciaRepository) {
		return (args) -> {
			
			LocalDate inicio = new LocalDate();
			LocalDate fim = new LocalDate().plusDays(3);

			Vigencia vigencia = new Vigencia(inicio.toDate(), fim.toDate());
			Campanha campanha = new Campanha(1L, vigencia);

			vigenciaRepository.save(vigencia);
			campanhaRepositorio.save(campanha);

		};
	}
}
