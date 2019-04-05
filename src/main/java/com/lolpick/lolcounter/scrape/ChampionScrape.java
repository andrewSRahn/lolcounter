/***
 * @author Andrew Ahn: andrewsrahn@gmail.com
 * @date 2 September 2018
 * 
 * This class scrapes https://lolcounter.com/champions for champion names.  League of Legends
 * champions contain three special characters:  ', ., and ' '.  For example, Cho'Gath, Dr. Mundo,
 * and Lee Sin.  The characters are preserved in the database which must be replaced when entered 
 * into urls.  The string replacement code can be round in LaneRoleScrape#scrape and 
 * TipScrape#constructor.  Copypasta ftw.  Last note, the index of champion is an arbitrary index
 * and does not correspond to Riot Games championId.  
 */

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
import com.lolpick.lolcounter.utility.ChampionUtil;

public class ChampionScrape {
	private List<Champion> champions;
	private final static int CHAMPION_COUNT = 141;
	private static Logger logger;

	public ChampionScrape() {
		logger = LoggerFactory.getLogger(ChampionScrape.class);
		champions = scrape();
		ChampionService.createChampions(champions);
	}

	public List<Champion> scrape() {
		List<Champion> championList = new ArrayList<>();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://lolcounter.com/champions").get();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		for (int i = 1; i < CHAMPION_COUNT; i++) {
			String selector = "#all-champions > div.champions > a:nth-child(" + i + ") > div > div";
			Elements champions = doc.select(selector);
			for (Element champion : champions) {
				String name = ChampionUtil.riotifyLolcounterChampionName(champion.text());
				Integer id = ChampionUtil.riotifyLolcounterChampionId(name);
				
				logger.info(id + " " + name);
				championList.add(new Champion(id, name));
			}
		}

		return championList;
	}

	public List<Champion> getChampions() {
		return champions;
	}
	
}
