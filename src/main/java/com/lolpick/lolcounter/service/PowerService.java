package com.lolpick.lolcounter.service;

import com.lolpick.lolcounter.daoimpl.PowerDaoImpl;
import com.lolpick.lolcounter.entity.Power;

public class PowerService {
	private static PowerDaoImpl daoimpl = new PowerDaoImpl();

	public static Power read(String name, String relation) {
		return daoimpl.readPage(name, relation);
	}

	public static boolean create(Power page) {
		return daoimpl.createPage(page);
	}
}