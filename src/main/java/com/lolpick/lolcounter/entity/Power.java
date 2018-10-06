package com.lolpick.lolcounter.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Champion, relation, and page ids are dependent upon one another because there are four Pages
 * for 140 Champions and each page must be identified uniquely. 
 * 
 * c = champion id {1 - 140}
 * r = relation id {1 - 4}:  1=weak, 2=strong, 3=even, 4=good
 * m = constant {1 - 4}:
 *	(id % 4) == 1 return 3
 *	(id % 4) == 2 return 2
 *	(id % 4) == 3 return 1
 *	(id % 4) == 0 return 0
 * p = page id {1 - 560}
 * 
 * p = cr + m(c-1)
 * 
 */
@Entity
@Table(name="power")
public class Power {
	@Id
	Integer id;
	
	@Column
	String name;
	
	@Column
	String power;
	
	@OneToMany(mappedBy="power", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	List<Vote> votes;
	
	public Power() {}

	public Power(Integer id, String name, String power, List<Vote> blocks) {
		super();
		this.id = id;
		this.name = name;
		this.power = power;
		this.votes = blocks;
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

	public String getRelation() {
		return power;
	}

	public void setRelation(String relation) {
		this.power = relation;
	}

	public List<Vote> getBlocks() {
		return votes;
	}

	public void setBlocks(List<Vote> blocks) {
		this.votes = blocks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
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
		Power other = (Power) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Page [id=" + id + ", name=" + name + ", relation=" + power + ", blocks=" + votes + "]";
	}
}
