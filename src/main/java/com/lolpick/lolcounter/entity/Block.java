package com.lolpick.lolcounter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Block, Champion, and Page entities are dependent upon one another because Pages are for 140 Champions 
 * which contains 20 Blocks. To track block id in the database, there must be relationships to identify 
 * records.
 * 
 * b = block id {1 - 11,200}
 * c = champion id {1 - 140}
 * r = relation id {1 - 4}:  1=weak, 2=strong, 3=even, 4=good
 * 
 * upper b = 20cr
 * lower b = 20cr - 79
 */
@Entity
@Table(name="block")
public class Block {
	@Id
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="page_id", nullable=false)
	private Page page;
	
	@Column
	private String name;
	
	@Column
	private String image;
	
	@Column
	private String lane;
	
	@Column
	private Integer up;
	
	@Column
	private Integer down;
	
	public Block() {
		super();
	}

	public Block(Integer id, Page page, String name, String image, String lane, Integer up, Integer down) {
		super();
		this.id = id;
		this.page = page;
		this.name = name;
		this.image = image;
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

	public String getImage() {
		return image;
	}

	public void setUrl(String image) {
		this.image = image;
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

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result + ((lane == null) ? 0 : lane.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((page == null) ? 0 : page.hashCode());
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
		Block other = (Block) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
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
		if (page == null) {
			if (other.page != null)
				return false;
		} else if (!page.equals(other.page))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Block [id=" + id + ", page=" + page + ", name=" + name + ", image=" + image + ", lane=" + lane + ", up="
				+ up + ", down=" + down + "]";
	}
}