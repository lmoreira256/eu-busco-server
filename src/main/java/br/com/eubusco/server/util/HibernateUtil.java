package br.com.eubusco.server.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	private static SessionFactory factory;
	static {
	}

	public static Session getSession() {
		return factory.openSession();
	}
}