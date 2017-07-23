package br.com.campanhas.service;

import java.util.List;

import br.com.campanhas.domain.Campanha;

public interface CampanhaService {
	public List<Campanha> listar();
	public Campanha buscarPorId(Long id);
	public Campanha salvar(Campanha campanha);
	public Campanha atualizar(Campanha campanha);
	public void deletar(Long id);
}
