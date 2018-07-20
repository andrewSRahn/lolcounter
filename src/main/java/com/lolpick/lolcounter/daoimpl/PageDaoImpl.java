package com.lolpick.lolcounter.daoimpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.PageDao;
import com.lolpick.lolcounter.entity.Page;
import com.lolpick.lolcounter.hibernate.HibernateUtil;

public class PageDaoImpl implements PageDao{

	@Override
	public boolean createPage(Page page) {
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
	public Page readPage(String name, String relation) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Page where name like :name and relation like :relation", Page.class)
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
