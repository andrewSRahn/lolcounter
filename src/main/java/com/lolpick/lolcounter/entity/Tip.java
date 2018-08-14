package com.lolpick.lolcounter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tip")
public class Tip implements Comparable<Tip>{
	
	@Id
	@Column(name="tip_id")
	@SequenceGenerator(name="tip_sequence",
		initialValue=1,
		allocationSize=600)
	@GeneratedValue(generator="tip_sequence")
	private Integer id;
	
	@Column
	private Integer votes;
	
	@ManyToOne
	@JoinColumn(name="champion_id")
	private Champion champion;
	
	@Column
	private String tip;
	
	public Tip() {
		super();
	}

	public Tip(Integer votes, Champion champion, String tip) {
		super();
		this.id = null;
		this.votes = votes;
		this.champion = champion;
		this.tip = tip;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Champion getChampion() {
		return champion;
	}

	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	@Override
	public String toString() {
		return "Tip [id=" + id + ", votes=" + votes + ", champion=" + champion + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tip == null) ? 0 : tip.hashCode());
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
		Tip other = (Tip) obj;
		if (champion == null) {
			if (other.champion != null)
				return false;
		} else if (!champion.equals(other.champion))
			return false;
		if (tip == null) {
			if (other.tip != null)
				return false;
		} else if (!tip.equals(other.tip))
			return false;
		return true;
	}

	@Override
	public int compareTo(Tip o) {
		if(this.votes > o.getVotes())
			return 1;
		else if(this.votes < o.getVotes())
			return -1;
		else 
			return 0;
	}
}
