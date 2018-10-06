package com.lolpick.lolcounter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vote")
public class Vote {
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="power_id", nullable=false)
	private Power power;
	
	@Column
	private String name;
	
	@Column
	private String lane;
	
	@Column
	private Integer up;
	
	@Column
	private Integer down;
	
	public Vote() {
		super();
	}

	public Vote(Integer id, Power page, String name, String lane, Integer up, Integer down) {
		super();
		this.id = id;
		this.power = page;
		this.name = name;
		this.lane = lane;
		this.up = up;
		this.down = down;
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

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public Integer getUp() {
		return up;
	}

	public void setUp(Integer up) {
		this.up = up;
	}

	public Integer getDown() {
		return down;
	}

	public void setDown(Integer down) {
		this.down = down;
	}

	public Power getPage() {
		return power;
	}

	public void setPage(Power page) {
		this.power = page;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lane == null) ? 0 : lane.hashCode());
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
		Vote other = (Vote) obj;
		if (lane == null) {
			if (other.lane != null)
				return false;
		} else if (!lane.equals(other.lane))
			return false;
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
		return "Block [id=" + id + ", page=" + power + ", name=" + name + ", lane=" + lane + ", up="
				+ up + ", down=" + down + "]";
	}
}