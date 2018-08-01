package com.lolpick.lolcounter.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lolpick.lolcounter.entity.Role;

public class RoleServiceTest {
	
	@Test
	public void testInitialize() {
		assertTrue(RoleService.initialize());
	}
	
	@Test
	public void testRead() {
		assertTrue(RoleService.readChampion("Amumu").contains(new Role(6, "Tank")));
		assertTrue(RoleService.readChampion("Amumu").contains(new Role(4, "Magical Damage")));
		assertTrue(RoleService.readChampion("Blitzcrank").contains(new Role(3, "Mage")));
		assertTrue(RoleService.readChampion("Blitzcrank").contains(new Role(4, "Magical Damage")));
		assertTrue(RoleService.readChampion("Leona").contains(new Role(5, "Physical Damage")));
		assertTrue(RoleService.readChampion("Leona").contains(new Role(6, "Tank")));
		assertTrue(RoleService.readChampion("Janna").contains(new Role(4, "Magical Damage")));
	}
}
