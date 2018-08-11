package com.lolpick.lolcounter.dao;

import com.lolpick.lolcounter.entity.Relation;

public interface PageDao {
	public boolean createPage(Relation page);
	public Relation readPage(String name, String relation);
}
