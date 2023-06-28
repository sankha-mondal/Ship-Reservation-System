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

import com.entity.Booking;
import com.entity.Passenger;
import com.entity.Route_Details;
import com.entity.Ship_Details;
import com.entity.Ship_Schedule;
import com.exception.ResourceNotFoundException;
import com.helper_classes.Res_SD_SS_on_Sour_Dest;
import com.payload.ApiResponse;
import com.repository.RouteDetails_Repository;
import com.repository.ShipDetails_Repository;
import com.repository.ShipSchedule_Repository;
import com.service.Ship_Schedule_Service;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ship_schedule")       //  http://localhost:8585/ship_schedule
public class ShipSchedule_Cotroller {
	
	@Autowired
	ShipSchedule_Repository shipScheduleRepo;
	
	@Autowired
	Ship_Schedule_Service shipScheduleService;
	
	@Autowired
	ShipDetails_Repository shipDetailsRepo;
	
	@Autowired
	RouteDetails_Repository routeDetailsRepo;
	

//====================================================== : CRUD : =============================================================

	  //  Retrieve Operation:-  Op:1
	
	  //  http://localhost:8585/ship_schedule/getAll

	  @GetMapping("/getAll")
	  public ResponseEntity<List<Ship_Schedule>> getAllshipSchedule() {
		  
	    List<Ship_Schedule> shipSchedules = new ArrayList<Ship_Schedule>();
	    	shipScheduleRepo.findAll().forEach(shipSchedules::add);
		
		    if (shipSchedules.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		 return new ResponseEntity<>(shipSchedules, HttpStatus.OK);
	  }
	

//======================================================================================================================================
		
		
	  //  Retrieve by Ship_Details Id:-  Op:1A

	  //  http://localhost:8585/ship_schedule/getBySDId/{shipId}

	  @GetMapping("/getBySDId/{shipId}")
	  public ResponseEntity<List<Ship_Schedule>> getAllShipScheBySDId(@PathVariable(value = "shipId") String shipId) {
		  
	    Ship_Details shipDetails = shipDetailsRepo.findById(shipId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Ship Details with Id = " + shipId));
	
		    List<Ship_Schedule> shipSchedules = new ArrayList<Ship_Schedule>();
		    shipSchedules.addAll(shipDetails.getShip_schedule());   // calling getShip_schedule()
	    
	    return new ResponseEntity<>(shipSchedules, HttpStatus.OK);
	  }


//======================================================================================================================================
	
	
	  //  Retrieve by Route_Details Id:-  Op:1B

	  //  http://localhost:8585/ship_schedule/getByRDId/{routeId}

	  @GetMapping("/getByRDId/{routeId}")
	  public ResponseEntity<List<Ship_Schedule>> getAllShipScheByRDId(@PathVariable(value = "routeId") String routeId) {
		  
	    Route_Details routeDetails = routeDetailsRepo.findById(routeId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Route Details with Id = " + routeId));
	
		    List<Ship_Schedule> shipSchedules = new ArrayList<Ship_Schedule>();
		    shipSchedules.addAll(routeDetails.getRoute_id());   // calling getShip_schedule()
	    
	    return new ResponseEntity<>(shipSchedules, HttpStatus.OK);
	  }
	  

//======================================================================================================================================

	  //  Retrieve by Ship_Schedule-Id:-  Op:1C
	  
	  //  http://localhost:8585/ship_schedule/getBySSId/{SSId}

	  @GetMapping("/getBySSId/{SSId}")
	  public ResponseEntity<Ship_Schedule> getShip_SchedulebySSId(@PathVariable(value = "SSId") int SSId) {
		  
	    Ship_Schedule shipSchedule = shipScheduleRepo.findById(SSId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Ship Schedule with Id = " + SSId));
	
	    return new ResponseEntity<>(shipSchedule, HttpStatus.OK);
	  }

//======================================================================================================================================
  	  	  
	  
	  //  Retrieve by Journey-date :-  Op:1D

	  //  http://localhost:8585/ship_schedule/getByJDate/{jDate}

	  @GetMapping(value = "getByJDate/{jDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Ship_Schedule> findSSbyjDate(@PathVariable("jDate") String jDate) {
		  return shipScheduleService.findSSbyjDate(jDate);
	  }
		  
  

//======================================================================================================================================
	  
	  
	  //  Insert Operation with Route_Details-Id & Ship_Details-Id:-    Op:2	  ==  Now Working  == 
	  
	  //  http://localhost:8585/ship_schedule/store/{routeId}/{shipid}            ==  Now Working  == 
	  
	  @PostMapping("/store/{routeId}/{shipid}")
	  public ResponseEntity<Ship_Schedule> create_shipSchedule( @PathVariable(value = "routeId") String routeId,
													@PathVariable(value = "shipid") String shipid,
													@RequestBody Ship_Schedule shipSchReq) {
		  
		  Ship_Schedule ship_Schedule = routeDetailsRepo.findById(routeId).map(route -> {
			  route.getRoute_id().add(shipSchReq);
			  
			  
			  Ship_Schedule _ship_Schedule = shipDetailsRepo.findById(shipid).map(ship -> {
					  ship.getShip_schedule().add(shipSchReq);
					  
					  return shipScheduleRepo.save(shipSchReq);
				  }).orElseThrow(() -> new ResourceNotFoundException("Not found Ship_Details with id = " + shipid));
		  		
		      return shipScheduleRepo.save(shipSchReq);
		  }).orElseThrow(() -> new ResourceNotFoundException("Not found Route_Details with id = " + routeId));
		 
	
	    return new ResponseEntity<>(ship_Schedule, HttpStatus.CREATED);
	  }
	  

//======================================================================================================================================
	  
	  //  Update Operation by Ship_Schedule-Id:-   Op:5
	  
	  //  http://localhost:8585/ship_schedule/update/{SSId}
	  
	  @PutMapping("/update/{SSId}")
	  public ResponseEntity<Ship_Schedule> update_ShipSchedule(@PathVariable("SSId") int SSId, 
			  									   			   @RequestBody Ship_Schedule shipScheduleReq) {
		  
	    Ship_Schedule _shipSchedule = shipScheduleRepo.findById(SSId)
	        .orElseThrow(() -> new ResourceNotFoundException("Ship Schedule Id " + SSId + "not found"));
	  
	    _shipSchedule.setJourneyDate(shipScheduleReq.getJourneyDate());
	    _shipSchedule.setJourneyTime(shipScheduleReq.getJourneyTime());
	    _shipSchedule.setSeatAvailability(shipScheduleReq.getSeatAvailability());
	    
	
	    return new ResponseEntity<>(shipScheduleRepo.save(_shipSchedule), HttpStatus.OK);
	  }
	  
	  
	  
//======================================================================================================================================
	  
	  //  Update Operation of Ship_Schedule SeatAvailability:-   Op:5A
	  
	  //  http://localhost:8585/ship_schedule/update_Ship_SeatAvai/{SSId}
	  
	  @PutMapping("/update_Ship_SeatAvai/{SSId}")
	  public ResponseEntity<Ship_Schedule> update_Ship_SeatAvailability(@PathVariable("SSId") int SSId, 
			  									   			   @RequestBody Ship_Schedule shipScheduleReq) {
		  
	    Ship_Schedule _shipSchedule = shipScheduleRepo.findById(SSId)
	        .orElseThrow(() -> new ResourceNotFoundException("Ship Schedule Id " + SSId + "not found"));
	  
	    _shipSchedule.setSeatAvailability(shipScheduleReq.getSeatAvailability());  // Only Seat update
	    
	
	    return new ResponseEntity<>(shipScheduleRepo.save(_shipSchedule), HttpStatus.OK);
	  }
		  
	  

//======================================================================================================================================
	  
	  
	  //  Delete Operation by Ship_Schedule-Id:-   Op:6
	  
	  //  http://localhost:8585/ship_schedule/delete/{ssId}
	  
	  @DeleteMapping("/delete/{ssID}")
	  public ResponseEntity<ApiResponse> delete_ShipSchedule(@PathVariable("ssID") int ssId) {
		  
		  Ship_Schedule ship_Schedule = shipScheduleRepo.findById(ssId)
			        .orElseThrow(() -> new ResourceNotFoundException("Not found Ship Schedule with Id = " + ssId));
		  
		  shipScheduleRepo.deleteById(ssId);
	    
	    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("Ship Schedule details deleted Successfully", true), HttpStatus.OK);
	  }
	  
	  
//======================================================================================================================================
	  
	  //  Retrieve Ship_Details & Ship_Schedule based ON Source and Destination : Op:7
	  
	  //  http://localhost:8585/ship_schedule/find_SDSS_on_Source_Destination/{source}/{destination}
	  
	  @GetMapping(value = "find_SDSS_on_Source_Destination/{source}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Res_SD_SS_on_Sour_Dest> find_SDSS_on_Source_Destination(@PathVariable("source") String source,
			  														 @PathVariable("destination") String destination) {
		  
		  return shipScheduleService.getAll_SD_SS(source, destination);
		  
	  }
		  
		  
	  
//======================================================================================================================================
//======================================================================================================================================	  
		  	

	  
	  
	  
	  
	  
	  
}
