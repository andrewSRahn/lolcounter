package com.lolpick.lolcounter.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.RoleDao;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.hibernate.HibernateUtil;
import com.lolpick.lolcounter.service.ChampionService;

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
	public boolean create(List<Role> roles, String champion) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Champion champ = ChampionService.readChampion(champion);
			champ.setRoles(roles);

			for (Role role: roles) {
				role.getChampions().add(champ);
				session.saveOrUpdate(role);
			}
			
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
