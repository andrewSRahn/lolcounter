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

	public TipScrape(Champion champion) {
		this.champion = champion;
		this.tips = scrapeChampion();
	}
	
	private Set<Tip> scrapeChampion() {
		Set<Tip> tips = new TreeSet<>();
		
		return tips;
	}
	
	public Set<Tip> scrapePage(){
		Set<Tip> tips = new TreeSet<>();
		
		return tips;
	}
	
	public int count() {
		String name = this.champion.getName().toLowerCase().replace(" ", "").replace("'", "");
		String url = "https://lolcounter.com/tips/" + name + "/all?page=5";
		
		try {
			Document document = Jsoup.connect(url).get();
			String left = "#tips-matchup > div.left.pagination > a:last-child";
			String leftText = document.select(left).text();
			System.out.println(url + ":" + leftText);
			
			String more = "#tips-matchup > div.paginationmore > span > em > a";
			String moreText = document.select(more).text();
			System.out.println(url + ":" + moreText);
			
			
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
