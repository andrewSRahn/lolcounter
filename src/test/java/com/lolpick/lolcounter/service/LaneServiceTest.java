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
		List<Lane> lanes = Arrays.asList(new Lane(2, "Jungler"));
		
		assertTrue(LaneService.create(lanes, "Amumu"));
		
		
	}

//	@Test
//	public void testRead() {
//		assertTrue(LaneService.readChampion("Amumu").contains(new Lane(2, "Jungler")));
//		assertTrue(LaneService.readChampion("Blitzcrank").contains(new Lane(0, "Bottom")));
//		assertTrue(LaneService.readChampion("Blitzcrank").contains(new Lane(2, "Jungler")));
//		assertTrue(LaneService.readChampion("Leona").contains(new Lane(1, "Support")));
//		assertTrue(LaneService.readChampion("Janna").contains(new Lane(1, "Support")));
//		assertTrue(LaneService.readChampion("Janna").contains(new Lane(3, "Mid")));
//	}
}
