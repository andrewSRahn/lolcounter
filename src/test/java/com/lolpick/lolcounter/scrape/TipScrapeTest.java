package com.lolpick.lolcounter.scrape;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Tip;

public class TipScrapeTest {

	@Test
	public void testAkali() {
		Champion akali = mock(Champion.class);
		when(akali.getName()).thenReturn("Akali");
		when(akali.getId()).thenReturn(3);
		
		Champion lee = mock(Champion.class);
		when(lee.getName()).thenReturn("Lee Sin");
		when(lee.getId()).thenReturn(62);
		
		Champion syndra = mock(Champion.class);
		when(syndra.getName()).thenReturn("Syndra");
		when(syndra.getId()).thenReturn(107);
		
		TipScrape tipScrape = new TipScrape(akali);
		
		Set<Tip> tips = tipScrape.getTips();
		Set<Tip> should = Stream.of(
			new Tip(1164, null, "After level 6, try to prevent her use of Mark of the Assassin, as she can proc it twice using her dashes."),
			new Tip(348, lee, "Your Q and your E can reveal Akali in stealth. Use this to your advantage."),
			new Tip(-39, syndra, "Upgrading your trinket to a pink totem can give you a permanent pink to throw down when you need it."))
				.collect(Collectors.toSet());
		
		System.out.println(should);
		System.out.println(tips);
		assertTrue(tips.containsAll(should));
	}

}
