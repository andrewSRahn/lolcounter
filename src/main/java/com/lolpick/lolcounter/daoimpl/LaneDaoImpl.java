package com.lolpick.lolcounter.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.LaneDao;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.hibernate.HibernateUtil;

public class LaneDaoImpl implements LaneDao{

	@Override
	public boolean create(List<Lane> lanes) {
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
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return false;
	}

	@Override
	public boolean create(List<Lane> lanes, String champion) {
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
			
			Champion champ = session.createQuery("from Champion where name like :name", Champion.class)
					.setParameter("name", champion)
					.getSingleResult();
			
			for(Lane lane: lanes) {
				lane.getChampions().add(champ);
				session.saveOrUpdate(lane);
			}
			
			champ.setLanes(lanes);
			
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
	public Lane read(String lane) {
		Session session = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			
			return session.createQuery("from Lane where champion_lane=:lane", Lane.class)
					.setParameter("lane", lane)
					.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return null;
	}
}
