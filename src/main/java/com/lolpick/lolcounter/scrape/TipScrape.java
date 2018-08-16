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
		this.tips = all();
	}
	
	private Set<Tip> all() {
		Set<Tip> tips = new TreeSet<>();
		
		
//		while(current != last) {
//			tips.addAll(page());
//		}
		
		return tips;
	}
	
	public Set<Tip> page(String url){
		Set<Tip> tips = new TreeSet<>();
		
		
		
		return tips;
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
