package br.com.campanhas.service;

import br.com.campanhas.domain.Vigencia;

public interface VigenciaService {
	public void verificarVigenciaExistentes(Vigencia vigencia);
	public Vigencia save(Vigencia vigencia);
}
