package com.lolpick.lolcounter.utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChampionUtilTest {

	@Test
	public void testCho() {
		assertEquals("Cho'Gath", ChampionUtil.expandChampionName("chogath"));
		assertEquals("Xin Zhao", ChampionUtil.expandChampionName("xinzhao"));
	}
	
	@Test
	public void testId() {
		assertEquals(ChampionUtil.riotifyLolcounterChampionId("Aatrox"), new Integer(266));
		assertEquals(ChampionUtil.riotifyLolcounterChampionId("Irelia"), new Integer(39));
		assertEquals(ChampionUtil.riotifyLolcounterChampionId("Zyra"), new Integer(143));
		assertEquals(ChampionUtil.riotifyLolcounterChampionId("Chogath"), new Integer(31));
	}

}
