package com.lolpick.lolcounter.service;

import java.util.Arrays;
import java.util.List;

import com.lolpick.lolcounter.daoimpl.RoleDaoImpl;
import com.lolpick.lolcounter.entity.Role;

public class RoleService {
	private static RoleDaoImpl dao = new RoleDaoImpl();
	public static boolean create(List<Role> roles) {
		return dao.create(roles);
	}
	
	public static List<Role> readChampion(String champion){
		return dao.readChampion(champion);
	}

	public static boolean initialize() {
		List<Role> roles = Arrays.asList(
				new Role(0, "Assassin"),
				new Role(1, "Fighter"),
				new Role(2, "Hybrid"),
				new Role(3, "Mage"),
				new Role(4, "Magical Damage"),
				new Role(5, "Physical Damage"),
				new Role(6, "Tank"));
		
		return create(roles);		
	}
	
	public static boolean create(List<Role> roles, String champion) {
		return dao.create(roles, champion);
	}
}
