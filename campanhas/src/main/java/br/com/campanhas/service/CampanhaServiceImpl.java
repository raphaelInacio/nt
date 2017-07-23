package br.com.campanhas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.campanhas.domain.Campanha;
import br.com.campanhas.repository.CampanhaRepository;

@Service
public class CampanhaServiceImpl implements CampanhaService {

	@Autowired
	private CampanhaRepository campanhaRepository;

	private VigenciaService vigenciaService;
	
	@Autowired
	public CampanhaServiceImpl(VigenciaServiceImpl vigenciaServiceImpl) {
		this.vigenciaService = vigenciaServiceImpl;
	}

	@Override
	public List<Campanha> listar() {
		return campanhaRepository.findAll();
	}

	@Override
	public Campanha salvar(Campanha campanha) {
		vigenciaService.verificarVigenciaExistentes(campanha.getVigencia());
		vigenciaService.save(campanha.getVigencia());
		return campanhaRepository.save(campanha);
	}

	@Override
	public Campanha atualizar(Campanha campanha) {
		vigenciaService.verificarVigenciaExistentes(campanha.getVigencia());
		vigenciaService.save(campanha.getVigencia());
		return campanhaRepository.save(campanha);
	}

	@Override
	public void deletar(Long id) {
		campanhaRepository.delete(id);
	}

	@Override
	public Campanha buscarPorId(Long id) {
		return campanhaRepository.findOne(id);
	}

}
