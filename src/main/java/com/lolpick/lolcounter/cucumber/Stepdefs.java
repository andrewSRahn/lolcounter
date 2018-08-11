package com.lolpick.lolcounter.cucumber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Power;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.LaneRoleScrape;
import com.lolpick.lolcounter.scrape.VoteScrape;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.PowerService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {
	List<Champion> champions = null;
	List<String> names = Arrays.asList("Amumu", "Blitzcrank", "Janna", "Leona");
    
    @Given("^Champions page is scraped$")
    public void champions_page_is_scraped() throws Exception {
    	@SuppressWarnings("unused")
    	ChampionScrape scrape = new ChampionScrape();
    }

    @When("^Champion service reads champions$")
    public void champion_service_reads_champions() throws Exception {
    	champions = ChampionService.readChampions();
    }

    @Then("^champions will contain given names\\.$")
    public void champions_will_contain_given_names() throws Exception {
        for(String name: names)
        	championCheck(name);
    }

	private void championCheck(String string) {
		Champion champion = champions.stream()
				.filter(c -> !c.getName().equals(string))
				.findFirst()
				.orElse(null);
		
		assertNotNull(champion);
	}
	
	Power amumuPage = null;
	Power blitzPage = null;
	Power jannaPage = null;
	Power leonaPage = null;
	
	Vote amumuBlock = null;
	Vote blitzValidate = null;
	Vote jannaValidate = null;
	Vote leonaValidate = null;
	
	@Given("^/champions/name/relation is scraped$")
	@SuppressWarnings("unused")
	public void champions_name_relation_is_scraped() throws Exception {
	    VoteScrape amumu = new VoteScrape(
	    		ChampionService.readChampion("Amumu"),
	    		"Weak");
	    
	    VoteScrape blitz = new VoteScrape(
	    		ChampionService.readChampion("Blitzcrank"),
	    		"Strong");
	    
	    VoteScrape janna = new VoteScrape(
	    		ChampionService.readChampion("Janna"),
	    		"Even");
	    
	    VoteScrape leona = new VoteScrape(
	    		ChampionService.readChampion("Leona"),
	    		"Good");
	}

	@When("^Page service reads pages$")
	public void page_service_reads_pages() throws Exception {
		amumuPage = PowerService.read("Amumu", "Weak");
		blitzPage = PowerService.read("Blitzcrank", "Strong");
		jannaPage = PowerService.read("Janna", "Even");
		leonaPage = PowerService.read("Leona", "Good");
	}

	@Then("^Page block will contain image, foe, lane, up, and down$")
	public void page_block_will_contain_image_foe_lane_up_and_down() throws Exception {
		amumuBlock = new Vote(
				100,
				amumuPage,
				"Shyvana", 
				"Jungler", 
				3164, 
				1654);
		blitzValidate = new Vote(
				480,
				blitzPage,
				"Sona", 
				"Bottom", 
				3982, 
				1857);
		jannaValidate = new Vote(
				2520,
				jannaPage,
				"Anivia", 
				"Jungler", 
				1335, 
				1006);
		leonaValidate = new Vote(
				5040,
				leonaPage,
				"Jinx", 
				"", 
				4454, 
				1430);
		
		assertTrue(amumuPage.getBlocks().contains(amumuBlock));
		assertTrue(blitzPage.getBlocks().contains(blitzValidate));
		assertTrue(jannaPage.getBlocks().contains(jannaValidate));
		assertTrue(leonaPage.getBlocks().contains(leonaValidate));
	}
	
	@Given("^Champion and Lane tables are initialized$")
	public void champion_and_Lane_tables_are_initialized() throws Exception {
	    LaneService.initialize();
	    @SuppressWarnings("unused")
	    ChampionScrape championScrape = new ChampionScrape();
	    Champion blitzcrank = ChampionService.readChampion("Blitzcrank");
	    @SuppressWarnings("unused")
	    LaneRoleScrape blitzcrankScrape = new LaneRoleScrape(blitzcrank);
	}

	@Then("^Blitzcrank will be a support and jungler$")
	public void blitzcrank_will_be_a_support_and_jungler() throws Exception {
		
	}

	@Then("^Leona will be a support$")
	public void leona_will_be_a_support() throws Exception {
	    // Write code here that turns the phrase above into concrete actions
	}

	
}