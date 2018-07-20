package com.lolpick.lolcounter.dao;

import com.lolpick.lolcounter.entity.Page;

public interface PageDao {
	public boolean createPage(Page page);
	public Page readPage(String name, String relation);
}
