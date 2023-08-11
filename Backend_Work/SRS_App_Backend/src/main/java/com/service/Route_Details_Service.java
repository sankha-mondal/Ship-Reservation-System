package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entity.Route_Details;
import com.repository.RouteDetails_Repository;

@Service
public class Route_Details_Service {
	
	@Autowired
	RouteDetails_Repository routeDetailsRepo;
	
// =====================================================================================================================
	
	
	
	//  Insert Operation by Id-Unique Email:-    Op:3
	
	public String store_RouteDetails(Route_Details route_details) {
		boolean res = routeDetailsRepo.existsById(route_details.getRouteId());
		System.out.println(res);
		if(res) {
			// return "Route details didn't store...\nRoute details with Id "+route_details.getRouteId()+" already Stored...";
			return "Route details didn't store...\nThis Id: "+ route_details.getRouteId() + " is already occupied...\nTry with another...";

		}
		else {
			routeDetailsRepo.save(route_details);
			return "Route Details("+ route_details.getRouteId() +") Stored successfully...";
		}
	}
	
	
// =====================================================================================================================

	
}
