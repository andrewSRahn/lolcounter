package com.lolpick.lolcounter.application;

import java.util.Arrays;
import java.util.List;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.LaneRoleScrape;
import com.lolpick.lolcounter.scrape.TipScrape;
import com.lolpick.lolcounter.scrape.VoteScrape;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.RoleService;

public class Application {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		ChampionScrape championScrape = new ChampionScrape();
		
		LaneService.initialize();
		RoleService.initialize();
		
		List<Champion> champions = ChampionService.readChampions();
		List<String> power = Arrays.asList("Weak", "Strong", "Even", "Good");
		
		for(Champion champion: champions) {
			LaneRoleScrape laneRoleScrape = new LaneRoleScrape(champion);
			TipScrape tipScrape = new TipScrape(champion);
				
			System.out.println(laneRoleScrape.getChampionId() + ":" + laneRoleScrape.getName());
			System.out.println(laneRoleScrape.getLanes());
			System.out.println(laneRoleScrape.getRoles());
			System.out.println(tipScrape.getTips());

			for(String relation: power) {
				VoteScrape pageScrape = new VoteScrape(champion, relation);
		
			}
		}
		System.out.println("Done!");
	}
}
