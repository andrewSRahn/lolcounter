package com.lolpick.lolcounter.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.dao.VoteDao;
import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.utility.HibernateUtil;

public class VoteDaoImpl implements VoteDao{
	private Logger logger = LoggerFactory.getLogger(VoteDaoImpl.class);

	@Override
	public boolean create(Vote block) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(block);
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
