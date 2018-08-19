package com.lolpick.lolcounter.utility;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		Properties properties = new Properties();
		Configuration configuration = new Configuration();
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
			configuration.addProperties(properties);
			return configuration.configure().buildSessionFactory();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void closeSession(Session session) {
		if(session != null)
			session.close();
	}
}
