package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.service.ChampionService;

public class ChampionScrapeTest {
	@SuppressWarnings("unused")
	@Test
	public void testScrape() {
		ChampionScrape scrape = new ChampionScrape();
		List<Champion> champions = ChampionService.readChampions();
		
		assertTrue(contains(champions, "Zed"));
		assertTrue(contains(champions, "Kayle"));
		assertTrue(contains(champions, "Irelia"));
		assertTrue(contains(champions, "Amumu"));
		assertTrue(contains(champions, "Zilean"));
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