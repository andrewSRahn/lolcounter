package com.lolpick.lolcounter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="champion")
public class Champion {
	@Id
	private Integer id;
	
	@Column
	private String name;

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

	@Override
	public String toString() {
		return "Champion [id=" + id + ", name=" + name + "]";
	}
}
