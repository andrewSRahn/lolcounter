package com.lolpick.lolcounter.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ChampionUtil {
	final static List<String> riotgames = Arrays.asList(
			"AurelionSol",
			"DrMundo",
			"JarvanIV",
			"LeeSin",
			"MasterYi",
			"MissFortune",
			"TahmKench",
			"TwistedFate",
			"XinZhao",
			"Chogath",
			"Kaisa",
			"Khazix",
			"Kogmaw",
			"Reksai",
			"Velkoz");

	final static List<String> lowercase = Arrays.asList(
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
	
	final static List<String> lolcounter = Arrays.asList(
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
			"Kha'Zix",
			"Kogmaw",
			"Rek'Sai",
			"Vel'Koz");
	public static String expandChampionName(String name) {
		return lolcounter.get(lowercase.indexOf(name));
	}
	
	public static Integer riotifyLolcounterChampionId(String name) {
		Map<String, Integer> map = new HashMap<>();
		Scanner scanner;
		try {
			scanner = new Scanner(new File("src/main/resources/champion-id.txt"));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] lines = line.split(" ");
				map.put(lines[0], Integer.parseInt(lines[1]));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return map.get(name);
	}
	
	public static String riotifyLolcounterChampionName(String name) {
		if(name.equals("Vi"))
			return "Vi";
		
		if(lolcounter.contains(name))
			return riotgames.get(lolcounter.indexOf(name));
		
		return name;
	}
}
