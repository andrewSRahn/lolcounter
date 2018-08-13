package com.lolpick.lolcounter.dao;

import java.util.Set;

import com.lolpick.lolcounter.entity.Tip;

public interface TipDao {
	public boolean create(Set<Tip> tips);
}
