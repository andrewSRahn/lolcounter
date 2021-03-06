package com.lolpick.lolcounter.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.lolpick.lolcounter.daoimpl.RoleDaoImpl;
import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Role;

public class RoleService {
	private static RoleDaoImpl dao = new RoleDaoImpl();
	
	public static boolean initialize() {
		Set<Role> roles = Stream.of(
				new Role(0, "Assassin"),
				new Role(1, "Fighter"),
				new Role(2, "Hybrid"),
				new Role(3, "Mage"),
				new Role(4, "Magical Damage"),
				new Role(5, "Physical Damage"),
				new Role(6, "Tank"),
				new Role(7, "Support"))
				.collect(Collectors.toSet());
		
		return create(roles);		
	}
	
	public static boolean create(Set<Role> roles) {
		return dao.create(roles);
	}
	
	public static Role read(String role) {
		return dao.read(role);
	}
	
	public static boolean update(Set<Role> roles, Champion champion) {
		return dao.update(roles, champion);
	}
}
