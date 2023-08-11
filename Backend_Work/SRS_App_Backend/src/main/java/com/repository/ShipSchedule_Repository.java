package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Ship_Schedule;

@Repository
public interface ShipSchedule_Repository extends JpaRepository<Ship_Schedule, Integer> {
	
	
	//  Retrieve data by journeyDate:-  Op: 1D
	
	@Query("select ss from Ship_Schedule ss where ss.journeyDate = :journeyDate")
	public List<Ship_Schedule> findSSbyjDate(@Param("journeyDate") String journeyDate);
	
}
