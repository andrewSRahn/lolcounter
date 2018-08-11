package com.lolpick.lolcounter.dao;

import com.lolpick.lolcounter.entity.Power;

public interface PowerDao {
	public boolean createPage(Power page);
	public Power readPage(String name, String relation);
}
