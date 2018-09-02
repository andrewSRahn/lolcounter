package com.lolpick.lolcounter.scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.service.ChampionService;

public class ChampionScrape {	
	private final static int CHAMPION_COUNT = 141;
	private static Logger logger;
	
	public ChampionScrape() {
		logger = LoggerFactory.getLogger(ChampionScrape.class);
		insert();
	}
	
	private static List<Champion> scrape() {
		List<Champion> championList = new ArrayList<>();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://lolcounter.com/champions").get();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		for(int i = 1; i < CHAMPION_COUNT; i++) {
			String selector = "#all-champions > div.champions > a:nth-child(" + i + ") > div > div";
			Elements champions = doc.select(selector);
			for(Element champion: champions) {
				logger.info(champion.text());
				championList.add(new Champion(i, champion.text()));
			}
		}
		
		return championList;
	}
	
	private static void insert() {
		ChampionService.createChampions(scrape());
	}
}
