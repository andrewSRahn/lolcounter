package com.lolpick.lolcounter.dao;

import java.util.List;

import com.lolpick.lolcounter.entity.Champion;

public interface ChampionDao {
	public boolean createChampions(List<Champion> champions);
	public List<Champion> readChampions();
}
