package br.com.stream;

import br.com.stream.util.Letra;

public class StreaImpl implements Stream {

	private int indice;
	private String[] array;
	private String texto;

	public StreaImpl(String texto) {
		if (texto == null || texto.equals(""))
			throw new IllegalArgumentException("Conteudo precisa ser informado");
		this.texto = texto;
		this.array = texto.split("");
		this.indice = 0;
	}

	@Override
	public char getNext() {
		String letra = processar();
		char response = letra.length() == 1 ? letra.charAt(0) : 0;
		indice = texto.indexOf(letra);
		return response;
	}

	@Override
	public boolean hasNext() {
		if ((indice + 1) >= array.length || array[indice] == null)
			return false;
		return true;

	}

	private String processar() {

		String letraUnicaEmTexto = "";
		String letraPrescedente = "";

		for (int i = (indice + 1); i < array.length; i++) {

			String letra = array[i];

			if (Letra.existir(letra, Letra.vogais)) {

				letraUnicaEmTexto = Letra.encontrarSemRepeticao(letra, array);

				if (!letraUnicaEmTexto.equals("")) {
					letraPrescedente = Letra.retornarPrescedente(letraUnicaEmTexto, this.texto);
					if (Letra.existir(letraPrescedente, Letra.consoantes)) {
						letraPrescedente = Letra.retornarPrescedente(letraPrescedente, this.texto);
						if (Letra.existir(letraPrescedente, Letra.vogais)) {
							return letraUnicaEmTexto;
						}
					}

				}
			}
		}

		return letraUnicaEmTexto;
	}

}
