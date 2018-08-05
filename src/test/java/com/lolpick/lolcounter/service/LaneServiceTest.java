package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Lane;

public class LaneServiceTest {

	@Test
	public void testInitialize() {
		assertTrue(LaneService.initialize());
	}
	
	@Test
	public void testCreate() {		
		Set<Lane> lanes = Stream.of(
				LaneService.read("Jungler"))
				.collect(Collectors.toSet());
		
		assertTrue(LaneService.create(lanes, "Amumu"));
	}

	@Test
	public void testRead() {
		assertTrue(ChampionService.readChampion("Amumu").getLanes().contains(new Lane(2, "Jungler")));
//		assertTrue(ChampionService.readChampion("Blitzcrank").getLanes().contains(new Lane(0, "Bottom")));
//		assertTrue(ChampionService.readChampion("Blitzcrank").getLanes().contains(new Lane(2, "Jungler")));
//		assertTrue(ChampionService.readChampion("Leona").getLanes().contains(new Lane(1, "Support")));
//		assertTrue(ChampionService.readChampion("Janna").getLanes().contains(new Lane(1, "Support")));
//		assertTrue(ChampionService.readChampion("Janna").getLanes().contains(new Lane(3, "Mid")));
	}
}
