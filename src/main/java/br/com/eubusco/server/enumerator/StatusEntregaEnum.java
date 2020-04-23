package br.com.eubusco.server.enumerator;

import java.util.HashMap;
import java.util.Map;

public enum StatusEntregaEnum {

	ABERTA(1), ANDAMENTO(2), FINALIZADA(3);

	private static final Map<Integer, StatusEntregaEnum> TYPES_BY_VALUE = new HashMap<>();

	static {
		for (StatusEntregaEnum type : StatusEntregaEnum.values()) {
			TYPES_BY_VALUE.put(type.integerValue, type);
		}
	}

	private final Integer integerValue;

	private StatusEntregaEnum(final Integer s) {
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

	public static StatusEntregaEnum forValue(Integer value) {
		return TYPES_BY_VALUE.get(value);
	}
}
