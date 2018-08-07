package com.lolpick.lolcounter.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

@Entity
@Table(name="lane")
public class Lane {
	@Id
	@Column(name="lane_id")
	private Integer id;
	
	@Column(name="champion_lane")
	private String lane;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="champion_lane", 
			joinColumns= {@JoinColumn(name="champion_lane")},
			inverseJoinColumns= {@JoinColumn(name="champion_name")})
	@SequenceGenerator(
			name="champion_lane_sequence", 
			initialValue=1, 
			allocationSize=500)
	@CollectionId(
			columns = @Column(name="champion_lane_id"),
			type = @Type(type="long"),
			generator = "champion_lane_sequence")
	private List<Champion> champions;
	
	public Lane(Integer id, String lane) {
		super();
		this.id = id;
		this.lane = lane;
		this.champions = new ArrayList<>();
	}

	public Lane() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getLane() {
		return lane;
	}

	public List<Champion> getChampions() {
		return champions;
	}

	public void setChampions(List<Champion> champions) {
		this.champions = champions;
	}

	@Override
	public String toString() {
		return "Lane [id=" + id + ", lane=" + lane + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lane == null) ? 0 : lane.hashCode());
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
		Lane other = (Lane) obj;
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
		return true;
	}
}
