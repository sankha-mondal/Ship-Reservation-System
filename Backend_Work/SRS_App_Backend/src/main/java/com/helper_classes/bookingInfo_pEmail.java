package com.helper_classes;

public class bookingInfo_pEmail {
	
	String bookingId;
	String pEmail;
	String pName;
	int paymentAmount;
	String bookingDate;
	
	String routeId;
	String journeyDate;
	String journeyTime;
	
	String shipId;
	String shipName;
	String shipModel;
	
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
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
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
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
	public String getShipId() {
		return shipId;
	}
	public void setShipId(String shipId) {
		this.shipId = shipId;
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
	
	@Override
	public String toString() {
		return "bookingInfo_pEmail [bookingId=" + bookingId + ", pEmail=" + pEmail + ", pName=" + pName
				+ ", paymentAmount=" + paymentAmount + ", bookingDate=" + bookingDate + ", routeId=" + routeId
				+ ", journeyDate=" + journeyDate + ", journeyTime=" + journeyTime + ", shipId=" + shipId + ", shipName="
				+ shipName + ", shipModel=" + shipModel + "]";
	}
	
	public bookingInfo_pEmail(String bookingId, String pEmail, String pName, int paymentAmount, String bookingDate,
			String routeId, String journeyDate, String journeyTime, String shipId, String shipName, String shipModel) {
		super();
		this.bookingId = bookingId;
		this.pEmail = pEmail;
		this.pName = pName;
		this.paymentAmount = paymentAmount;
		this.bookingDate = bookingDate;
		this.routeId = routeId;
		this.journeyDate = journeyDate;
		this.journeyTime = journeyTime;
		this.shipId = shipId;
		this.shipName = shipName;
		this.shipModel = shipModel;
	}
	
	public bookingInfo_pEmail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
	
	

}
