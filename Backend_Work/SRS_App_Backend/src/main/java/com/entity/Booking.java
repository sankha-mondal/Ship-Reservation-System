package com.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookingId;
	
	@Column(name="pName")
	private String pName;
	
	@Column(name="paymentAmount")
	private int paymentAmount;
	
	@Column(name="bookingDate")
	private String bookingDate;
	
	@OneToOne
	@JoinColumn(name = "ship_sch_id")     //  One Booking has one ship_Schedule
	private Ship_Schedule ship_Schedule;    //  "ship_sch_id" column will be created in "Booking" table
	
	@Column(name="noOfHeadCount")
	private int noOfHeadCount;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)    // One Booking can have many Head-Count
	@JoinColumn(name = "hc_booking_id")     //  "hc_booking_id" column will be created in "Head-Count" Table
 	private Set<Head_Count> Head_Count = new HashSet<>();

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public Ship_Schedule getShip_Schedule() {
		return ship_Schedule;
	}

	public void setShip_Schedule(Ship_Schedule ship_Schedule) {
		this.ship_Schedule = ship_Schedule;
	}

	public int getNoOfHeadCount() {
		return noOfHeadCount;
	}

	public void setNoOfHeadCount(int noOfHeadCount) {
		this.noOfHeadCount = noOfHeadCount;
	}

	public Set<Head_Count> getHead_Count() {
		return Head_Count;
	}

	public void setHead_Count(Set<Head_Count> head_Count) {
		Head_Count = head_Count;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", pName=" + pName + ", paymentAmount=" + paymentAmount
				+ ", bookingDate=" + bookingDate + ", ship_Schedule=" + ship_Schedule + ", noOfHeadCount="
				+ noOfHeadCount + ", Head_Count=" + Head_Count + "]";
	}

	public Booking(int bookingId, String pName, int paymentAmount, String bookingDate, Ship_Schedule ship_Schedule,
			int noOfHeadCount, Set<com.entity.Head_Count> head_Count) {
		super();
		this.bookingId = bookingId;
		this.pName = pName;
		this.paymentAmount = paymentAmount;
		this.bookingDate = bookingDate;
		this.ship_Schedule = ship_Schedule;
		this.noOfHeadCount = noOfHeadCount;
		Head_Count = head_Count;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
