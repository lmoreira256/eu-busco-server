package br.com.eubusco.server.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;

public class ListUtil {

	private Object origem;

	public ListUtil() {

	}

	public static <T> Boolean isNullOrEmpty(List<T> list) {

		if (list != null && (list.size() > IntegerUtil.ZERO)) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}

	public static ListUtil from(Object origem) {

		ListUtil listUtil = new ListUtil();
		listUtil.setOrigem(origem);

		return listUtil;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(Class<T> returnType) {
		T[] array = null;
		List<?> list = null;
		if (this.getOrigem() instanceof Collection<?>) {
			list = (List<?>) this.getOrigem();
		} else {
			return array;
		}
		array = (T[]) Array.newInstance(returnType, list.size());
		return list.toArray(array);
	}

	public Object getOrigem() {
		return origem;
	}

	public void setOrigem(Object origem) {
		this.origem = origem;
	}

}
