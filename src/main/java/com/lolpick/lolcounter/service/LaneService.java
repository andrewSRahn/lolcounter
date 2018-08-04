package com.lolpick.lolcounter.service;

import java.util.Arrays;
import java.util.List;

import com.lolpick.lolcounter.daoimpl.LaneDaoImpl;
import com.lolpick.lolcounter.entity.Lane;

public class LaneService {
	private static LaneDaoImpl dao = new LaneDaoImpl();

	public static boolean create(List<Lane> lanes) {
		return dao.create(lanes);
	}
	
	public static boolean initialize() {
		List<Lane> lanes = Arrays.asList(
				new Lane(0, "Bottom"),
				new Lane(1, "Support"),
				new Lane(2, "Jungler"),
				new Lane(3, "Mid"),
				new Lane(4, "Top"));
		
		return LaneService.create(lanes);
	}
	
	public static boolean create(List<Lane> lanes, String champion) {
		return dao.create(lanes, champion);
	}
	
	public static Lane read(String lane) {
		return dao.read(lane);
	}
}
