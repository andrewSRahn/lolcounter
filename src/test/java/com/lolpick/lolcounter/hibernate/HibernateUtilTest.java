package com.lolpick.lolcounter.hibernate;

import org.hibernate.SessionFactory;
import org.junit.Test;
import static org.junit.Assert.*;

public class HibernateUtilTest {

	@Test
	public void testGetSessionFactory() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		assertNotNull(sessionFactory);	
	}
}