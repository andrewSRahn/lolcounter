package com.lolpick.lolcounter.scrape;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lolpick.lolcounter.entity.Lane;
import com.lolpick.lolcounter.entity.Role;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.LaneService;
import com.lolpick.lolcounter.service.RoleService;

public class LaneRoleScrape {
	private List<Lane> lanes;
	private List<Role> roles;
	private Integer championId;
	private String name;
	
	public LaneRoleScrape(String name) {
		this.lanes = new ArrayList<>();
		this.roles = new ArrayList<>();
		this.championId = ChampionService.readChampion(name).getId();
		this.name = name;
	}
	
	public List<Lane> getLanes() {
		return lanes;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public boolean scrape() {
		Document document = null;
		
		try {
			String url = "https://lolcounter.com/champions/" + this.name;
			document = Jsoup.connect(url).get();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		for(int i = 1; i < countRoles(document); i++) {
			String selector = "div.role:nth-child(" + i + ")";
			String role = document.select(selector).text();
			Integer id = roleSwitch(role);
			this.roles.add(new Role(id, role));
		}

		for(int i = 1; i < countLanes(document); i++) {
			String selector = "div.lanes > div.lane:nth-child(" + i + ")";
			String lane = document.select(selector).text();
			Integer id = laneSwitch(lane);
			this.lanes.add(new Lane(id, lane));
		}
		
		boolean bottom = lanes.contains(new Lane(0, "Bottom"));
		boolean support = roles.contains(new Role(7, "Support"));
		
		if(bottom && support) {
			lanes.remove(new Lane(0, "Bottom"));
			roles.remove(new Role(7, "Support"));
			lanes.add(new Lane(1, "Support"));
		}
		System.out.println(this.championId);
		System.out.println(this.name);
		System.out.println(this.lanes);
		System.out.println(this.roles);
		
		return true;
	}
	
	public boolean insert() {
		boolean lane = LaneService.create(this.lanes, this.name);
		boolean role = RoleService.create(this.roles, this.name);
		return lane && role;
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
