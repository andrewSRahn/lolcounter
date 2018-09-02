package com.lolpick.lolcounter.application;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
		Logger logger = LoggerFactory.getLogger(Application.class);
		
		logger.info("Starting up!");
		
		ChampionScrape championScrape = new ChampionScrape();
		
		LaneService.initialize();
		RoleService.initialize();
		
		List<Champion> champions = ChampionService.readChampions();
		List<String> powers = Arrays.asList("Weak", "Strong", "Even", "Good");
		
		for(Champion champion: champions) {
			LaneRoleScrape laneRoleScrape = new LaneRoleScrape(champion);
			TipScrape tipScrape = new TipScrape(champion);
			
			logger.info(laneRoleScrape.getChampionId() + ":" + laneRoleScrape.getName());
			logger.info(laneRoleScrape.getLanes().toString());
			logger.info(laneRoleScrape.getRoles().toString());
			logger.info(tipScrape.getTips().toString());

			for(String power: powers) {
				VoteScrape voteScrape = new VoteScrape(champion, power);
			}
		}
		
		logger.info("Done!");
	}
}
