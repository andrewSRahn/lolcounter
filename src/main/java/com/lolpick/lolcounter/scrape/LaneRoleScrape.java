package com.lolpick.lolcounter.scrape;

import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.RoleService;

public class LaneRoleScrape {	
	private Champion champion;
	private Logger logger;
	
	public LaneRoleScrape(Champion champion) {
		this.champion = champion;
		this.logger = LoggerFactory.getLogger(LaneRoleScrape.class);
		scrape();
		insert();
	}
	
	public String getName() {
		return this.champion.getName();
	}
	
	public Integer getChampionId() {
		return this.champion.getId();
	}
	
	public Set<Lane> getLanes() {
		return this.champion.getLanes();
	}

	public Set<Role> getRoles() {
		return this.champion.getRoles();
	}

	public boolean scrape() {
		Document document = null;
		
		try {
			String name = this.champion.getName().toLowerCase().replace("'", "").replace(".", "").replace(" ", "");
			String url = "https://lolcounter.com/champions/" + name;
			document = Jsoup.connect(url).get();
		} catch(Exception e) {
			logger.error(e.getMessage());
			return false;
		}
		
		for(int i = 1; i < countRoles(document); i++) {
			String selector = "div.role:nth-child(" + i + ")";
			String role = document.select(selector).text();
			this.champion.getRoles().add(RoleService.read(role));
		}

		for(int i = 1; i < countLanes(document); i++) {
			String selector = "div.lanes > div.lane:nth-child(" + i + ")";
			String lane = document.select(selector).text();
			this.champion.getLanes().add(LaneService.read(lane));
		}
		
		boolean support = this.champion.getRoles().contains(RoleService.read("Support"));
		
		if(support) {
			this.champion.getLanes().remove(LaneService.read("Bottom"));
			this.champion.getRoles().remove(RoleService.read("Support"));
			this.champion.getLanes().add(LaneService.read("Support"));
		}
		
		return true;
	}
	
	public boolean insert() {
		boolean lane = LaneService.update(this.champion.getLanes(), this.champion);
		boolean role = RoleService.update(this.champion.getRoles(), this.champion);
		boolean champion = ChampionService.updateChampion(this.champion);
		return lane && role && champion;
	}
	
	@SuppressWarnings("unused")
	private Integer laneSwitch(String lane) {
		switch(lane) {
		case "Bottom": return 0;
		case "Support": return 1;
		case "Jungler": return 2;
		case "Mid": return 3;
		case "Top": return 4;
		default: return -1;
		}
	}
	
	@SuppressWarnings("unused")
	private int roleSwitch(String role) {
		switch(role) {
		case "Assassin": return 0;
		case "Fighter": return 1;
		case "Hybrid": return 2;
		case "Mage": return 3;
		case "Magical Damage": return 4;
		case "Physical Damage": return 5;
		case "Tank": return 6;
		case "Support": return 7;
		default: return -1;
		}
	}
	
	private int countRoles(Document document) {
		return document.select(".role").size() + 1;
	}
	
	private int countLanes(Document document) {
		return document.select("div.lanes > div.lane").size() + 1;
	}
}
