package com.lolpick.lolcounter.dao;

import java.util.Set;

import com.lolpick.lolcounter.entity.Champion;
import com.lolpick.lolcounter.entity.Role;

public interface RoleDao {
	public boolean create(Set<Role> roles);
	public Role read(String role);
	public boolean update(Set<Role> roles, Champion champion);
}
