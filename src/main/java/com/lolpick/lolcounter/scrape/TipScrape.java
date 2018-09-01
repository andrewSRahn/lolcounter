package com.lolpick.lolcounter.scrape;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.service.TipService;
import com.lolpick.lolcounter.utility.ChampionUtil;

public class TipScrape {
	private Logger logger;
	private Champion champion;
	private Set<Tip> tips;
	
	private String name; 
	private String base; 

	public TipScrape(Champion champion) {
		this.champion = champion;
		this.name = this.champion.getName().toLowerCase().replace(" ", "").replace(".","").replace("'", "");
		this.base = "https://lolcounter.com/tips/" + name + "/all?page=";
		this.logger = LoggerFactory.getLogger(TipScrape.class);
		this.tips = scrapeChampion();
		TipService.create(this.tips);
	}
	
	public Set<Tip> scrapeChampion() {
		Set<Tip> tips = new TreeSet<>();
		
		for(int i=1; i<=countChampion(); i++)
			tips.addAll(scrapePage(this.base + i));
		
		return tips;
	}
	
	public Integer integerParseInt(String string) {
		if(string.contains(".")) {	
			String thousand = string.substring(0, string.indexOf("."));
			String hundred = string.substring(string.indexOf(".")+1, string.length()-1);
			
			Integer integerVote = 0;
			integerVote += new Integer(1000) * Integer.parseInt(thousand);
			integerVote += new Integer(100) * Integer.parseInt(hundred);
			return integerVote;
		} else {
			String thousand = string.substring(0, string.indexOf("k"));
			Integer integerVote = 0;
			integerVote += new Integer(1000) * Integer.parseInt(thousand);
			return integerVote;
		}
	}
	
	public Set<Tip> scrapePage(String url){
		try {
			Document document = Jsoup.connect(url).get();
			
			Elements voteElements = document.select(".tips > .votes");
			Elements themElements = document.select(".tips > .champ-img");
			Elements tipElements = document.select(".tips > .tip");
			
			List<String> tipsString = tipElements.
					stream().
					map(tip -> tip.text()).
					map(tip -> tip.substring(0, tip.contains(" Report Submitted By") ? tip.indexOf(" Report Submitted By") : tip.length())).
					collect(Collectors.toList());

			List<Integer> votes = new ArrayList<>();
			
			for(Element voteElement: voteElements) {
				String vote = voteElement.text();
				
				if(vote.contains("k")) 
					votes.add(integerParseInt(vote));
				else
					votes.add(Integer.parseInt(vote));
						
			}

			List<Champion> them = new ArrayList<>();
			
			for(Element themElement: themElements) {
				String find = themElement.attr("find");
				
				if(find.equals("generic"))
					them.add(null);
				else if(find.contains(" ") || find.contains("'") || find.contains(".")) 
					them.add(ChampionService.readChampion(ChampionUtil.expandChampionName(find)));
				else
					them.add(ChampionService.readChampion(find.substring(0, 1).toUpperCase() + find.substring(1, find.length())));
			}
			
			return createTips(votes, them, tipsString);
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	public Set<Tip> createTips(List<Integer> votes, List<Champion> them, List<String> tips){
		Set<Tip> tipSet = new TreeSet<>();

		for(int i=0; i<votes.size(); i++)
			tipSet.add(new Tip(votes.get(i), this.champion, them.get(i), tips.get(i)));

		return tipSet;
	}
	
	public int countPage(int page) {
		try {
			Document document = Jsoup.connect(this.base + page).get();
			return document.select("[class ^= tips _]").size();
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		
		return 0;
	}
	
	public int countChampion() {
		return countChampionTail(this.base, this.base + "5");
	}
	
	public int countChampionTail(String base, String url) {
		try {
			Document document = Jsoup.connect(url).get();
			String left = "#tips-matchup > div.left.pagination > a:last-child";
			String leftText = document.select(left).text();
			
			String more = "#tips-matchup > div.paginationmore > span > em > a";
			String moreText = document.select(more).text();
			
			if(!moreText.equals(">>"))
				return Integer.parseInt(leftText);
			else
				return countChampionTail(base, base + leftText);
			
		} catch(Exception e) {
			logger.error(e.getMessage());
		} 
		return 0;
	}

	public Champion getChampion() {
		return champion;
	}

	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	public Set<Tip> getTips() {
		return tips;
	}

	public void setTips(Set<Tip> tips) {
		this.tips = tips;
	}
}
