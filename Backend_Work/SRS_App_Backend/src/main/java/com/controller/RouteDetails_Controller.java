package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Passenger;
import com.entity.Route_Details;
import com.exception.ResourceNotFoundException;
import com.payload.ApiResponse;
import com.repository.RouteDetails_Repository;
import com.service.Route_Details_Service;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/route_details")		//  http://localhost:8585/route_details
public class RouteDetails_Controller {
	
	@Autowired
	RouteDetails_Repository routeDetailsRepo;
	
	@Autowired
	Route_Details_Service routeDetService;
	
//*************************************************** : CRUD Operation : *****************************************************************	  
//=======================================================================================================================================

	
		 //  Retrieve Operation:-  Op:1
	  
	 	 //	 http://localhost:8585/route_details/getAll
	 
		  @GetMapping("/getAll")
		  public ResponseEntity<List<Route_Details>> getAll_RouteDetails() {
		    List<Route_Details> routeDetails = new ArrayList<Route_Details>();
		
		    	routeDetailsRepo.findAll().forEach(routeDetails::add);
			
			    if (routeDetails.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }
			
			    return new ResponseEntity<>(routeDetails, HttpStatus.OK);
		  }
	  
 
 
//=======================================================================================================================================

		  //  Retrieve data by Route-Id :-  Op:2
		  
		  //  http://localhost:8585/route_details/route/{routeId}

		  @GetMapping("/route/{routeId}")
		  public ResponseEntity<Route_Details> getRouteByrId(@PathVariable("routeId") String routeId) {
			  
			  Route_Details routeDetails = routeDetailsRepo.findById(routeId)
					  .orElseThrow(() -> new ResourceNotFoundException("Not found Route Details with Id = " + routeId));
		
		    	return new ResponseEntity<>(routeDetails, HttpStatus.OK);
		  }
				  
			  
			  
//===========================================================================================================================================
	
	
		  //  Insert Operation:-    Op:3
		  
		  //  http://localhost:8585/route_details/store
		  
//		  @PostMapping("/store")
//		  public ResponseEntity<Route_Details> create_RouteDetails(@RequestBody Route_Details routeDetailReq) { 
//			  
//			  Route_Details _routeDetails = routeDetailsRepo.save(new Route_Details(routeDetailReq.getRouteId(),
//					  												 			routeDetailReq.getSourcePoint(), 
//					  												            routeDetailReq.getDestinationPoint(),
//					  												            routeDetailReq.getDistanceKm(),
//					  												            routeDetailReq.getRoute_id()
//					  												  			));
//		    return new ResponseEntity<>(_routeDetails, HttpStatus.CREATED);
//		  }
		  
		  @PostMapping(value="store", consumes = MediaType.APPLICATION_JSON_VALUE)
		  public String store_RouteDetails(@RequestBody Route_Details routeDetails) {
			  System.out.println(routeDetails.getRouteId());
			  
			  return routeDetService.store_RouteDetails(routeDetails);
		  }
		  
	  
	  
//=======================================================================================================================================  

		  //  Update Operation:-   Op:4
		  
		  //  http://localhost:8585/route_details/update/{routeId}
		  
		  @PutMapping("update/{routeId}")
		  public ResponseEntity<Route_Details> update_RouteDetails(@PathVariable("routeId") String routeId, 
				  										   @RequestBody Route_Details routeDetailReq) {
			  
			  Route_Details _routeDetails = routeDetailsRepo.findById(routeId)
					  .orElseThrow(() -> new ResourceNotFoundException("Not found Route Details with Id = " + routeId));
		
			    _routeDetails.setSourcePoint(routeDetailReq.getSourcePoint());
			    _routeDetails.setDestinationPoint(routeDetailReq.getDestinationPoint());
			    _routeDetails.setDistanceKm(routeDetailReq.getDistanceKm());
			    
		    return new ResponseEntity<>(routeDetailsRepo.save(_routeDetails), HttpStatus.OK);
		  }
				  
				  
			  
//=======================================================================================================================================
				  
		  //  Delete Operation by Id:-   Op:5
		  
		  //  http://localhost:8585/route_details/delete/{routeId}
		  
		  @DeleteMapping("/delete/{routeId}")
		  public ResponseEntity<ApiResponse> deletePassenger(@PathVariable("routeId") String routeId) {
			  
			  Route_Details routeDetails = routeDetailsRepo.findById(routeId)
				        .orElseThrow(() -> new ResourceNotFoundException("Not found Route Details with Id = " + routeId));
			  
			  routeDetailsRepo.deleteById(routeId);
		    
		    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  return new ResponseEntity<ApiResponse>(new ApiResponse("Route details deleted Successfully", true), HttpStatus.OK);
		  }
				  
				  
			  
//=======================================================================================================================================
		  
		  //  All Delete Operation:-   Op:6
			 
		  //  http://localhost:8585/route_details/deleteAll

		  @DeleteMapping("/deleteAll")
		  public ResponseEntity<HttpStatus> deleteAll_RouteDetails() {
			  
			  routeDetailsRepo.deleteAll();
		    
		    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
	
		  
//=======================================================================================================================================
		  

}
