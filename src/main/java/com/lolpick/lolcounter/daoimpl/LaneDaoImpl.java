package com.lolpick.lolcounter.daoimpl;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.dao.LaneDao;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.utility.HibernateUtil;

public class LaneDaoImpl implements LaneDao{
	private Logger logger = LoggerFactory.getLogger(LaneDaoImpl.class);
	
	@Override
	public boolean create(Set<Lane> lanes) {
		for(Lane lane: lanes)
			if(!createLane(lane))
				return false;
		
		return true;
	}
	
	private boolean createLane(Lane lane) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(lane);
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
	public Lane read(String lane) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			return session.createQuery("from Lane where champion_lane=:lane", Lane.class)
					.setParameter("lane", lane)
					.getSingleResult();
		} catch(Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		
		return null;
	}
	
	@Override
	public boolean update(Set<Lane> lanes, Champion champion) {
		for(Lane lane: lanes) 
			if(!update(lane, champion))
				return false;
		
		return true;
	}
	
	private boolean update(Lane current, Champion champion) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Lane stale = session.get(Lane.class, current.getId());
			List<Champion> champions = current.getChampions();
			champions.add(champion);
			stale.setChampions(champions);
			
			session.merge(stale);
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
