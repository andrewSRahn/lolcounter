package com.lolpick.lolcounter.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.dao.ChampionDao;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.utility.HibernateUtil;

public class ChampionDaoImpl implements ChampionDao {
	private Logger logger = LoggerFactory.getLogger(ChampionDaoImpl.class);
	
	@Override
	public boolean updateChampion(Champion champion) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Champion current = session.get(Champion.class, champion.getId());
			current.setLanes(champion.getLanes());
			current.setRoles(champion.getRoles());
			
			session.refresh(current);
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
	
	@Override 
	public boolean createChampions(List<Champion> champions){
		for(Champion champion: champions)
			if(!createChampion(champion))
				return false;

		return true;
	}
	
	@Override
	public boolean createChampion(Champion champion) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(champion);
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
	
	@Override
	public List<Champion> readChampions() {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			return session.createQuery("from Champion", Champion.class).getResultList();
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		
		return null;
	}
	
	@Override
	public Champion readChampion(String champion) {
		List<Champion> champions = readChampions();
		
		return champions.stream()
				.filter( c -> c.getName().equals(champion))
				.findFirst()
				.orElse(null);
	}
}
