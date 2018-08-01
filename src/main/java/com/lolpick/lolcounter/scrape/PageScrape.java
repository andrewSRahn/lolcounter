package com.lolpick.lolcounter.scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.lolpick.lolcounter.entity.Block;
import com.lolpick.lolcounter.entity.Page;
import com.lolpick.lolcounter.service.BlockService;
import com.lolpick.lolcounter.service.PageService;

public class PageScrape {
	public static Page scrape(String url, String champion, String relation, Integer championId) {
		Integer pageId = pageId(championId, relation);
		List<Block> blocks = new ArrayList<>();
		Page page = new Page(pageId, champion, relation, blocks);
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
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
			
			blocks.add(new Block(blockId, page, nameString, laneString, upInteger, downInteger));
			page.setBlocks(blocks);
		}
		
		return page;
	}

	public static boolean insert(String url, String champion, String relation, Integer championId) {
		Page page = scrape(url, champion, relation, championId);
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
	public static int blockId(Integer pageId, Integer blockIndex) {
		return 20*pageId + blockIndex - 20;
	}
	
	// equation for page id can be found in com.lolpick.lolcounter.entity.Page
	public static int pageId(Integer championId, String relation) {
		return championId * relationSwitch(relation) + constantSwitch(relation) * (championId - 1);
	}
}
