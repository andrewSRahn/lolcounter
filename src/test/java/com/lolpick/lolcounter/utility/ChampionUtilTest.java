package com.lolpick.lolcounter.utility;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChampionUtilTest {

	@Test
	public void testCho() {
		assertEquals("Cho'Gath", ChampionUtil.expandChampionName("chogath"));
		assertEquals("Xin Zhao", ChampionUtil.expandChampionName("xinzhao"));
	}

}
