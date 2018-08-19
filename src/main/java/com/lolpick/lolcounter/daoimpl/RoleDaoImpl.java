package com.lolpick.lolcounter.daoimpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.RoleDao;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.utility.HibernateUtil;

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
	public Role read(String role) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Role where champion_role=:role", Role.class)
					.setParameter("role", role)
					.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
	
	@Override
	public boolean update(Set<Role> roles, Champion champion) {
		for(Role role: roles) {
			if(!update(role, champion))
				return false;
		}
		
		return true;
	}
	
	private boolean update(Role current, Champion champion) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Role stale = session.get(Role.class, current.getId());
			
			List<Champion> champions = current.getChampions();
			champions.add(champion);
			stale.setChampions(champions);
			
			session.merge(current);
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
