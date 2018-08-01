package com.lolpick.lolcounter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="champion")
public class Champion {
	@Id
	@Column(name="champion_id")
	private Integer id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy="champions", cascade=CascadeType.ALL)
	private List<Lane> lanes;
	
	@ManyToMany(mappedBy="champions", cascade=CascadeType.ALL)
	private List<Role> roles;

	public Champion(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Champion() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Champion [id=" + id + ", name=" + name + "]";
	}
}
