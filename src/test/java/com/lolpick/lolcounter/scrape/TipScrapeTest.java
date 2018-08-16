package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;
import com.lolpick.lolcounter.service.ChampionService;

public class TipScrapeTest {

	@Test
	public void testAkali() {
		Champion akali = ChampionService.readChampion("Akali");
		Champion lee = ChampionService.readChampion("Lee Sin");
		Champion syndra = ChampionService.readChampion("Syndra");
		
		TipScrape tipScrape = new TipScrape(akali);
		
		Set<Tip> tips = tipScrape.getTips();
		Set<Tip> should = Stream.of(
			new Tip(1164, null, "After level 6, try to prevent her use of Mark of the Assassin, as she can proc it twice using her dashes."),
			new Tip(348, lee, "Your Q and your E can reveal Akali in stealth. Use this to your advantage."),
			new Tip(-39, syndra, "Upgrading your trinket to a pink totem can give you a permanent pink to throw down when you need it."))
				.collect(Collectors.toSet());
		
		assertTrue(tips.containsAll(should));
	}
	
	@Test
	public void testPage() {
		
	}
	
}
