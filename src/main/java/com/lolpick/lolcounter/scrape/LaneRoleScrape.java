package com.lolpick.lolcounter.scrape;

import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.RoleService;

public class LaneRoleScrape {	
	private Champion champion;
	
	public LaneRoleScrape(Champion champion) {
		this.champion = champion;
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
			e.printStackTrace();
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
		
		boolean bottom = this.champion.getLanes().contains(new Lane(0, "Bottom"));
		boolean support = this.champion.getRoles().contains(new Role(7, "Support"));
		
		if(bottom && support) {
			this.champion.getLanes().remove(new Lane(0, "Bottom"));
			this.champion.getRoles().remove(new Role(7, "Support"));
			this.champion.getLanes().add(new Lane(1, "Support"));
		}
		
		return true;
	}
	
	public boolean insert() {
		boolean lane = LaneService.create(this.champion.getLanes(), this.champion.getName());
		boolean role = RoleService.create(this.champion.getRoles(), this.champion.getName());
		boolean champion = ChampionService.updateChampion(this.champion);
		return lane && role && champion;
	}
	
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
