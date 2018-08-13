package com.lolpick.lolcounter.scrape;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Champion;

import static org.mockito.Mockito.*;

public class TipScrapeTest {

	@Test
	public void testAkali() {
		Champion akali = mock(Champion.class);
		when(akali.getName()).thenReturn("Akali");
		when(akali.getId()).thenReturn(3);
		
		
		TipScrape tipScrape = new TipScrape(akali);
		
	}

}
