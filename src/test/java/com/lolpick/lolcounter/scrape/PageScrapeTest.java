package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.entity.Relation;
import com.lolpick.lolcounter.service.BlockService;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.PageService;

public class PageScrapeTest {
	@Test
	public void testScrapeAmumu() throws Exception {
		PageScrape pageScrape = new PageScrape(
				ChampionService.readChampion("Amumu"),
				"Weak");
		
		Relation scrape = pageScrape.getPage();
		Vote block = new Vote(
				100,
				scrape,
				"Shyvana", 
				"Jungler", 
				3164, 
				1654);
		
		assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));	
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}

	@Test
	public void testScrapeBlitz() throws Exception{
		PageScrape pageScrape = new PageScrape(
				ChampionService.readChampion("Blitzcrank"),
				"Strong");
		Relation scrape = pageScrape.getPage();
	    Vote block = new Vote(
	    		480,
				scrape,
				"Sona", 
				"Bottom", 
				3982, 
				1857);
	    
	    assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));	
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testScrapeJanna() throws Exception{
		PageScrape pageScrape = new PageScrape(
				ChampionService.readChampion("Janna"),
				"Even");
		Relation scrape = pageScrape.getPage();
	    Vote block = new Vote(
	    		2520,
				scrape,
				"Anivia", 
				"Jungler", 
				1335, 
				1007);
	    assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));	
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testScrapeLeona() throws Exception{
		PageScrape pageScrape = new PageScrape(
				ChampionService.readChampion("Leona"),
				"Good");

		Relation scrape = pageScrape.getPage();
		Vote block = new Vote(
				5040,
				scrape,
				"Jinx", 
				"", 
				4454, 
				1430);
		
		assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testCount() throws Exception{
		Document camilleGood = Jsoup.connect("https://lolcounter.com/champions/camille/good").get();
		assertEquals(PageScrape.count(camilleGood), 0);
		
		Document braumEven = Jsoup.connect("https://lolcounter.com/champions/braum/even").get();
		assertEquals(PageScrape.count(braumEven), 20);
		
		Document braumGood = Jsoup.connect("https://lolcounter.com/champions/braum/good").get();
		assertEquals(PageScrape.count(braumGood), 9);
	}
}