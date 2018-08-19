package com.lolpick.lolcounter.utility;

import java.util.Arrays;
import java.util.List;

public class ChampionUtil {
	public static String expandChampionName(String name) {
		List<String> minimize = Arrays.asList(
				"aurelionsol",
				"drmundo",
				"jarvaniv",
				"leesin",
				"masteryi",
				"missfortune",
				"tahmkench",
				"twistedfate",
				"xinzhao",
				"chogath",
				"kaisa",
				"khazzix",
				"kogmaw",
				"reksai",
				"velkoz");
		
		List<String> expand = Arrays.asList(
				"Aurelion Sol",
				"Dr. Mundo",
				"Jarvan IV",
				"Lee Sin",
				"Master Yi",
				"Miss Fortune",
				"Tahm Kench",
				"Twisted Fate",
				"Xin Zhao",
				"Cho'Gath",
				"Kai'Sa",
				"Khaz'Zix",
				"Kog'Maw",
				"Rek'Sai",
				"Vel'Koz");
		
		return expand.get(minimize.indexOf(name));
	}
}
