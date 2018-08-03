package com.lolpick.lolcounter.dao;

import java.util.List;

import com.lolpick.lolcounter.entity.Role;

public interface RoleDao {
	public boolean create(List<Role> roles);
	public boolean create(List<Role> roles, String champion);
	public List<Role> readChampion(String champion);
}
