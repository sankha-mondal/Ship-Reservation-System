package com.helper_classes;

public class Res_SD_SS_on_Sour_Dest {
		
	String shipName;
	String shipModel;
	int shipCapacity;
	int shipResCapacity;
	int perKmRate;
	
	String journeyDate;
	String journeyTime;
	int seatAvailability;
	
	String ssId;
	String shipId;
	
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShipModel() {
		return shipModel;
	}
	public void setShipModel(String shipModel) {
		this.shipModel = shipModel;
	}
	public int getShipCapacity() {
		return shipCapacity;
	}
	public void setShipCapacity(int shipCapacity) {
		this.shipCapacity = shipCapacity;
	}
	public int getShipResCapacity() {
		return shipResCapacity;
	}
	public void setShipResCapacity(int shipResCapacity) {
		this.shipResCapacity = shipResCapacity;
	}
	public int getPerKmRate() {
		return perKmRate;
	}
	public void setPerKmRate(int perKmRate) {
		this.perKmRate = perKmRate;
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
	public String getSsId() {
		return ssId;
	}
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
	}
	
	@Override
	public String toString() {
		return "Res_SD_SS_on_Sour_Dest [shipName=" + shipName + ", shipModel=" + shipModel + ", shipCapacity="
				+ shipCapacity + ", shipResCapacity=" + shipResCapacity + ", perKmRate=" + perKmRate + ", journeyDate="
				+ journeyDate + ", journeyTime=" + journeyTime + ", seatAvailability=" + seatAvailability + ", ssId="
				+ ssId + ", shipId=" + shipId + "]";
	}
	
	public Res_SD_SS_on_Sour_Dest(String shipName, String shipModel, int shipCapacity, int shipResCapacity,
			int perKmRate, String journeyDate, String journeyTime, int seatAvailability, String ssId, String shipId) {
		super();
		this.shipName = shipName;
		this.shipModel = shipModel;
		this.shipCapacity = shipCapacity;
		this.shipResCapacity = shipResCapacity;
		this.perKmRate = perKmRate;
		this.journeyDate = journeyDate;
		this.journeyTime = journeyTime;
		this.seatAvailability = seatAvailability;
		this.ssId = ssId;
		this.shipId = shipId;
	}
	
	public Res_SD_SS_on_Sour_Dest() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	
	
	
	
	
}
