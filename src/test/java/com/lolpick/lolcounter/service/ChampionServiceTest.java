package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;

public class ChampionServiceTest {

	@Test
	public void test() {
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
