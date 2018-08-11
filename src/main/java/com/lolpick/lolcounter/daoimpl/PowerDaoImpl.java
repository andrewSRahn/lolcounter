package com.lolpick.lolcounter.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.PowerDao;
import com.lolpick.lolcounter.entity.Power;
import com.lolpick.lolcounter.hibernate.HibernateUtil;

public class PowerDaoImpl implements PowerDao{

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
			transaction.rollback();
			e.printStackTrace();
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
			return session.createQuery("from Page where name like :name and relation like :relation", Power.class)
					.setParameter("name", name)
					.setParameter("relation", relation)
					.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
}