package br.com.eubusco.server.resources;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import br.com.eubusco.server.util.StringUtil;

public class ManageProperties {
	private static ResourceBundle bundle;

	private ManageProperties() {

	}

	public static String getPropertyUsuario(String property) {
		bundle = ResourceBundle.getBundle("Mensagem");
		return getProperty(bundle, property);

	}

	private static String getProperty(ResourceBundle bundle, String property) {
		try {
			return new String(bundle.getString(property).getBytes("ISO-8859-1"), "UTF-8");
		} catch (Exception e) {
			Logger.getLogger(Resource.class.getName())
					.severe("Chave da propriedade: " + property + " não esta configurada.");
		}
		return StringUtil.STRING_VAZIA;
	}

}
