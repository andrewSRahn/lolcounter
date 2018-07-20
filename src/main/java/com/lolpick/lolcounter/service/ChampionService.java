package com.lolpick.lolcounter.service;

import java.util.List;

import com.lolpick.lolcounter.daoimpl.ChampionDaoImpl;
import com.lolpick.lolcounter.entity.Champion;

public class ChampionService {
	private static ChampionDaoImpl daoimpl = new ChampionDaoImpl();
	
	public static boolean createChampions(List<Champion> champions) {
		return daoimpl.createChampions(champions);
	}
	
	public static List<Champion> readChampions(){
		return daoimpl.readChampions();
	}
}
