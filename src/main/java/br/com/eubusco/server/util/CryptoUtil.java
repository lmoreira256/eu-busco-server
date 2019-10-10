package br.com.eubusco.server.util;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class CryptoUtil {

	protected final static String SECRET_KEY = "@!#V$%Fz$#!@";
	private final static Charset CHARSET = Charset.forName("UTF-8");
	private static SecretKeySpec keySpec = null;
	private static Cipher cipher;

	static {

		try {
			keySpec = new SecretKeySpec(SECRET_KEY.getBytes(CHARSET), "Blowfish");

			cipher = Cipher.getInstance("Blowfish");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String toMD5(String textoConverter) {
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.update(textoConverter.getBytes(), 0, textoConverter.length());
			return new BigInteger(1, m.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

	}

	public static String toBlowfish(String converter) {
		try {

			cipher.init(Cipher.ENCRYPT_MODE, keySpec);

			return Base64.getEncoder().encodeToString(cipher.doFinal(converter.getBytes(CHARSET)));

		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			JOptionPane.showMessageDialog(null, "ERRO CRIPTOGRAFANDO ARQUIVO");
			return null;
		}

	}

	public static String fromBlowfish(String blowFish) {
		try {

			cipher.init(Cipher.DECRYPT_MODE, keySpec);

			return new String(cipher.doFinal(Base64.getDecoder().decode(blowFish.getBytes(CHARSET))));

		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			JOptionPane.showMessageDialog(null, "ERRO DESCRIPTOGRAFANDO ARQUIVO");
			return null;
		}

	}
}
