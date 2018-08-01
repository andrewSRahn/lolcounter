package com.lolpick.lolcounter.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.RoleDao;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.hibernate.HibernateUtil;

public class RoleDaoImpl implements RoleDao{

	@Override
	public boolean create(List<Role> roles) {
		for(Role role: roles)
			if(!createRole(role))
				return false;
		
		return true;
	}
	
	private boolean createRole(Role role) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(role);
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
	public List<Role> readChampion(String champion) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			String query = "select r.role_id, r.champion_id "
					+ "from Role r, Champion_role cr, Champion ch "
					+ "where r.role_id=cr.role_id "
					+ "and cr.champion_id=ch.champion_id"
					+ "and ch.name=:name";
			return session.createQuery(query, Role.class)
					.setParameter("name", champion)
					.getResultList();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
}
