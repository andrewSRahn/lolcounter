package com.lolpick.lolcounter.dao;

import java.util.Set;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;

public interface LaneDao {
	public boolean create(Set<Lane> lanes);
	public Lane read(String lane);
	public boolean update(Set<Lane> lanes, Champion champion);
}
