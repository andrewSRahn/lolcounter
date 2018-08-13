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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lanes == null) ? 0 : lanes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Champion other = (Champion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lanes == null) {
			if (other.lanes != null)
				return false;
		} else if (!lanes.equals(other.lanes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}
}
