package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Block;
import com.lolpick.lolcounter.entity.Page;
import com.lolpick.lolcounter.service.BlockService;
import com.lolpick.lolcounter.service.PageService;

public class PageScrapeTest {
	@Test
	public void testScrapeAmumu() throws Exception {
		Page scrape = PageScrape.scrape("https://lolcounter.com/champions/amumu/weak", "Amumu", "Weak", 5);
		Block block = new Block(
				100,
				scrape,
				"Shyvana", 
				"http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/Shyvana.png", 
				"Jungler", 
				3164, 
				1654);
		
		assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));	
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}

	@Test
	public void testScrapeBlitz() throws Exception{
		Page scrape = PageScrape.scrape(
	    		"https://lolcounter.com/champions/blitzcrank/strong", 
	    		"Blitzcrank", 
	    		"Strong",
	    		12);
	    Block block = new Block(
	    		480,
				scrape,
				"Sona", 
				"http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/Sona.png", 
				"Bottom", 
				3982, 
				1857);
	    
	    assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));	
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testScrapeJanna() throws Exception{
		Page scrape = PageScrape.scrape(
	    		"https://lolcounter.com/champions/janna/even", 
	    		"Janna", 
	    		"Even",
	    		42);
	    Block block = new Block(
	    		2520,
				scrape,
				"Anivia", 
				"http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/Anivia.png", 
				"Jungler", 
				1335, 
				1007);
	    assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));	
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}
	
	@Test
	public void testScrapeLeona() throws Exception{
		Page scrape = PageScrape.scrape(
	    		"https://lolcounter.com/champions/leona/good", 
	    		"Leona", 
	    		"Good",
	    		63);
		
		Block block = new Block(
				5040,
				scrape,
				"Jinx", 
				"http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/Jinx.png", 
				"", 
				4454, 
				1430);
		
		assertTrue(scrape.getBlocks().contains(block));
		assertTrue(PageService.create(scrape));
		assertTrue(BlockService.createBlocks(scrape.getBlocks()));
	}
}