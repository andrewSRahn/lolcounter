package com.lolpick.lolcounter.daoimpl;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.RoleDao;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.hibernate.HibernateUtil;

public class RoleDaoImpl implements RoleDao{

	@Override
	public boolean create(Set<Role> roles) {
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
	public boolean create(Set<Role> roles, String champion) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Champion champ = session.createQuery("from Champion where name like :name", Champion.class)
					.setParameter("name", champion)
					.getSingleResult();

			for (Role role: roles) {
				role.getChampions().add(champ);
				session.saveOrUpdate(role);
			}
			
			champ.setRoles(roles);
			
			session.saveOrUpdate(champ);
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
	public Role read(String role) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Role where champion_role=:r", Role.class)
					.setParameter("r", role)
					.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
}
