package com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Head_Count")
public class Head_Count {
	
	@Id
	@Column(name="hcId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)   // or AUTO
	private int hcId;
	
	@Column(name="hcName")
	private String hcName;
	
	@Column(name="hcAge")
	private int hcAge;
	
	@Column(name="hcGender")
	private String hcGender;

	public int getHcId() {
		return hcId;
	}

	public void setHcId(int hcId) {
		this.hcId = hcId;
	}

	public String getHcName() {
		return hcName;
	}

	public void setHcName(String hcName) {
		this.hcName = hcName;
	}

	public int getHcAge() {
		return hcAge;
	}

	public void setHcAge(int hcAge) {
		this.hcAge = hcAge;
	}

	public String getHcGender() {
		return hcGender;
	}

	public void setHcGender(String hcGender) {
		this.hcGender = hcGender;
	}

	@Override
	public String toString() {
		return "Head_Count [hcId=" + hcId + ", hcName=" + hcName + ", hcAge=" + hcAge + ", hcGender=" + hcGender + "]";
	}

	public Head_Count(int hcId, String hcName, int hcAge, String hcGender) {
		super();
		this.hcId = hcId;
		this.hcName = hcName;
		this.hcAge = hcAge;
		this.hcGender = hcGender;
	}

	public Head_Count() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	

}
