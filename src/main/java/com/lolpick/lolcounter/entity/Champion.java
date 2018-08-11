package com.lolpick.lolcounter.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="champion")
public class Champion {
	@Id
	@Column(name="champion_id")
	private Integer id;
	
	@Column
	private String name;
	
	@ManyToMany(mappedBy="champions")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<Lane> lanes;
	
	@ManyToMany(mappedBy="champions")
	@Cascade({CascadeType.SAVE_UPDATE})
	private Set<Role> roles;

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

	public Set<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(Set<Lane> lanes) {
		this.lanes = lanes;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Champion [id=" + id + ", name=" + name + ", lanes=" + lanes + ", roles=" + roles + "]";
	}
}
