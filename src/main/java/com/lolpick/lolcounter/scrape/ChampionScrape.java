package com.lolpick.lolcounter.scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.service.ChampionService;

public class ChampionScrape {	
	private final static int CHAMPION_COUNT = 141;
	
	public ChampionScrape() {
		insert();
	}
	
	private static List<Champion> scrape() {
		List<Champion> championList = new ArrayList<>();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://lolcounter.com/champions").get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 1; i < CHAMPION_COUNT; i++) {
			String selector = "#all-champions > div.champions > a:nth-child(" + i + ") > div > div";
			Elements champions = doc.select(selector);
			for(Element champion: champions)
				championList.add( 
						new Champion(i, champion.text()));
		}
		
		return championList;
	}
	
	private static void insert() {
		ChampionService.createChampions(scrape());
	}
}
