package com.lolpick.lolcounter.service;

import com.lolpick.lolcounter.daoimpl.PageDaoImpl;
import com.lolpick.lolcounter.entity.Page;

public class PageService {
	private static PageDaoImpl daoimpl = new PageDaoImpl();

	public static Page read(String name, String relation) {
		return daoimpl.readPage(name, relation);
	}

	public static boolean create(Page page) {
		return daoimpl.createPage(page);
	}
}