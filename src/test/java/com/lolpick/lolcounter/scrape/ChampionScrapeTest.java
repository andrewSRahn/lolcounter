package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;

public class ChampionScrapeTest {
	@Test
	public void testScrape() {
		List<Champion> champions = ChampionScrape.scrape();
		
		assertTrue(contains(champions, "Zed"));
		assertTrue(contains(champions, "Kayle"));
		assertTrue(contains(champions, "Irelia"));
		assertTrue(contains(champions, "Amumu"));
		assertTrue(contains(champions, "Zilean"));
	}
	
	@Test
	public void testInsert() {
		ChampionScrape.insert();
	}
	
	private boolean contains(List<Champion> champions, String string) {
		Champion champion = champions.stream()
			.filter(c -> !c.getName().equals(string))
			.findAny()
			.orElse(null);
		
		if(champion == null)
			return false;
		
		return true;
	}
}