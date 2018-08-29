package com.lolpick.lolcounter.application;

import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		Logger logger = Logger.getLogger(Application.class.getName());
		logger.setLevel(Level.ALL);
		try {
			FileHandler fileHandler = new FileHandler("src/main/resources/lolcounter%g%u.log");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		ChampionScrape championScrape = new ChampionScrape();
		
		LaneService.initialize();
		RoleService.initialize();
		
		List<Champion> champions = ChampionService.readChampions();
		List<String> powers = Arrays.asList("Weak", "Strong", "Even", "Good");
		
		for(Champion champion: champions) {
			LaneRoleScrape laneRoleScrape = new LaneRoleScrape(champion);
			TipScrape tipScrape = new TipScrape(champion);
			
			String log0 = laneRoleScrape.getChampionId() + ":" + laneRoleScrape.getName();
			String log1 = laneRoleScrape.getLanes().toString();
			String log2 = laneRoleScrape.getRoles().toString();
			String log3 = tipScrape.getTips().toString();
			
			logger.log(Level.INFO, log0);
			logger.log(Level.INFO, log1);
			logger.log(Level.INFO, log2);
			logger.log(Level.INFO, log3);
			
//			System.out.println(laneRoleScrape.getChampionId() + ":" + laneRoleScrape.getName());
//			System.out.println(laneRoleScrape.getLanes());
//			System.out.println(laneRoleScrape.getRoles());
//			System.out.println(tipScrape.getTips());

			for(String power: powers) {
				VoteScrape voteScrape = new VoteScrape(champion, power, logger);
			}
		}
		System.out.println("Done!");
	}
}
