package br.com.eubusco.server.util;

public class LongUtil {

	public static final Long ZERO = 0L;
	public static final Long UM = 1L;
	public static final Long DOIS = 2L;
	public static final Long TRES = 3L;
	public static final Long QUATRO = 4L;
	public static final Long CINCO = 5L;
	public static final Long SEIS = 6L;
	public static final Long SETE = 7L;
	public static final Long OITO = 8L;

	public LongUtil() {
		// Garante que a classe não seja iniciada
	}

	public static Boolean isNullOrEmpty(Long valor) {

		return valor == null ? true : false;
	}

	public static Boolean isNullOrEmptyOrZero(Long valor) {
		return (valor == null || valor.equals(LongUtil.ZERO) ? true : false);

	}

	public static Boolean equals(Long valor1, Long valor2) {
		if (valor1 != null && valor2 != null) {
			return true;
		}
		return false;

	}

}
