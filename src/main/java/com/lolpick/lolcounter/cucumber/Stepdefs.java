package com.lolpick.lolcounter.cucumber;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.lolpick.lolcounter.entity.Block;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.entity.Page;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.LaneRoleScrape;
import com.lolpick.lolcounter.scrape.PageScrape;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.PageService;
import com.lolpick.lolcounter.service.RoleService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Stepdefs {
	List<Champion> champions = null;
	List<String> names = Arrays.asList("Amumu", "Blitzcrank", "Janna", "Leona");
    
    @Given("^Champions page is scraped$")
    public void champions_page_is_scraped() throws Exception {
    	ChampionService.createChampions(ChampionScrape.scrape());
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
	
	Page amumuPage = null;
	Page blitzPage = null;
	Page jannaPage = null;
	Page leonaPage = null;
	
	Block amumuBlock = null;
	Block blitzValidate = null;
	Block jannaValidate = null;
	Block leonaValidate = null;
	
	@Given("^/champions/name/relation is scraped$")
	public void champions_name_relation_is_scraped() throws Exception {
	    
	    PageService.create(PageScrape.scrape(
	    		"https://lolcounter.com/champions/amumu/weak", 
	    		"Amumu", 
	    		"Weak",
	    		5));
	    PageService.create(PageScrape.scrape(
	    		"https://lolcounter.com/champions/blitzcrank/strong", 
	    		"Blitzcrank", 
	    		"Strong",
	    		12));
	    PageService.create(PageScrape.scrape(
	    		"https://lolcounter.com/champions/janna/even", 
	    		"Janna", 
	    		"Even",
	    		42));
	    PageService.create(PageScrape.scrape(
	    		"https://lolcounter.com/champions/leona/good", 
	    		"Leona", 
	    		"Good",
	    		63));
	}

	@When("^Page service reads pages$")
	public void page_service_reads_pages() throws Exception {
		amumuPage = PageService.read("Amumu", "Weak");
		blitzPage = PageService.read("Blitzcrank", "Strong");
		jannaPage = PageService.read("Janna", "Even");
		leonaPage = PageService.read("Leona", "Good");
	}

	@Then("^Page block will contain image, foe, lane, up, and down$")
	public void page_block_will_contain_image_foe_lane_up_and_down() throws Exception {
		amumuBlock = new Block(
				100,
				amumuPage,
				"Shyvana", 
				"Jungler", 
				3164, 
				1654);
		blitzValidate = new Block(
				480,
				blitzPage,
				"Sona", 
				"Bottom", 
				3982, 
				1857);
		jannaValidate = new Block(
				2520,
				jannaPage,
				"Anivia", 
				"Jungler", 
				1335, 
				1006);
		leonaValidate = new Block(
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
	
	Set<Lane> amumuLanes = null;
	Set<Lane> blitzLanes = null;
	Set<Lane> jannaLanes = null;
	Set<Lane> leonaLanes = null;
	
	Set<Role> amumuRoles = null;
	Set<Role> blitzRoles = null;
	Set<Role> jannaRoles = null;
	Set<Role> leonaRoles = null;
	
	@Given("^/champions/name is scraped$")
	public void champions_name_is_scraped() throws Exception {
		for(String name: names) {
			LaneRoleScrape scrape = new LaneRoleScrape(name);
			assertTrue(LaneService.create(scrape.getLanes()));
			assertTrue(RoleService.create(scrape.getRoles()));
		}
	}  

	@When("^Lane service reads lanes$")
	public void lane_service_reads_lanes() throws Exception {
		amumuLanes = ChampionService.readChampion("Amumu").getLanes();
		blitzLanes = ChampionService.readChampion("Blitzcrank").getLanes();
		jannaLanes = ChampionService.readChampion("Janna").getLanes();
		leonaLanes = ChampionService.readChampion("Leona").getLanes();
	}

	@When("^Role service reads$")
	public void role_service_reads() throws Exception {
		amumuRoles = ChampionService.readChampion("Amumu").getRoles();
		blitzRoles = ChampionService.readChampion("Blitzcrank").getRoles();
		jannaRoles = ChampionService.readChampion("Janna").getRoles();
		leonaRoles = ChampionService.readChampion("Leona").getRoles();
	}

	@Then("^lanes will contain Top, Mid, Jungler, Support, or Bottom$")
	public void lanes_will_contain_Top_Mid_Jungler_Support_or_Bottom() throws Exception {
		Lane amumuLane = amumuLanes.stream()
				.filter(l -> !l.getLane().equals("Jungler"))
				.findFirst()
				.orElse(null);
		
		Lane blitzLane = blitzLanes.stream()
		.filter(l -> !l.getLane().equals("Support")
				|| !l.getLane().equals("Bottom")
				|| !l.getLane().equals("Jungler"))
		.findFirst()
		.orElse(null);
		
		Lane jannaLane = jannaLanes.stream()
				.filter(l -> !l.getLane().equals("Mid")
						|| !l.getLane().equals("Bottom")
						|| !l.getLane().equals("Support"))
				.findFirst()
				.orElse(null);
		
		Lane leonaLane = leonaLanes.stream()
				.filter(l -> !l.getLane().equals("Support")
						|| !l.getLane().equals("Bottom"))
				.findFirst()
				.orElse(null);
		
		assertNotNull(amumuLane);
		assertNotNull(blitzLane);
		assertNotNull(jannaLane);
		assertNotNull(leonaLane);
	}

	@Then("^roles will contain Fighter, Mage, Assassin, or Tank$")
	public void roles_will_contain_Fighter_Mage_Assassin_or_Tank() throws Exception {
		Role amumu = amumuRoles.stream()
				.filter( r -> !r.getRole().equals("Tank"))
				.findFirst()
				.orElse(null);
		
		Role blitz = blitzRoles.stream()
				.filter( r -> !r.getRole().equals("Mage"))
				.findFirst()
				.orElse(null);
		
		Role janna = jannaRoles.stream()
				.findFirst()
				.orElse(null);
		
		Role leona = leonaRoles.stream()
				.filter( r -> !r.getRole().equals("Tank"))
				.findFirst()
				.orElse(null);
		
		assertNotNull(amumu);
		assertNotNull(blitz);
		assertNull(janna);
		assertNotNull(leona);
	}
	
}