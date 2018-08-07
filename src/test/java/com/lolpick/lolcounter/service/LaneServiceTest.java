package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
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
		LaneService.create(lanes, "Blitzcrank");
	}

	@Test
	public void testRead() {
		for(Champion champion: LaneService.read("Jungler").getChampions()) {
			System.out.println(champion);
		}
	}
}
