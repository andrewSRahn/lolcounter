package com.lolpick.lolcounter.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.BlockDao;
import com.lolpick.lolcounter.entity.Block;
import com.lolpick.lolcounter.hibernate.HibernateUtil;

public class BlockDaoImpl implements BlockDao{

	@Override
	public boolean create(Block block) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(block);
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
}
