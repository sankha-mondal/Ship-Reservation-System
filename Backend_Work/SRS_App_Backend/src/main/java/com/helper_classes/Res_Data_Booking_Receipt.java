package com.helper_classes;

public class Res_Data_Booking_Receipt {
	
	int bokingId;
	String pEmail;
	String pName;
	String pPhone;
	String pAddress;
	String url;
	
	String journeyDate;
	String journeyTime;
	
	String sourcePoint;
	String destinationPoint;
	int distancekm;
	
	String shipName;
	String shipModel;
	
	String hcName;
	int hcAge;
	String hcGender;
	
	int noOfHeadCount;
	int paymentAmount;
	
	public int getBokingId() {
		return bokingId;
	}
	public void setBokingId(int bokingId) {
		this.bokingId = bokingId;
	}
	public String getpEmail() {
		return pEmail;
	}
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpPhone() {
		return pPhone;
	}
	public void setpPhone(String pPhone) {
		this.pPhone = pPhone;
	}
	public String getpAddress() {
		return pAddress;
	}
	public void setpAddress(String pAddress) {
		this.pAddress = pAddress;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getSourcePoint() {
		return sourcePoint;
	}
	public void setSourcePoint(String sourcePoint) {
		this.sourcePoint = sourcePoint;
	}
	public String getDestinationPoint() {
		return destinationPoint;
	}
	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}
	public int getDistancekm() {
		return distancekm;
	}
	public void setDistancekm(int distancekm) {
		this.distancekm = distancekm;
	}
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
	public int getNoOfHeadCount() {
		return noOfHeadCount;
	}
	public void setNoOfHeadCount(int noOfHeadCount) {
		this.noOfHeadCount = noOfHeadCount;
	}
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	@Override
	public String toString() {
		return "Res_Data_Booking_Receipt [bokingId=" + bokingId + ", pEmail=" + pEmail + ", pName=" + pName
				+ ", pPhone=" + pPhone + ", pAddress=" + pAddress + ", url=" + url + ", journeyDate=" + journeyDate
				+ ", journeyTime=" + journeyTime + ", sourcePoint=" + sourcePoint + ", destinationPoint="
				+ destinationPoint + ", distancekm=" + distancekm + ", shipName=" + shipName + ", shipModel="
				+ shipModel + ", hcName=" + hcName + ", hcAge=" + hcAge + ", hcGender=" + hcGender + ", noOfHeadCount="
				+ noOfHeadCount + ", paymentAmount=" + paymentAmount + "]";
	}
	
	public Res_Data_Booking_Receipt(int bokingId, String pEmail, String pName, String pPhone, String pAddress,
			String url, String journeyDate, String journeyTime, String sourcePoint, String destinationPoint,
			int distancekm, String shipName, String shipModel, String hcName, int hcAge, String hcGender,
			int noOfHeadCount, int paymentAmount) {
		super();
		this.bokingId = bokingId;
		this.pEmail = pEmail;
		this.pName = pName;
		this.pPhone = pPhone;
		this.pAddress = pAddress;
		this.url = url;
		this.journeyDate = journeyDate;
		this.journeyTime = journeyTime;
		this.sourcePoint = sourcePoint;
		this.destinationPoint = destinationPoint;
		this.distancekm = distancekm;
		this.shipName = shipName;
		this.shipModel = shipModel;
		this.hcName = hcName;
		this.hcAge = hcAge;
		this.hcGender = hcGender;
		this.noOfHeadCount = noOfHeadCount;
		this.paymentAmount = paymentAmount;
	}
	
	public Res_Data_Booking_Receipt() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
