package br.com.stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StreaImplTest {

	@Test
	public void testStreaImpl() {
		Stream stream = new StreaImpl("abc");
		assertNotNull(stream);
	}

	@Test
	public void deveRetornaAsTresOcorrenciasDeAcordoComAsRegras() {
		Stream stream = new StreaImpl("afeaccafuafo");
		assertEquals('e', stream.getNext());
		assertEquals('u', stream.getNext());
		assertEquals('o', stream.getNext());
	}

	@Test
	public void deveRetornarTrueQuandoAindaHouverElementos() {
		Stream stream = new StreaImpl("abc");
		assertTrue(stream.hasNext());
	}
	
	@Test
	public void deveRetornarFalseQuandoChegarNoUltimoItemDaLista() {
		Stream stream = new StreaImpl("afeaccafuafo");
		stream.getNext();
		stream.getNext();
		stream.getNext();
		assertFalse(stream.hasNext());
	}

}
