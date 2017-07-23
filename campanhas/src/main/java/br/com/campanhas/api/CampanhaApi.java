package br.com.campanhas.api;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.campanhas.domain.Campanha;
import br.com.campanhas.service.CampanhaService;
import br.com.campanhas.service.CampanhaServiceImpl;

@RestController
@RequestMapping("/api/campanhas")
public class CampanhaApi {

	private CampanhaService campanhaNegocio;

	@Autowired
	public CampanhaApi(CampanhaServiceImpl campanhaNegocioImpl) {
		this.campanhaNegocio = campanhaNegocioImpl;
	}

	@RequestMapping(method = GET)
	public ResponseEntity<Iterable<Campanha>> listar() {

		List<Campanha> campanhas = campanhaNegocio.listar();

		if (campanhas == null || campanhas.isEmpty()) {
			return new ResponseEntity<Iterable<Campanha>>(campanhas, HttpStatus.NO_CONTENT);

		}

		return new ResponseEntity<Iterable<Campanha>>(campanhas, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<Campanha> buscatPorId(@PathVariable Long id) {

		Campanha campanha = campanhaNegocio.buscarPorId(id);

		if (campanha == null) {
			return new ResponseEntity<Campanha>(campanha, HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Campanha>(campanha, HttpStatus.OK);
	}

	@RequestMapping(method = POST)
	public ResponseEntity<Campanha> salvar(@RequestBody Campanha campanha) {
		return new ResponseEntity<Campanha>(campanhaNegocio.salvar(campanha), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Campanha> atualizar(@PathVariable Long id, @RequestBody Campanha campanha) {

		Campanha campanhaParaSerAtualizada = campanhaNegocio.buscarPorId(id);

		if (campanhaParaSerAtualizada == null) {
			return new ResponseEntity<Campanha>(campanha, HttpStatus.NOT_FOUND);
		}
		
		campanha.setId(campanhaParaSerAtualizada.getId());
		
		return new ResponseEntity<Campanha>(campanhaNegocio.atualizar(campanha), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		
		Campanha campanhaParaSerDeletada = campanhaNegocio.buscarPorId(id);

		if (campanhaParaSerDeletada == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		
		campanhaNegocio.deletar(id);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
