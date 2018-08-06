package com.lolpick.lolcounter.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lolpick.lolcounter.daoimpl.LaneDaoImpl;
import com.lolpick.lolcounter.entity.Lane;

public class LaneService {
	private static LaneDaoImpl dao = new LaneDaoImpl();

	public static boolean create(Set<Lane> lanes) {
		return dao.create(lanes);
	}
	
	public static boolean initialize() {
		Set<Lane> lanes = Stream.of(
				new Lane(0, "Bottom"),
				new Lane(1, "Support"),
				new Lane(2, "Jungler"),
				new Lane(3, "Mid"),
				new Lane(4, "Top"))
				.collect(Collectors.toSet());
		
		return LaneService.create(lanes);
	}
	
	public static boolean create(Set<Lane> lanes, String champion) {
		return dao.create(lanes, champion);
	}
	
	public static Lane read(String lane) {
		return dao.read(lane);
	}
	
	public static boolean update(Set<Lane> lanes) {
		return dao.update(lanes);
	}
}
