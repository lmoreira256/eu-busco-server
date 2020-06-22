package br.com.eubusco.server.enumerator;

import java.util.HashMap;
import java.util.Map;

public enum TipoUsuarioEnum {

	ADMIN(1), CLIENTE(2), ENTREGADOR(3);

	private static final Map<Integer, TipoUsuarioEnum> TYPES_BY_VALUE = new HashMap<>();

	static {
		for (TipoUsuarioEnum type : TipoUsuarioEnum.values()) {
			TYPES_BY_VALUE.put(type.integerValue, type);
		}
	}

	private final Integer integerValue;

	private TipoUsuarioEnum(final Integer s) {
		integerValue = s;
	}

	public Integer toInteger() {
		return integerValue;
	}

	public Boolean isEquals(Integer s) {
		return integerValue == s;
	}

	@Override
	public String toString() {
		return integerValue.toString();
	}

	public static TipoUsuarioEnum forValue(Integer value) {
		return TYPES_BY_VALUE.get(value);
	}
}
