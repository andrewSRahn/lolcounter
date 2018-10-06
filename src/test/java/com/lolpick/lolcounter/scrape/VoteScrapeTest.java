package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.entity.Power;
import com.lolpick.lolcounter.service.VoteService;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.PowerService;

public class VoteScrapeTest {
	@Test
	public void testScrapeAmumu() throws Exception {
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Amumu"),
				"Weak");
		
		Power power = voteScrape.getPage();
		Vote block = new Vote(
				100,
				power,
				"Amumu",
				"Shyvana", 
				"Jungler", 
				3302, 
				1762);
		
		System.out.println(block);
		System.out.println(power);
		
		assertTrue(power.getBlocks().contains(block));
		assertTrue(PowerService.create(power));	
		assertTrue(VoteService.createBlocks(power.getBlocks()));
	}

	@Test
	public void testScrapeBlitz() throws Exception{
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Blitzcrank"),
				"Strong");
		Power power = voteScrape.getPage();
	    Vote block = new Vote(
	    		480,
				power,
				"Blitzcrank",
				"Sona", 
				"Bottom", 
				4117, 
				1965);
	    
	    assertTrue(power.getBlocks().contains(block));
		assertTrue(PowerService.create(power));	
		assertTrue(VoteService.createBlocks(power.getBlocks()));
	}
	
	@Test
	public void testScrapeJanna() throws Exception{
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Janna"),
				"Even");
		Power power = voteScrape.getPage();
	    Vote block = new Vote(
	    		2520,
				power,
				"Janna",
				"Anivia", 
				"Jungler", 
				1406, 
				1090);
	    assertTrue(power.getBlocks().contains(block));
		assertTrue(PowerService.create(power));	
		assertTrue(VoteService.createBlocks(power.getBlocks()));
	}
	
	@Test
	public void testScrapeLeona() throws Exception{
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Leona"),
				"Good");

		Power power = voteScrape.getPage();
		Vote block = new Vote(
				5040,
				power,
				"Leona",
				"Jinx", 
				"", 
				4570, 
				1512);
		
		assertTrue(power.getBlocks().contains(block));
		assertTrue(PowerService.create(power));
		assertTrue(VoteService.createBlocks(power.getBlocks()));
	}
	
	@Test
	public void testCount() throws Exception{
		Document camilleGood = Jsoup.connect("https://lolcounter.com/champions/camille/good").get();
		assertEquals(VoteScrape.count(camilleGood), 0);
		
		Document braumEven = Jsoup.connect("https://lolcounter.com/champions/braum/even").get();
		assertEquals(VoteScrape.count(braumEven), 20);
		
		Document braumGood = Jsoup.connect("https://lolcounter.com/champions/braum/good").get();
		assertEquals(VoteScrape.count(braumGood), 9);
	}
}