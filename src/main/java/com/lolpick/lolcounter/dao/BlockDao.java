package com.lolpick.lolcounter.dao;

import com.lolpick.lolcounter.entity.Vote;

public interface BlockDao {
	public boolean create(Vote block);
}
