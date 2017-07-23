package br.com.campanhas.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campanhas.domain.Vigencia;
import br.com.campanhas.repository.VigenciaRepository;

@Service
public class VigenciaServiceImpl implements VigenciaService {

	@Autowired
	private VigenciaRepository vigenciaRepository;

	@Override
	public void verificarVigenciaExistentes(Vigencia vigencia) {

		List<Vigencia> vigenciasExistentes = vigenciaRepository.findAllByFim(vigencia.getFim());

		if (!vigenciasExistentes.isEmpty()) {

			List<Vigencia> novasVigencias = new ArrayList<Vigencia>();
			int numeroDeDias = 1;
			
			for (Iterator<Vigencia> iterator = vigenciasExistentes.iterator(); iterator.hasNext();) {
				
				Vigencia vigenciaAlterada = iterator.next();
				vigenciaAlterada.setFim(acrescentarDiasEmUmaVigencia(vigenciaAlterada.getFim(), numeroDeDias));
				novasVigencias.add(vigenciaAlterada);
				verificarVigenciaExistentes(vigenciaAlterada);
				numeroDeDias++;
				
			}

			vigenciaRepository.save(novasVigencias);

		}
	}

	private Date acrescentarDiasEmUmaVigencia(Date fimDaVigencia, int numeroDeDias) {
		LocalDate novaData = new LocalDate(fimDaVigencia);
		return novaData.plusDays(numeroDeDias).toDate();
	}

	@Override
	public Vigencia save(Vigencia vigencia) {
		return vigenciaRepository.save(vigencia);
	}

}
