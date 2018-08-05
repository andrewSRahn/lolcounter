package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		Set<Role> roles = Stream.of(
				RoleService.read("Tank"),
				RoleService.read("Magical Damage"))
				.collect(Collectors.toSet());
		assertTrue(RoleService.create(roles, "Amumu"));
		
	}
}
