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
		VoteScrape pageScrape = new VoteScrape(
				ChampionService.readChampion("Amumu"),
				"Weak");
		
		Power scrape = pageScrape.getPage();
		Vote block = new Vote(
				100,
				scrape,
				"Shyvana", 
				"Jungler", 
				3164, 
				1654);
		
		assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PowerService.create(scrape));	
		assertTrue(VoteService.createBlocks(scrape.getBlocks()));
	}

	@Test
	public void testScrapeBlitz() throws Exception{
		VoteScrape pageScrape = new VoteScrape(
				ChampionService.readChampion("Blitzcrank"),
				"Strong");
		Power scrape = pageScrape.getPage();
	    Vote block = new Vote(
	    		480,
				scrape,
				"Sona", 
				"Bottom", 
				3982, 
				1857);
	    
	    assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PowerService.create(scrape));	
		assertTrue(VoteService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testScrapeJanna() throws Exception{
		VoteScrape pageScrape = new VoteScrape(
				ChampionService.readChampion("Janna"),
				"Even");
		Power scrape = pageScrape.getPage();
	    Vote block = new Vote(
	    		2520,
				scrape,
				"Anivia", 
				"Jungler", 
				1335, 
				1007);
	    assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PowerService.create(scrape));	
		assertTrue(VoteService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testScrapeLeona() throws Exception{
		VoteScrape pageScrape = new VoteScrape(
				ChampionService.readChampion("Leona"),
				"Good");

		Power scrape = pageScrape.getPage();
		Vote block = new Vote(
				5040,
				scrape,
				"Jinx", 
				"", 
				4454, 
				1430);
		
		assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PowerService.create(scrape));
		assertTrue(VoteService.createBlocks(scrape.getBlocks()));
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