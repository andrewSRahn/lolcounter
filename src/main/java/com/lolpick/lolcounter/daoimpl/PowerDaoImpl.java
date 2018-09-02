package com.lolpick.lolcounter.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.dao.PowerDao;
import com.lolpick.lolcounter.entity.Power;
import com.lolpick.lolcounter.utility.HibernateUtil;

public class PowerDaoImpl implements PowerDao{
	private Logger logger = LoggerFactory.getLogger(PowerDaoImpl.class);

	@Override
	public boolean createPage(Power page) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(page);
			transaction.commit();
			return true;
		} catch(Exception e) {
			logger.error(e.getMessage());
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return false;
	}
	
	@Override
	public Power readPage(String name, String relation) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from page where name like :name and relation like :relation", Power.class)
					.setParameter("name", name)
					.setParameter("relation", relation)
					.getSingleResult();
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		
		return null;
	}
}
