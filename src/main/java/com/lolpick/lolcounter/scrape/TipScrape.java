package com.lolpick.lolcounter.scrape;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;

public class TipScrape {
	private Champion champion;
	private Set<Tip> tips;

	public TipScrape(Champion champion) {
		this.champion = champion;
		this.tips = scrape();
	}
	
	private Set<Tip> scrape() {
		Champion lee = mock(Champion.class);
		when(lee.getName()).thenReturn("Lee Sin");
		when(lee.getId()).thenReturn(62);
		
		Champion syndra = mock(Champion.class);
		when(syndra.getName()).thenReturn("Syndra");
		when(syndra.getId()).thenReturn(107);
		
		Set<Tip> should = Stream.of(
				new Tip(1164, null, "After level 6, try to prevent her use of Mark of the Assassin, as she can proc it twice using her dashes."),
				new Tip(348, lee, "Your Q and your E can reveal Akali in stealth. Use this to your advantage."),
				new Tip(-39, syndra, "Upgrading your trinket to a pink totem can give you a permanent pink to throw down when you need it."))
					.collect(Collectors.toSet());
		return should;
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
