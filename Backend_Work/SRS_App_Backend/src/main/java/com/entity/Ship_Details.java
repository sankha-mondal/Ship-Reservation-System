package com.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "Ship_Details")
public class Ship_Details {
	
	@Id
	@Column(name="ship_id", unique=true)
	private String shipId;
	
	@Column(name="shipName")
	private String shipName;
	
	@Column(name="shipModel")
	private String shipModel;
	
	@Column(name="shipCapacity")
	private int shipCapacity;
	
	@Column(name="shipReservationCapacity")
	private int shipReservationCapacity;
	
	@Column(name="per_kmRate")
	private int per_kmRate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ship_details_id")    //  "ship_details_id" column will be created in "Ship_Schedule" Table
	private Set<Ship_Schedule> ship_schedule = new HashSet<>();

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

	public int getShipCapacity() {
		return shipCapacity;
	}

	public void setShipCapacity(int shipCapacity) {
		this.shipCapacity = shipCapacity;
	}

	public int getShipReservationCapacity() {
		return shipReservationCapacity;
	}

	public void setShipReservationCapacity(int shipReservationCapacity) {
		this.shipReservationCapacity = shipReservationCapacity;
	}

	public int getPer_kmRate() {
		return per_kmRate;
	}

	public void setPer_kmRate(int per_kmRate) {
		this.per_kmRate = per_kmRate;
	}

	public Set<Ship_Schedule> getShip_schedule() {
		return ship_schedule;
	}

	public void setShip_schedule(Set<Ship_Schedule> ship_schedule) {
		this.ship_schedule = ship_schedule;
	}

	@Override
	public String toString() {
		return "Ship_Details [shipId=" + shipId + ", shipName=" + shipName + ", shipModel=" + shipModel
				+ ", shipCapacity=" + shipCapacity + ", shipReservationCapacity=" + shipReservationCapacity
				+ ", per_kmRate=" + per_kmRate + ", ship_schedule=" + ship_schedule + "]";
	}

	public Ship_Details(String shipId, String shipName, String shipModel, int shipCapacity, int shipReservationCapacity,
			int per_kmRate, Set<Ship_Schedule> ship_schedule) {
		super();
		this.shipId = shipId;
		this.shipName = shipName;
		this.shipModel = shipModel;
		this.shipCapacity = shipCapacity;
		this.shipReservationCapacity = shipReservationCapacity;
		this.per_kmRate = per_kmRate;
		this.ship_schedule = ship_schedule;
	}

	public Ship_Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}
