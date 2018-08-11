package com.lolpick.lolcounter.application;

import java.util.Arrays;
import java.util.List;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.LaneRoleScrape;
import com.lolpick.lolcounter.scrape.VoteScrape;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.RoleService;

public class Application {
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ChampionScrape championScrape = new ChampionScrape();
		
		LaneService.initialize();
		RoleService.initialize();
		
		List<Champion> champions = ChampionService.readChampions();
		List<String> power = Arrays.asList("Weak", "Strong", "Even", "Good");
		
		for(Champion champion: champions) {
			LaneRoleScrape laneRoleScrape = new LaneRoleScrape(champion);
				
			System.out.println(laneRoleScrape.getChampionId() + ":" + laneRoleScrape.getName());
			System.out.println(laneRoleScrape.getLanes());
			System.out.println(laneRoleScrape.getRoles());

			for(String relation: power) {
				@SuppressWarnings("unused")
				VoteScrape pageScrape = new VoteScrape(champion, relation);
		
			}
		}
		System.out.println("Done!");
	}
}
