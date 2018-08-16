package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.LaneRoleScrape;

public class RoleServiceTest {
	
	@Test
	public void testInitialize() {
		@SuppressWarnings("unused")
		ChampionScrape championScrape = new ChampionScrape();
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
	@SuppressWarnings("unused")
	public void testLeona() {
		RoleService.initialize();
		LaneService.initialize();
		
		Champion leona = ChampionService.readChampion("Leona");
		System.out.println(leona);
		
		ChampionScrape scrape = new ChampionScrape();
		
		LaneRoleScrape leonaScrape = new LaneRoleScrape(leona);
		System.out.println(leona.getRoles());
		
	}
}
