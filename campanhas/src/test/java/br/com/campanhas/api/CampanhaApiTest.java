package br.com.campanhas.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.campanhas.CampanhaApplication;
import br.com.campanhas.domain.Campanha;
import br.com.campanhas.domain.Vigencia;
 


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CampanhaApplication.class)
@WebIntegrationTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CampanhaApiTest {
	
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	ObjectMapper objectMapper;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}
	
	public String dateToString(Date data){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(data);
	}
	
	@Test
	public void testeA_deveRetornarListaDeCampanhas() throws Exception {

		mvc.perform(get("/api/campanhas"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$").isArray())
			.andExpect(jsonPath("$").isNotEmpty())
			.andExpect(jsonPath("$[0].id").value(1))
			.andExpect(jsonPath("$[0].idTimeDoCoracao").value(1))
			.andExpect(jsonPath("$[0].vigencia.inicio").value(dateToString(new LocalDate().toDate())))
			.andExpect(jsonPath("$[0].vigencia.fim").value(dateToString(new LocalDate().plusDays(3).toDate())));
	}
	
	@Test
	public void testeB_deveRetornarCampanhaPorId() throws Exception {
		
		mvc.perform(get("/api/campanhas/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.idTimeDoCoracao").value(1))
			.andExpect(jsonPath("$.vigencia.inicio").value(dateToString(new LocalDate().toDate())))
			.andExpect(jsonPath("$.vigencia.fim").value(dateToString(new LocalDate().plusDays(3).toDate())));
	}
	
	@Test
	public void testeC_deveAcrescentarUmDiaEmCampanhaJaExistente() throws Exception {
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(3);
		
		Vigencia vigencia = new Vigencia( inicio.toDate(), fim.toDate());
		Campanha campanha = new Campanha(1L, vigencia);
		
		mvc.perform(
		post("/api/campanhas")
		.contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(objectMapper.writeValueAsString(campanha)))
		.andExpect(status().isCreated());
		
		mvc.perform(get("/api/campanhas"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isNotEmpty())
		.andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].idTimeDoCoracao").value(1))
		.andExpect(jsonPath("$[0].vigencia.inicio").value(dateToString(inicio.toDate())))
		.andExpect(jsonPath("$[0].vigencia.fim").value(dateToString(fim.plusDays(1).toDate())));
	}
	
	@Test
	public void testeD_deveAcrescentarUmDiaEmMaisDeUmaCampanha() throws Exception {
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(3);
		
		Vigencia vigencia = new Vigencia( inicio.toDate(), fim.toDate());
		Campanha campanha = new Campanha(1L, vigencia);
		
		mvc.perform(
		post("/api/campanhas")
		.contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(objectMapper.writeValueAsString(campanha)))
		.andExpect(status().isCreated());
		
		mvc.perform(get("/api/campanhas"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$").isNotEmpty())
		.andExpect(jsonPath("$[0].id").value(1))
		.andExpect(jsonPath("$[0].idTimeDoCoracao").value(1))
		.andExpect(jsonPath("$[0].vigencia.inicio").value(dateToString(inicio.toDate())))
		.andExpect(jsonPath("$[0].vigencia.fim").value(dateToString(fim.plusDays(2).toDate())));
	}
	
	@Test
	public void testeE_deveRemoverUmaCampanha() throws Exception {
		mvc.perform(delete("/api/campanhas/2")).andExpect(status().isOk());
	}
	
	@Test
	public void testeF_atualizarUmaCampanha() throws Exception {
		
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate().plusDays(3);
		
		Vigencia vigencia = new Vigencia( inicio.toDate(), fim.toDate());
		Campanha campanha = new Campanha(2L, vigencia);
		
		mvc.perform(
		put("/api/campanhas/1")
		.contentType(MediaType.APPLICATION_JSON_UTF8)
		.content(objectMapper.writeValueAsString(campanha)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.idTimeDoCoracao").value(2))
		.andExpect(jsonPath("$.vigencia.inicio").value(dateToString(new LocalDate().toDate())))
		.andExpect(jsonPath("$.vigencia.fim").value(dateToString(new LocalDate().plusDays(3).toDate())));
	}
	
}
