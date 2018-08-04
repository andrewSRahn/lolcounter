package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Lane;

public class LaneServiceTest {

	@Test
	public void testInitialize() {
		assertTrue(LaneService.initialize());
	}
	
	@Test
	public void testCreate() {		
		List<Lane> lanes = Arrays.asList(
				LaneService.read("Jungler"));
		
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
