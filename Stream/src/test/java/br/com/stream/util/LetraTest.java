package br.com.stream.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class LetraTest {

	@Test
	public void deveEncontrarUmaStringNoArray() {
		assertEquals("e", Letra.encontra("e", Letra.vogais));
	}

	public void deveRetornarUmaExcecaoSeNaoEncontrarUmaStringNoArray() {
		assertEquals(null, Letra.encontra("c", Letra.vogais));
	}

	@Test
	public void deveTrasformaUmTextoEmArray() {
		assertTrue(Letra.retornarArray("texto").length > 1);
	}

	@Test
	public void deveRetornarAStringPrescedente() {
		assertEquals("c", Letra.retornarPrescedente("a", "cafe"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void deveRetornarUmaExcecaoSeNaoHouverPrescedente() {
		assertEquals("c", Letra.retornarPrescedente("c", "cafe"));
	}

	@Test
	public void deveEncontrarPrimeiraLetraQueNaoSeRepeteEmUmArray() {
		assertEquals("e", Letra.encontrarSemRepeticao("e", "eaabcd".split("")));
	}

}