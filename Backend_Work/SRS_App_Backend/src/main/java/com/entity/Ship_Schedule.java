package com.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ship_Schedule")
public class Ship_Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ss_id;
	
	@Column(name="journeyDate")
	private String journeyDate;
	
	@Column(name="journeyTime")
	private String journeyTime;
	
	@Column(name="seatAvailability")
	private int seatAvailability;

	public int getSs_id() {
		return ss_id;
	}

	public void setSs_id(int ss_id) {
		this.ss_id = ss_id;
	}

	public String getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(String journeyDate) {
		this.journeyDate = journeyDate;
	}

	public String getJourneyTime() {
		return journeyTime;
	}

	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}

	public int getSeatAvailability() {
		return seatAvailability;
	}

	public void setSeatAvailability(int seatAvailability) {
		this.seatAvailability = seatAvailability;
	}

	@Override
	public String toString() {
		return "Ship_Schedule [ss_id=" + ss_id + ", journeyDate=" + journeyDate + ", journeyTime=" + journeyTime
				+ ", seatAvailability=" + seatAvailability + "]";
	}

	public Ship_Schedule(int ss_id, String journeyDate, String journeyTime, int seatAvailability) {
		super();
		this.ss_id = ss_id;
		this.journeyDate = journeyDate;
		this.journeyTime = journeyTime;
		this.seatAvailability = seatAvailability;
	}

	public Ship_Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}
		

}
