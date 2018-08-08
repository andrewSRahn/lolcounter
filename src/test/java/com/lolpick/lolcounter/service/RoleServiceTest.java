package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.LaneRoleScrape;

public class RoleServiceTest {
	
	@Test
	public void testInitialize() {
		assertTrue(RoleService.initialize());
		assertEquals(RoleService.read("Assassin").getId(), new Integer(0));
		assertNotEquals(RoleService.read("Support").getId(), new Integer(0));		
	}
	
	/*
	 * This is clearly an integration test of Leona but it must be written.
	 * TODO rewrite this code utilizing testng
	 * Leona has roles: physical damage, tank
	 * Leona has lanes: support
	 */
	@Test
	public void testUpdate() {
		RoleService.initialize();
		LaneService.initialize();
		ChampionScrape.insert();
		
		Champion leona = ChampionService.readChampion("Leona");
		System.out.println(leona);
		
		// leona should start with no roles
		assertTrue(leona.getRoles().isEmpty());
		
		// leona should start with no lanes
		assertTrue(leona.getLanes().isEmpty());
		
		// scrape leona
		LaneRoleScrape leonaScrape = new LaneRoleScrape(leona);
		
		leona = ChampionService.readChampion("Leona");
		System.out.println(leona);
		
		Role physical = RoleService.read("Physical Damage");
		Role tank = RoleService.read("Tank");
		
		System.out.println("physical damage" + physical.getChampions());
		System.out.println("tank" + tank.getChampions());
		
		
		
	}
}
