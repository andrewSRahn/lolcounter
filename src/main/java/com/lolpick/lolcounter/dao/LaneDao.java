package com.lolpick.lolcounter.dao;

import java.util.Set;

import com.lolpick.lolcounter.entity.Lane;

public interface LaneDao {
	public boolean create(Set<Lane> lanes);
	public boolean create(Set<Lane> lanes, String champion);
	public Lane read(String lane);
}
