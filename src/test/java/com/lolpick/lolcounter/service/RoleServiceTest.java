package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Role;

public class RoleServiceTest {
	
	@Test
	public void testInitialize() {
		assertTrue(RoleService.initialize());
	}
	
	@Test
	public void testRead() {
		assertTrue(ChampionService.readChampion("Amumu").getRoles().contains(new Role(6, "Tank")));
		assertTrue(ChampionService.readChampion("Amumu").getRoles().contains(new Role(4, "Magical Damage")));
//		assertTrue(ChampionService.readChampion("Blitzcrank").getRoles().contains(new Role(3, "Mage")));
//		assertTrue(ChampionService.readChampion("Blitzcrank").getRoles().contains(new Role(4, "Magical Damage")));
//		assertTrue(ChampionService.readChampion("Leona").getRoles().contains(new Role(5, "Physical Damage")));
//		assertTrue(ChampionService.readChampion("Leona").getRoles().contains(new Role(6, "Tank")));
//		assertTrue(ChampionService.readChampion("Janna").getRoles().contains(new Role(4, "Magical Damage")));
	}
	
	@Test
	public void testCreate() {
		List<Role> roles = Arrays.asList(
				RoleService.read("Tank"),
				RoleService.read("Magical Damage"));
		assertTrue(RoleService.create(roles, "Amumu"));
		
	}
}
