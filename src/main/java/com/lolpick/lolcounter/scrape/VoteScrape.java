package com.lolpick.lolcounter.scrape;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Vote;
import com.lolpick.lolcounter.service.VoteService;

public class VoteScrape {
	private String url;
	private List<Vote> votes;
	private Logger logger;
	
	public List<Vote> getVotes(){
		return this.votes;
	}

	public VoteScrape(Champion champion, String relation) {
		String name = champion.getName().toLowerCase().replace("'", "").replace(".", "").replace(" ", "");
		this.url = "https://lolcounter.com/champions/" + name + "/" + relation.toLowerCase();
		this.logger = LoggerFactory.getLogger(VoteScrape.class);
		this.votes = scrapee(name, relation, champion.getId());
		try {
			boolean result = insert(this.url, champion.getName(), relation, champion.getId());
			
			if (result)
				logger.info(this.url);
			else
				logger.info(this.url + " failed");
		} catch (Exception e) {
			logger.error(this.url + " failed");
		}
	}

	private List<Vote> scrapee(String us, String voteString, Integer championId) {
		Document document = null;
		List<Vote> votes = new ArrayList<>();
		try {
			 document = Jsoup.connect(this.url).get();
			 
			 for(int i = 2; i < count(document); i++) {
					String nameSelector = "#championPage > div.block3._all > div > div > div > div:nth-child(" + i
							+ ") > div.left.theinfo > a > div";
					String laneSelector = "#championPage > div.block3._all > div > div > div > div:nth-child(" + i
							+ ") > div.left.theinfo > div.info > div";
					String upSelector = "#championPage > div.block3._all > div > div > div > div:nth-child(" + i
							+ ") > div.left.theinfo > div.info > a:nth-child(2)";
					String downSelector = "#championPage > div.block3._all > div > div > div > div:nth-child(" + i
							+ ") > div.left.theinfo > div.info > a:nth-child(3) > div";

					Element name = document.selectFirst(nameSelector);
					Element lane = document.selectFirst(laneSelector);
					Element up = document.selectFirst(upSelector);
					Element down = document.selectFirst(downSelector);

					String themString = name.text();
					String laneString = lane.text();

					// the numbers contain , between thousands and hundreds
					String upString = up.text().replace(",", "");
					String downString = down.text().replaceAll(",", "");

					Integer upInteger = Integer.parseInt(upString);
					Integer downInteger = Integer.parseInt(downString);

					Vote vote = new Vote(
							pageId(championId, voteString),
							voteString,
							us,
							themString,
							laneString,
							upInteger,
							downInteger
							);
					
					votes.add(vote);
			 }
			 
		} catch(Exception e) {
			logger.error(e.toString());
		}
		
		return votes;
	}

	private boolean insert(String url, String champion, String vote, Integer championId) {
		return VoteService.createBlocks(scrapee(champion, vote, championId));
	}

	private static int relationSwitch(String voteString) {
		switch (voteString) {
		case "Weak":
			return 1;
		case "Strong":
			return 2;
		case "Even":
			return 3;
		case "Good":
			return 4;
		default:
			return 0;
		}
	}

	private static int constantSwitch(String relation) {
		switch (relation) {
		case "Weak":
			return 3;
		case "Strong":
			return 2;
		case "Even":
			return 1;
		case "Good":
			return 0;
		default:
			return 0;
		}
	}

	public static int count(Document document) {
		return document.select(".champ-block").size() - 1;
	}

	/*
	 * Champion, relation, and page ids are dependent upon one another because there
	 * are four Pages for 140 Champions and each page must be identified uniquely.
	 * 
	 * c = champion id {1 - 140} r = relation id {1 - 4}: 1=weak, 2=strong, 3=even,
	 * 4=good m = constant {1 - 4}: (id % 4) == 1 return 3 (id % 4) == 2 return 2
	 * (id % 4) == 3 return 1 (id % 4) == 0 return 0 p = page id {1 - 560}
	 * 
	 * p = cr + m(c-1)
	 * 
	 */
	
	// smelly code.. this is not getting called and it is affecting tests VoteScrapeTests
	// Block indices no longer map correctly to a specific function, however i am not sure if this matters..
	private static int blockId(Integer pageId, Integer blockIndex) {
		return 20 * pageId + blockIndex - 20;
	}

	// equation for page id can be found in com.lolpick.lolcounter.entity.Page
	private static int pageId(Integer championId, String vote) {
		return championId * relationSwitch(vote) + constantSwitch(vote) * (championId - 1);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
