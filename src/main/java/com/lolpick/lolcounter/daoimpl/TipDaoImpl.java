package com.lolpick.lolcounter.daoimpl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.dao.TipDao;
import com.lolpick.lolcounter.entity.Tip;
import com.lolpick.lolcounter.utility.HibernateUtil;

public class TipDaoImpl implements TipDao{
	private Logger logger = LoggerFactory.getLogger(TipDaoImpl.class);
	
	@Override
	public boolean create(Set<Tip> tips) {
		for(Tip tip: tips)
			if(!create(tip))
				return false;
		
		return true;
	}
	
	private boolean create(Tip tip) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(tip);
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
	
}
