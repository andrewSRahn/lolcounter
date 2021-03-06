package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Ignore;
import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;
import com.lolpick.lolcounter.service.ChampionService;

public class TipScrapeTest {

	@Test
	public void testIntegerParseInt() {
		Champion ahri = ChampionService.readChampion("Ahri");
		TipScrape tipScrape = new TipScrape(ahri);
		assertEquals(new Integer(1000), tipScrape.integerParseInt("1k"));
		assertEquals(new Integer(1200), tipScrape.integerParseInt("1.2k"));
		assertEquals(new Integer(1600), tipScrape.integerParseInt("1.6k"));
	}
	
	@Ignore
	public void testAkali() {
		Champion akali = ChampionService.readChampion("Akali");
		Champion lee = ChampionService.readChampion("Lee Sin");
		Champion syndra = ChampionService.readChampion("Syndra");
		
		TipScrape tipScrape = new TipScrape(akali);
		
		Set<Tip> tips = tipScrape.getTips();
		Set<Tip> should = Stream.of(
			new Tip(1164, akali, null, "After level 6, try to prevent her use of Mark of the Assassin, as she can proc it twice using her dashes."),
			new Tip(348, akali, lee, "Your Q and your E can reveal Akali in stealth. Use this to your advantage."),
			new Tip(-39, akali, syndra, "Upgrading your trinket to a pink totem can give you a permanent pink to throw down when you need it."))
				.collect(Collectors.toSet());
		
		assertTrue(tips.containsAll(should));
	}
	
	@Ignore
	public void testCountChampionAkali() {
		Champion akali = ChampionService.readChampion("Akali");
		TipScrape tipScrape = new TipScrape(akali);
		assertEquals(14, tipScrape.countChampion());
	}
	
	@Ignore
	public void testCountChampionAurelion() {
		Champion aurelion = ChampionService.readChampion("Aurelion Sol");
		TipScrape tipScrape = new TipScrape(aurelion);
		assertEquals(1, tipScrape.countChampion());
	}
	
	@Ignore
	public void testCountChampionCho() {
		Champion cho = ChampionService.readChampion("Cho'Gath");
		TipScrape tipScrape = new TipScrape(cho);
		assertEquals(6, tipScrape.countChampion());
	}
	
	@Ignore
	public void testCountChampionLee() {
		Champion lee = ChampionService.readChampion("Lee Sin");
		TipScrape tipScrape = new TipScrape(lee);
		assertEquals(7, tipScrape.countChampion());
	}

	@Ignore
	public void testCountChampionZed() {
		Champion zed = ChampionService.readChampion("Zed");
		TipScrape tipScrape = new TipScrape(zed);
		assertEquals(18, tipScrape.countChampion());
	}
	
	@Ignore
	public void testPageZed() {
		Champion zed = ChampionService.readChampion("Zed");
		TipScrape tipScrape = new TipScrape(zed);
		assertEquals(3, tipScrape.countPage(18));
		assertEquals(10, tipScrape.countPage(17));
	}
}
