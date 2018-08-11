package com.lolpick.lolcounter.service;

import com.lolpick.lolcounter.daoimpl.PageDaoImpl;
import com.lolpick.lolcounter.entity.Relation;

public class PageService {
	private static PageDaoImpl daoimpl = new PageDaoImpl();

	public static Relation read(String name, String relation) {
		return daoimpl.readPage(name, relation);
	}

	public static boolean create(Relation page) {
		return daoimpl.createPage(page);
	}
}