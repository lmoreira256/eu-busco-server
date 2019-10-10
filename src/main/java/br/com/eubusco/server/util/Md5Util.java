package br.com.eubusco.server.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
	public static String gerarMd5(String frase) {

		if (StringUtil.isNullOrEmpty(frase)) {
			return StringUtil.STRING_VAZIA;
		}

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(frase.getBytes());
			byte[] hashMd5 = md.digest();
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < hashMd5.length; i++) {
				int parteAlta = ((hashMd5[i] >> 4) & 0xf) << 4;
				int parteBaixa = hashMd5[i] & 0xf;
				if (parteAlta == 0)
					s.append('0');
				s.append(Integer.toHexString(parteAlta | parteBaixa));
			}
			return s.toString();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return StringUtil.STRING_VAZIA;

	}
}
