package com.lolpick.lolcounter.scrape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.lolpick.lolcounter.entity.Block;
import com.lolpick.lolcounter.entity.Page;
import com.lolpick.lolcounter.service.BlockService;
import com.lolpick.lolcounter.service.PageService;

public class PageScrape {
	
	public static Page scrape(String url, String champion, String relation, Integer id) {
		List<Block> blocks = new ArrayList<>();
		Page page = new Page(id, champion, relation, blocks);
		Document document = null;
		try {
			document = Jsoup.connect(url).get();
		} catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println(url);
		for(int i = 2; i < count(document); i++) {
			String imageSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > a > div";
			String nameSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > a > div";
			String laneSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > div.info > div";
			String upSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > div.info > a:nth-child(2)";
			String downSelector = 
					"#championPage > div.block3._all > div > div > div > div:nth-child(" + i + ") > div.left.theinfo > div.info > a:nth-child(3) > div";
			
			Element image = document.selectFirst(imageSelector);
			Element name = document.selectFirst(nameSelector);
			Element lane = document.selectFirst(laneSelector);
			Element up = document.selectFirst(upSelector);
			Element down = document.selectFirst(downSelector);
			
			// <div find="morgana" class="left champ-img" style="background: url(http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/Morgana.png); background-size: cover;"></div>
			// ^ this ugly piece of html is why the following two lines of code are required
			// String style and imageString get the ddragon url from the style attribute
			String style = parseImage(image, champion);
			String imageString = style.substring(style.indexOf("(") + 1, style.indexOf(")"));
			// end 
			
			String nameString = name.text();
			String laneString = lane.text();
			
			// the numbers contain , between thousands and hundreds
			String upString = up.text().replace(",", "");
			String downString = down.text().replaceAll(",", "");
			
			Integer upInteger = Integer.parseInt(upString);
			Integer downInteger = Integer.parseInt(downString);
			
			// equation for block id can be found in com.lolpick.lolcounter.entity.Block
			Integer newId = 20 * relationSwitch(relation) * id - 79;
			newId = newId + i - 1;
			
			blocks.add(new Block(newId, page, nameString, imageString, laneString, upInteger, downInteger));
			page.setBlocks(blocks);
		}
		
		return page;
	}
	
	private static String parseImage(Element image, String champion) {
//		String string = null;
//		try {
//			string = image.attr("style");
//		} catch(Exception e) {
//			e.printStackTrace();
//			string = "(http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/" + champion + ".png)";
//		}
		return "(http://ddragon.leagueoflegends.com/cdn/8.12.1/img/champion/" + champion + ".png)";
	}

	public void insert(String url, String champion, String relation, Integer id) {
		Page page = scrape(url, champion, relation, id);
		PageService.create(page);
		BlockService.createBlocks(page.getBlocks());
	}
	
	private static int relationSwitch(String relation) {
		switch(relation) {
		case "Strong": return 1;
		case "Weak": return 2;
		case "Even": return 3;
		case "Good": return 4;
		default: return 0;
		}
	}

	public static int count(Document document) {
		String selector = ".champ-block";
		
		Elements elements = document.select(selector);
		return elements.size()-1;
	}
}
