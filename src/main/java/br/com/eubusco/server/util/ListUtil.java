package br.com.eubusco.server.util;

import java.util.List;

public class ListUtil {

	public ListUtil() {

	}

	public static <T> Boolean isNullOrEmpty(List<T> list) {

		if (list != null && (list.size() > IntegerUtil.ZERO)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;

	}

}
