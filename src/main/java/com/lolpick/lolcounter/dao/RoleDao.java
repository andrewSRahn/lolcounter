package com.lolpick.lolcounter.dao;

import java.util.Set;

import com.lolpick.lolcounter.entity.Role;

public interface RoleDao {
	public boolean create(Set<Role> roles);
	public boolean create(Set<Role> roles, String champion);
	public Role read(String role);
	public boolean update(Set<Role> roles);
}
