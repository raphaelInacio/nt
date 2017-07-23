package br.com.stream.util;

public class Letra {

	public static final String[] vogais = { "a", "e", "i", "o", "u" };
	public static final String[] consoantes = { "c", "d", "f" };

	public static String encontra(String letra, String emLista[]) {
		for (String item : emLista)
			if (item.equals(letra))
				return letra;
		return null;
	}
	
	public static Boolean existir(String letra, String emLista[]) {
		for (String item : emLista)
			if (item.equals(letra))
				return true;
		return false;
	}

	public static String encontrarPrescedente(String deLetra, String[] emLista, String paraTexto) {
		String precedente = retornarPrescedente(deLetra, paraTexto);
		return encontra(precedente, emLista);
	}

	public static String encontrarSemRepeticao(String item, String[] emLista) {
		String letraEncontrada  = "";
		int cont = 0;
		for (String letra : emLista) {
			if (item.equals(letra)) {
				letraEncontrada = item;
				cont++;
				if (cont > 1) {
					letraEncontrada = "";
					cont = 0;
					break;
				}
			}

		}
		return letraEncontrada;
	}

	public static String retornarPrescedente(String deLetra, String emTexto) {
		int index = emTexto.indexOf(deLetra);
		if (index == 0)
			throw new IllegalArgumentException("Não existe prescedente");
		return retornarArray(emTexto)[index - 1];
	}

	public static String[] retornarArray(String deTexto) {
		if (deTexto == null || deTexto.equals(""))
			throw new IllegalArgumentException("Não permitido valores em branco ou nulos");
		return deTexto.split("");
	}
}