package com.lolpick.lolcounter.application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.lolpick.lolcounter.scrape.VoteScrape;
import com.lolpick.lolcounter.service.ChampionService;

public class Fail {
	public static void appendToFile(String file, String url, String name, String relation, Integer id) {
		try(FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw)){
		    out.println(url + ":" + name + ":" + relation + ":" + id.toString());
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static boolean retry(String file) {
		try(FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);){
			
			List<String> all = new ArrayList<>();
			String current = null;
			
			while((current = br.readLine()) != null) 
				all.add(current);
			
			for(int i = 0; i < all.size(); i++) {
				String lines[] = all.get(i).split(":");
				String name = lines[2];
				String relation = lines[3];
				
				remove(file, i+1);
				
				@SuppressWarnings("unused")
				VoteScrape scrape = new VoteScrape(
						ChampionService.readChampion(name),
						relation);
			}
			
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean remains(String file) {
		try(FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);){
			
			List<String> lines = new ArrayList<>();
			String line = null;
			
			while((line = br.readLine()) != null) 
				lines.add(line);
						
			if(lines.size() == 0)
				return false;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean remove(String file, int i) {
		try(FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);){
			
			List<String> lines = new ArrayList<>();
			String line = null;
			
			while((line = br.readLine()) != null) 
				lines.add(line);
			
			lines.remove(i-1);
			
			return write(file, lines);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean write(String file, List<String> lines) {
		try(FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);){
			
			for(String line: lines)
				bw.write(line + "\n");
			
		} catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static int count(String file) {
		int count = 0;
		try(FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);){

			while(br.readLine() != null)
				count++;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
}
