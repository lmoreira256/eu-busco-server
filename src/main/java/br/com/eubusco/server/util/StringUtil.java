package br.com.eubusco.server.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class StringUtil {

	private static Logger logger = Logger.getLogger(StringUtil.class.getName());

	public static final String STRING_VAZIA = "";
	public static final String SEPARADOR_BARRA = " / ";
	public static final String CODIGO_DA_EMPRESA = "CÓDIGO DA EMPRESA";
	public static final String CODIGO_DO_USUARIO = "CÓDIGO DO USUÁRIO";
	public static final String STATUS = "STATUS";
	public static final String DATA_INICIO = "DATA INICIAL";
	public static final String DATA_FIM = "DATA FINAL";
	public static final String VIRGULA = ",";
	public static final String ASPAS_SIMPLES = "'";
	public static final String ALIAS_QUERY = "query";
	public static final String STRING_ESPACO = " ";

	private StringUtil() {
		// Garante que a classe não seja instanciada.
	}

	/**
	 * Valida uma String para verificar se ela é vazia.
	 *
	 * @param valor
	 *            Valor String à ser analisado.
	 * @return Retorna <b>true</b> se o valor informado for nulo ou vazio absoluto.
	 *         <br>
	 *         Retorna <b>false</b> se o valor informado não for nulo ou possui pelo
	 *         menos 1 caracter.
	 *         <p/>
	 *         Obs.: Caso a variável possua espaços nas extremidades, esses serão
	 *         removídos para a análise.
	 */
	public static Boolean isVazio(String valor) {

		if (valor == null || StringUtil.STRING_VAZIA.equals(valor.trim())) {

			return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}

	/**
	 * Remove aspas duplas das strings.
	 *
	 * @param valor
	 *            Valor String à ser analisado.
	 * @return Retorna <b>string</b> sem aspas.
	 *         <p/>
	 */
	public static StringBuilder removeCaracteresInvalidos(StringBuilder stringBuilder) {
		String valor = "";
		if (stringBuilder != null) {
			valor = stringBuilder.toString().replaceAll("[\"\']", "");
		}
		StringBuilder sb = new StringBuilder();
		return sb.append(valor);
	}

	public static String removerNaoNumeros(String cnpj) {
		if (!isNullOrEmpty(cnpj)) {
			return cnpj.replaceAll("[^0-9]", "");
		}
		return "";
	}

	/**
	 * Remove espaços em brancos de Strings
	 *
	 * @param valor
	 *            Valor String à ser analisado.
	 * @return Retorna <b>string</b> sem espaços.
	 *         <p/>
	 */
	public static String removeEspacos(String valor) {
		if (valor == null) {
			return valor;
		} else {
			return valor.trim();
		}
	}

	/**
	 * Remove espaços em brancos de Strings e tranforma para minusculo
	 *
	 * @param valor
	 *            Valor String à ser analisado.
	 * @return Retorna <b>string</b> sem espaços em brancos e em minusculo.
	 *         <p/>
	 */
	public static String transformaMininusculo(String valor) {
		String retorno = removeEspacos(valor);
		if (retorno != null) {
			return retorno.toLowerCase();
		} else {
			return STRING_VAZIA;
		}
	}

	/**
	 * Conta quantidade de caracteres absoluto
	 *
	 * @param valor
	 *            Valor String à ser analisado.
	 * @return Retorna <b>Integer</b> quantidade de caracteres absoluto
	 *         <p/>
	 */
	public static Integer contaCaracteres(String valor) {
		String retorno = removeEspacos(valor);
		if (retorno != null) {
			return retorno.length();
		} else {
			return 0;
		}
	}

	/**
	 * Verificar se a String for nula retornar vazio
	 *
	 * @param valor
	 *            : Valor String à ser analisado.
	 * @return retorna: String
	 */
	public static String tratarNulo(String valor) {
		if (valor == null) {
			return STRING_VAZIA;
		}
		return valor;
	}

	public static boolean isNumericList(String valor) {
		if (valor.contains(",")) {
			String[] valores = valor.split(",");
			for (String item : valores) {
				if (!isNumeric(item)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isNumeric(String atributo) {
		try {
			Double.valueOf(atributo);
			return true;
		} catch (Exception e) {
			logger.fine("O valor não é um numérico" + e.getMessage());
			return false;
		}
	}

	public static String listToString(List<?> value) {
		String list = value.toString();
		list = list.replace("[", "");
		list = list.replace("]", "");
		return list;
	}

	public static String primeiraMaiscula(String palavra) {
		return palavra.substring(0, 1).toUpperCase().concat(palavra.substring(1));
	}

	public static String primeiraMinuscula(String palavra) {
		return palavra.substring(0, 1).toLowerCase().concat(palavra.substring(1));
	}

	public static boolean isNullOrEmpty(String texto) {

		return IfNull.get(texto, STRING_VAZIA).equals(STRING_VAZIA);
	}

	/**
	 * Truncates trunca a string para o minimo do seu length ou para o valor
	 * informado.
	 **/
	public static String truncate(String s, int len) {
		if (s == null) {
			return null;
		}
		return s.substring(0, Math.min(len, s.length()));
	}

	/**
	 * Retorna uma lista dos caracteres que representam uma configuração. <br>
	 * Este tipo de configuração é comum na aplicação delphi, por exemplo "NL" é uma
	 * configuração onde existe dois caracters concatenados.
	 * 
	 * @param valor
	 * @return
	 */
	public static List<String> toCaractersList(String valor) {

		return Arrays.asList(valor.split(""));
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> toList(Class<T> returnType, String valor) {

		String replace = valor.replace("[", "");
		replace = replace.replace("]", "");
		replace = replace.replace("\"", "");
		String[] codigos = replace.toUpperCase().split(",");

		List<T> lista = new ArrayList<T>();
		for (String item : codigos) {
			lista.add((T) item);
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> listS1toList(Class<T> returnType, String valor) {

		String replace = valor.replace("<", "");
		replace = replace.replace(">", "");
		String[] codigos = replace.toUpperCase().split(",");

		List<T> lista = new ArrayList<T>();
		for (String item : codigos) {
			lista.add((T) item);
		}
		return lista;
	}

	public static String padLeft(String stringToPad, Integer padToLength) {
		String retValue = null;
		if (stringToPad.length() < padToLength) {
			retValue = String.format("%0" + (padToLength - stringToPad.length()) + "d%s", IntegerUtil.ZERO,
					stringToPad);
		} else {
			retValue = stringToPad;
		}
		return retValue;
	}

	public static String primeiraLetraMaiusculo(String palavra) {

		return palavra.substring(0, 1).toUpperCase().concat(palavra.substring(1).toLowerCase());

	}

	public static String trim(String string) {
		if (isNullOrEmpty(string)) {
			return STRING_VAZIA;
		}
		return string.trim();
	}

	public static Boolean isNumeric(Class<?> type, String valoPesquisa) {
		try {
			Constructor<?> constructor = type.getConstructor(String.class);
			constructor.newInstance(valoPesquisa);
			return Boolean.TRUE;

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | InstantiationException e) {
			return Boolean.FALSE;
		}
	}

	public static String replaceNaoNumeros(String valor) {
		return valor.replaceAll("[^0-9]", "");
	}

	public static String formataMoeda(BigDecimal valor, Integer casas, Boolean simbolo) {
		NumberFormat nf;
		if (simbolo) {
			nf = NumberFormat.getCurrencyInstance();
		} else {
			nf = NumberFormat.getNumberInstance();
		}
		nf.setMaximumFractionDigits(casas);
		nf.setMinimumFractionDigits(casas);

		return nf.format(valor);
	}

	public static String formataMoeda(BigDecimal valor) {
		return formataMoeda(valor, IntegerUtil.DOIS, Boolean.FALSE);
	}

	@SuppressWarnings("unchecked")
	/*
	 * Médodo que quebra o array de chave e valor que vem da tela e devolve uma
	 * lista de valores que pode ser convertida para o valor esperado
	 */
	public static <T> List<T> quebrarArrayCodigos(Class<T> retorno, String chaves) {
		List<T> valores = new ArrayList<>();

		String[] split = chaves.replace("<", "").replace(">", "").split(",");

		for (String codigo : split) {
			PropertyEditor editor = PropertyEditorManager.findEditor(retorno);
			editor.setAsText(codigo);
			valores.add((T) editor.getValue());
		}
		return valores;
	}

	public static String gerarSenha(Long quantidadeLetras) {

		char[] chart = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
				'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
				'Z' };
		char[] senha = new char[quantidadeLetras.intValue()];
		int chartLenght = chart.length;
		Random rdm = new Random();
		for (int x = 0; x < quantidadeLetras.intValue(); x++)
			senha[x] = chart[rdm.nextInt(chartLenght)];
		return new String(senha);
	}

}
