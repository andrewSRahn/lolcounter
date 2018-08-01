package com.lolpick.lolcounter.application;

import java.util.Arrays;
import java.util.List;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.scrape.ChampionScrape;
import com.lolpick.lolcounter.scrape.PageScrape;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.RoleService;

public class Application {
	public static void main(String[] args) {
		ChampionScrape.insert();
		
		LaneService.initialize();
		RoleService.initialize();
		
		List<Champion> champions = ChampionService.readChampions();
		List<String> relations = Arrays.asList("Weak", "Strong", "Even", "Good");
		
		for(Champion champion: champions) {
			for(String relation: relations) {
				String name = champion.getName().toLowerCase().replace("'", "").replace(".", "").replace(" ", "");
				String url = "https://lolcounter.com/champions/" + name + "/" + relation.toLowerCase();
				
				try {
					if(PageScrape.insert(url, champion.getName(), relation, champion.getId()))
						System.out.println(url);
					else
						throw new Exception(url + " failed");

				} catch(Exception e) {
					Fail.appendToFile("src/main/resources/fails.txt", url, name, relation, champion.getId());
					System.out.println(url + " failed");
				}
			}
		}
	}
}
