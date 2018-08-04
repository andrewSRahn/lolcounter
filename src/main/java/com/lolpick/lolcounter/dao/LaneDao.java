package com.lolpick.lolcounter.dao;

import java.util.List;

import com.lolpick.lolcounter.entity.Lane;

public interface LaneDao {
	public boolean create(List<Lane> lanes);
	public boolean create(List<Lane> lanes, String champion);
	public Lane read(String lane);
}
