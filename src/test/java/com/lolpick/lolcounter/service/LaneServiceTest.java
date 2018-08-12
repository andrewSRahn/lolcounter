package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;

public class LaneServiceTest {

	@Test
	public void testInitialize() {
		assertTrue(LaneService.initialize());
	}
	
	@Test
	public void testRead() {
		for(Champion champion: LaneService.read("Jungler").getChampions()) {
			System.out.println(champion);
		}
	}
}
