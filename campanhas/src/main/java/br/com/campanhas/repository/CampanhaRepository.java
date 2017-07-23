package br.com.campanhas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.campanhas.domain.Campanha;
@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {

}
