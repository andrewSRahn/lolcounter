package com.lolpick.lolcounter.scrape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.service.ChampionService;

public class VoteScrapeTest {
	@Test
	public void testScrapeAmumu() throws Exception {
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Amumu"),
				"Weak");
		
		Vote vote = new Vote(
				125,
				"Weak",
				"amumu",
				"Shyvana", 
				"Jungler", 
				3532, 
				1965);
		
		List<Vote> votes = voteScrape.getVotes();
		
		System.out.println(vote);
		System.out.println(votes);
		assertTrue(votes.contains(vote));
	}

	@Test
	public void testScrapeBlitz() throws Exception{
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Blitzcrank"),
				"Strong");
	    Vote vote = new Vote(
	    		210,
				"Strong",
				"blitzcrank",
				"Sona", 
				"Bottom", 
				4428, 
				2207);
	    
	    List<Vote> votes = voteScrape.getVotes();
		System.out.println(vote);
		System.out.println(votes);
		assertTrue(votes.contains(vote));
	    

	}
	
	@Test
	public void testScrapeJanna() throws Exception{
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Janna"),
				"Even");
	    Vote vote = new Vote(
	    		159,
				"Even",
				"janna",
				"Anivia", 
				"Jungler", 
				1519, 
				1192);

	    List<Vote> votes = voteScrape.getVotes();
		System.out.println(vote);
		System.out.println(votes);
		assertTrue(votes.contains(vote));
	}
	
	@Test
	public void testScrapeLeona() throws Exception{
		VoteScrape voteScrape = new VoteScrape(
				ChampionService.readChampion("Leona"),
				"Good");

		Vote vote = new Vote(
				356,
				"Good",
				"leona",
				"Jinx", 
				"", 
				4771, 
				1637);
		
	    List<Vote> votes = voteScrape.getVotes();
		System.out.println(vote);
		System.out.println(votes);
		assertTrue(votes.contains(vote));
		
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