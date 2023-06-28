package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Ship_Details;
import com.repository.ShipDetails_Repository;

@Service
public class Ship_Details_Service {
	
	@Autowired
	ShipDetails_Repository shipDetailsRepo;
	
	
// ======================================================================================================================
	
	
	//  Insert Operation by Id-Unique Email:-    Op:3
	
	public String store_ShipDetails(Ship_Details ship_details) {
		boolean res = shipDetailsRepo.existsById(ship_details.getShipId());
		System.out.println(res);
		if(res) {
			return "Ship details didn't store...\nThis Id: "+ ship_details.getShipId() + " is already occupied...\nTry with another...";
		}
		else {
			shipDetailsRepo.save(ship_details);
			return "Ship Details("+ ship_details.getShipId() +") Stored successfully...";
		}
	}
	
	
// =====================================================================================================================
// =====================================================================================================================

	
}
