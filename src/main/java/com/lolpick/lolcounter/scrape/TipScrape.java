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

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;
import com.lolpick.lolcounter.service.ChampionService;
import com.lolpick.lolcounter.utility.ChampionUtil;

public class TipScrape {
	private Champion champion;
	private Set<Tip> tips;
	
	private String name; 
	private String base; 

	public TipScrape(Champion champion) {
		this.champion = champion;
		this.name = this.champion.getName().toLowerCase().replace(" ", "").replace("'", "");
		this.base = "https://lolcounter.com/tips/" + name + "/all?page=";
		this.tips = scrapeChampion();
	}
	
	public Set<Tip> scrapeChampion() {
		Set<Tip> tips = new TreeSet<>();
		
		for(int i=1; i<=countChampion(); i++)
			tips.addAll(scrapePage(this.base + i));
		
		return tips;
	}
	
	public Set<Tip> scrapePage(String url){
		try {
			Document document = Jsoup.connect(url).get();
			
			Elements votesElements = document.select(".tips > .votes");
			Elements themElements = document.select(".tips > .champ-img");
			Elements tipsElements = document.select(".tips > .tip");
			
			List<Integer> votes = votesElements.stream().map(vote -> Integer.parseInt(vote.text())).collect(Collectors.toList());
			List<Champion> them = new ArrayList<>();
			List<String> tipsString = tipsElements.stream().map(tip -> tip.text()).collect(Collectors.toList());
			
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
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Set<Tip> createTips(List<Integer> votes, List<Champion> them, List<String> tips){
		Set<Tip> tipSet = new TreeSet<>();

		for(int i=0; i<votes.size(); i++)
			tipSet.add(new Tip(votes.get(i), this.champion, them.get(i), tips.get(i)));

		System.out.println(tipSet);
		return tipSet;
	}
	
	public int countPage(int page) {
		try {
			Document document = Jsoup.connect(this.base + page).get();
			return document.select("[class ^= tips _]").size();
		} catch(Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
