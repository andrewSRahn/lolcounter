package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.service.ChampionService;

public class LaneRoleScrapeTest {
	@Test
	public void testAmumu() {
		Champion mu = ChampionService.readChampion("Amumu");
		LaneRoleScrape amumu = new LaneRoleScrape(mu);
		assertTrue(amumu.scrape());
		assertTrue(amumu.insert());
		
		Set<Lane> lanes = mu.getLanes();
		Set<Role> roles = mu.getRoles();

		assertTrue(lanes.contains(new Lane(2, "Jungler")));
		assertTrue(roles.contains(new Role(4, "Magical Damage")));
		assertTrue(roles.contains(new Role(6, "Tank")));
	}
	
//	@Test
//	public void testBlitz() {
//		Champion blitzcrank = ChampionService.readChampion("Blitzcrank");
//		LaneRoleScrape blitz = new LaneRoleScrape(blitzcrank);
//		
//		assertTrue(blitz.scrape());
//		assertTrue(blitz.insert());
//		
//		Set<Lane> lanes = blitzcrank.getLanes();
//		Set<Role> roles = blitzcrank.getRoles();
//		
//		assertTrue(lanes.contains(new Lane(2, "Jungler")));
//		assertTrue(lanes.contains(new Lane(1, "Support")));
//		assertTrue(roles.contains(new Role(3, "Mage")));
//		assertTrue(roles.contains(new Role(4, "Magical Damage")));
//		
//		assertFalse(lanes.contains(new Lane(0, "Bottom")));
//	}
//	
//	@Test
//	public void testLeona() {
//		LaneRoleScrape leona = new LaneRoleScrape("Leona");
//		assertTrue(leona.scrape());
//		List<Lane> lanes = leona.getLanes();
//		List<Role> roles = leona.getRoles();
//		
//		assertTrue(lanes.contains(new Lane(1, "Support")));
//		assertTrue(roles.contains(new Role(5, "Physical Damage")));
//		assertTrue(roles.contains(new Role(6, "Tank")));
//		
//		assertFalse(lanes.contains(new Lane(0, "Bottom")));
//	}
//	
//	@Test
//	public void testJanna() {
//		LaneRoleScrape janna = new LaneRoleScrape("Janna");
//		assertTrue(janna.scrape());
//		List<Lane> lanes = janna.getLanes();
//		List<Role> roles = janna.getRoles();
//		
//		assertTrue(lanes.contains(new Lane(1, "Support")));
//		assertTrue(lanes.contains(new Lane(3, "Mid")));
//		assertTrue(roles.contains(new Role(4, "Magical Damage")));
//		
//		assertFalse(lanes.contains(new Lane(0, "Bottom")));
//	}
}
