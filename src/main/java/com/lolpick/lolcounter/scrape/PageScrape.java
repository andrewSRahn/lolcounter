package com.lolpick.lolcounter.scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.lolpick.lolcounter.application.Fail;
import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Relation;
import com.lolpick.lolcounter.service.BlockService;
import com.lolpick.lolcounter.service.PageService;

public class PageScrape {
	private String url;
	private Relation page;

	public PageScrape(Champion champion, String relation) {
		String name = champion.getName().toLowerCase().replace("'", "").replace(".", "").replace(" ", "");
		this.url = "https://lolcounter.com/champions/" + name + "/" + relation.toLowerCase();
		
		this.page = scrape(name, relation, champion.getId());
		try {
			if(insert(this.url, champion.getName(), relation, champion.getId()))
				System.out.println(this.url);
			else
				throw new Exception(this.url + " failed");
		} catch(Exception e) {
			Fail.appendToFile("src/main/resources/fails.txt", this.url, name, relation, champion.getId());
			System.out.println(this.url + " failed");
		}
	}
	
	private Relation scrape(String champion, String relation, Integer championId) {
		Integer pageId = pageId(championId, relation);
		List<Vote> blocks = new ArrayList<>();
		Relation page = new Relation(pageId, champion, relation, blocks);
		Document document = null;
		try {
			document = Jsoup.connect(this.url).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 2; i < count(document); i++) {
			String nameSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > a > div";
			String laneSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > div.info > div";
			String upSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > div.info > a:nth-child(2)";
			String downSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > div.info > a:nth-child(3) > div";
			
			Element name = document.selectFirst(nameSelector);
			Element lane = document.selectFirst(laneSelector);
			Element up = document.selectFirst(upSelector);
			Element down = document.selectFirst(downSelector);
			
			String nameString = name.text();
			String laneString = lane.text();
			
			// the numbers contain , between thousands and hundreds
			String upString = up.text().replace(",", "");
			String downString = down.text().replaceAll(",", "");
			
			Integer upInteger = Integer.parseInt(upString);
			Integer downInteger = Integer.parseInt(downString);
			
			Integer blockId = blockId(pageId, i-1);
			
			blocks.add(new Vote(blockId, page, nameString, laneString, upInteger, downInteger));
			page.setBlocks(blocks);
		}
		
		return page;
	}

	private boolean insert(String url, String champion, String relation, Integer championId) {
		Relation page = scrape(champion, relation, championId);
		boolean result = PageService.create(page);
		return result & BlockService.createBlocks(page.getBlocks());
	}
	
	private static int relationSwitch(String relation) {
		switch(relation) {
		case "Weak": return 1;
		case "Strong": return 2;
		case "Even": return 3;
		case "Good": return 4;
		default: return 0;
		}
	}
	
	private static int constantSwitch(String relation) {
		switch(relation) {
		case "Weak": return 3;
		case "Strong": return 2;
		case "Even": return 1;
		case "Good": return 0;
		default: return 0;
		}
	}

	public static int count(Document document) {
		return document.select(".champ-block").size()-1;
	}
	
	// equation for block id can be found in com.lolpick.lolcounter.entity.Block
	private static int blockId(Integer pageId, Integer blockIndex) {
		return 20*pageId + blockIndex - 20;
	}
	
	// equation for page id can be found in com.lolpick.lolcounter.entity.Page
	private static int pageId(Integer championId, String relation) {
		return championId * relationSwitch(relation) + constantSwitch(relation) * (championId - 1);
	}

	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Relation getPage() {
		return page;
	}

	public void setPage(Relation page) {
		this.page = page;
	}
}
