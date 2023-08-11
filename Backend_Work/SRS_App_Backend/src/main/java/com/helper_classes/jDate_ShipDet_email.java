package com.helper_classes;

public class jDate_ShipDet_email {
	
	int bookingId;
	String pEmail;
	String pName;
	String bDate;
	int onOfHC;
	int payableAmount;
	String pPhone;
	String pAddress;
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
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
	public String getbDate() {
		return bDate;
	}
	public void setbDate(String bDate) {
		this.bDate = bDate;
	}
	public int getOnOfHC() {
		return onOfHC;
	}
	public void setOnOfHC(int onOfHC) {
		this.onOfHC = onOfHC;
	}
	public int getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(int payableAmount) {
		this.payableAmount = payableAmount;
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
	
	public jDate_ShipDet_email(int bookingId, String pEmail, String pName, String bDate, int onOfHC, int payableAmount,
			String pPhone, String pAddress) {
		super();
		this.bookingId = bookingId;
		this.pEmail = pEmail;
		this.pName = pName;
		this.bDate = bDate;
		this.onOfHC = onOfHC;
		this.payableAmount = payableAmount;
		this.pPhone = pPhone;
		this.pAddress = pAddress;
	}
	
	@Override
	public String toString() {
		return "jDate_ShipDet_email [bookingId=" + bookingId + ", pEmail=" + pEmail + ", pName=" + pName + ", bDate="
				+ bDate + ", onOfHC=" + onOfHC + ", payableAmount=" + payableAmount + ", pPhone=" + pPhone
				+ ", pAddress=" + pAddress + "]";
	}
	
	public jDate_ShipDet_email() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
