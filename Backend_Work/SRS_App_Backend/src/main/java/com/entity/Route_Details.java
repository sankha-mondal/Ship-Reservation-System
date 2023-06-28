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
@Table(name = "Route_Details")
public class Route_Details {
	
	@Id
	@Column(name="routeId", unique=true)
	private String routeId;
	
	@Column(name="sourcePoint")
	private String sourcePoint;
	
	@Column(name="destinationPoint")
	private String destinationPoint;
	
	@Column(name="distanceKm")
	private int distanceKm;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "route_id")    //  "route_id" column will be created in "Ship_Schedule" Table
	private Set<Ship_Schedule> route_id = new HashSet<>();

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
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

	public int getDistanceKm() {
		return distanceKm;
	}

	public void setDistanceKm(int distanceKm) {
		this.distanceKm = distanceKm;
	}

	public Set<Ship_Schedule> getRoute_id() {
		return route_id;
	}

	public void setRoute_id(Set<Ship_Schedule> route_id) {
		this.route_id = route_id;
	}

	@Override
	public String toString() {
		return "Route_Details [routeId=" + routeId + ", sourcePoint=" + sourcePoint + ", destinationPoint="
				+ destinationPoint + ", distanceKm=" + distanceKm + ", route_id=" + route_id + "]";
	}

	public Route_Details(String routeId, String sourcePoint, String destinationPoint, int distanceKm,
			Set<Ship_Schedule> route_id) {
		super();
		this.routeId = routeId;
		this.sourcePoint = sourcePoint;
		this.destinationPoint = destinationPoint;
		this.distanceKm = distanceKm;
		this.route_id = route_id;
	}

	public Route_Details() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}
