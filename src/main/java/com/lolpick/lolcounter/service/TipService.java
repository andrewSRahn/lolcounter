package com.lolpick.lolcounter.service;

import java.util.Set;

import com.lolpick.lolcounter.daoimpl.TipDaoImpl;
import com.lolpick.lolcounter.entity.Tip;

public class TipService {
	private static TipDaoImpl dao = new TipDaoImpl();
	
	public static boolean create(Set<Tip> tips) {
		return dao.create(tips);
	}
}
