package com.lolpick.lolcounter.scrape;

import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;

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
		
		for(int i = 1; i <= countChampion(); i++)
			System.out.println(this.base + i);
		
		return tips;
	}
	
	public Set<Tip> scrapePage(){
		Set<Tip> tips = new TreeSet<>();
		
		String votes = "#tips-matchup > div.tips.ss > div > div.votes";
		String image = "#tips-matchup > div.tips.ff > div.tips._1 > div.left.champ-img";
		return null;
	}
	
	public int countPage(int page) {
		return 0;
	}
	
	public int countChampion() {
		return connect(this.base, this.base + "5");
	}
	
	public int connect(String base, String url) {
		try {
			Document document = Jsoup.connect(url).get();
			String left = "#tips-matchup > div.left.pagination > a:last-child";
			String leftText = document.select(left).text();
			
			String more = "#tips-matchup > div.paginationmore > span > em > a";
			String moreText = document.select(more).text();
			
			if(!moreText.equals(">>"))
				return Integer.parseInt(leftText);
			else
				return connect(base, base + leftText);
			
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
