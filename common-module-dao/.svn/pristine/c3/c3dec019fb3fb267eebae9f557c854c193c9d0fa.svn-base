package com.module.dao.entity.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

 
@Entity
@Table(name = "Region")
public class Region implements Serializable {

	private static final long serialVersionUID = -3778846042841381462L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Long parentId;

	@Column
	private String regionId;

	@Column
	private String countryCode;

	@Column
	private String regionEngName;

	@Column
	private String regionChiName;

	@Column
	private String countryEngName;

	@Column
	private String countryChiName;

	@Column
	private Date createDate;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRegionEngName() {
		return regionEngName;
	}

	public void setRegionEngName(String regionEngName) {
		this.regionEngName = regionEngName;
	}

	public String getRegionChiName() {
		return regionChiName;
	}

	public void setRegionChiName(String regionChiName) {
		this.regionChiName = regionChiName;
	}

	public String getCountryEngName() {
		return countryEngName;
	}

	public void setCountryEngName(String countryEngName) {
		this.countryEngName = countryEngName;
	}

	public String getCountryChiName() {
		return countryChiName;
	}

	public void setCountryChiName(String countryChiName) {
		this.countryChiName = countryChiName;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((countryCode == null) ? 0 : countryCode.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((regionEngName == null) ? 0 : regionEngName.hashCode());
		result = prime * result + ((regionId == null) ? 0 : regionId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Region other = (Region) obj;
		if (countryCode == null) {
			if (other.countryCode != null)
				return false;
		} else if (!countryCode.equals(other.countryCode))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (regionEngName == null) {
			if (other.regionEngName != null)
				return false;
		} else if (!regionEngName.equals(other.regionEngName))
			return false;
		if (regionId == null) {
			if (other.regionId != null)
				return false;
		} else if (!regionId.equals(other.regionId))
			return false;
		return true;
	}

	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
