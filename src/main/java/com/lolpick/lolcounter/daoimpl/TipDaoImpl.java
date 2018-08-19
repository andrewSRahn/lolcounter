package com.lolpick.lolcounter.daoimpl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.TipDao;
import com.lolpick.lolcounter.entity.Tip;
import com.lolpick.lolcounter.utility.HibernateUtil;

public class TipDaoImpl implements TipDao{
	
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
			e.printStackTrace();
			transaction.rollback();
		} finally {
			session.close();
		}
		
		return false;
	}
	
}
