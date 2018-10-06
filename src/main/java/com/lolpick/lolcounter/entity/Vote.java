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
	private String us;
	
	@Column
	private String them;
	
	@Column
	private String lane;
	
	@Column
	private Integer up;
	
	@Column
	private Integer down;
	
	public Vote() {
		super();
	}

	public Vote(Integer id, Power power, String us, String them, String lane, Integer up, Integer down) {
		super();
		this.id = id;
		this.power = power;
		this.us = us;
		this.them = them;
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

	public String getUs() {
		return us;
	}

	public void setUs(String us) {
		this.us = us;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
		this.power = power;
	}

	public String getThem() {
		return them;
	}

	public void setThem(String them) {
		this.them = them;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((down == null) ? 0 : down.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lane == null) ? 0 : lane.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((them == null) ? 0 : them.hashCode());
		result = prime * result + ((up == null) ? 0 : up.hashCode());
		result = prime * result + ((us == null) ? 0 : us.hashCode());
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
		if (down == null) {
			if (other.down != null)
				return false;
		} else if (!down.equals(other.down))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lane == null) {
			if (other.lane != null)
				return false;
		} else if (!lane.equals(other.lane))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		if (them == null) {
			if (other.them != null)
				return false;
		} else if (!them.equals(other.them))
			return false;
		if (up == null) {
			if (other.up != null)
				return false;
		} else if (!up.equals(other.up))
			return false;
		if (us == null) {
			if (other.us != null)
				return false;
		} else if (!us.equals(other.us))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vote [id=" + id + ", power=" + power + ", us=" + us + ", them=" + them + ", lane=" + lane + ", up=" + up
				+ ", down=" + down + "]";
	}
	
}