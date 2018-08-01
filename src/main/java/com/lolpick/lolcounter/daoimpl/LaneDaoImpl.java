package com.lolpick.lolcounter.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.lolpick.lolcounter.dao.LaneDao;
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
	public List<Lane> readChampion(String champion) {
		// TODO Auto-generated method stub
		return null;
	}

}
