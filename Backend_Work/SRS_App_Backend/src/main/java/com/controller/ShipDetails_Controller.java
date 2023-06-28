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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Passenger;
import com.entity.Route_Details;
import com.entity.Ship_Details;
import com.exception.ResourceNotFoundException;
import com.payload.ApiResponse;
import com.repository.ShipDetails_Repository;
import com.service.Ship_Details_Service;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ship_details")		//  http://localhost:8585/ship_details
public class ShipDetails_Controller {
	
	@Autowired
	ShipDetails_Repository shipDetailsRepo;
	
	@Autowired
	Ship_Details_Service shipDetails_Service;
	
//**************************************** : CRUD Operation : *****************************************************************	  
//=============================================================================================================================

	  	 //  Retrieve Operation:-  Op:1
	  
	  	 //	 http://localhost:8585/ship_details/getAll
	  
		  @GetMapping("/getAll")
		  public ResponseEntity<List<Ship_Details>> getAllShipDetails() {
		    List<Ship_Details> shipDetails = new ArrayList<>();
		
		    	shipDetails=shipDetailsRepo.findAll();
			        // System.out.println(shipDetails);
			    if (shipDetails.isEmpty()) {
			      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			    }
			
			    return new ResponseEntity<>(shipDetails, HttpStatus.OK);
		  }
//	
	
	
// =======================================================================================================================================
	
	
		  //  Retrieve data by Ship_Details Id :-  Op:2
		  
		  //  http://localhost:8585/ship_details/shipId/{shipId}

		  @GetMapping("/shipId/{shipId}")
		  public ResponseEntity<Ship_Details> getShipDetailById(@PathVariable("shipId") String shipId) {
			  
			 Ship_Details shipDetails = shipDetailsRepo.findById(shipId)
					  .orElseThrow(() -> new ResourceNotFoundException("Not found Ship Details with Id = " + shipId));
		
		    	return new ResponseEntity<>(shipDetails, HttpStatus.OK);
		  }
		  
		
// =======================================================================================================================================
		  
		  //  Insert Operation:-    Op:3
		  
		  //  http://localhost:8585/ship_details/store
		  
//		  @PostMapping("/store")
//		  public ResponseEntity<Ship_Details> createPassenger(@RequestBody Ship_Details sdReq) { 
//			  Ship_Details _shipDetails = shipDetailsRepo.save(new Ship_Details(sdReq.getShipId(),
//					  												            sdReq.getShipName(), 
//					  												            sdReq.getShipModel(),
//					  												            sdReq.getShipCapacity(),
//					  												            sdReq.getShipReservationCapacity(),
//					  												            sdReq.getPer_kmRate(),
//					  												            sdReq.getBooking(),
//					  												            sdReq.getShip_schedule()
//					  												  		));
//		    return new ResponseEntity<>(_shipDetails, HttpStatus.CREATED);
//		  }
		  
		  @PostMapping(value="store", consumes = MediaType.APPLICATION_JSON_VALUE)
		  public String store_ShipDetails(@RequestBody Ship_Details shipDetails) {
			  System.out.println(shipDetails.getShipId());
			  
			  return shipDetails_Service.store_ShipDetails(shipDetails);
		  }
		  
		  
// =======================================================================================================================================
	
		  //  Update Operation:-   Op:4
		  
		  //  http://localhost:8585/ship_details/update/{shipId}
		  
		  @PutMapping("/update/{shipId}")
		  public ResponseEntity<Ship_Details> updateShipDetails(@PathVariable("shipId") String shipId, 
				  										        @RequestBody Ship_Details sdReq) {
			  
			  Ship_Details _shipDetails = shipDetailsRepo.findById(shipId)
		        .orElseThrow(() -> new ResourceNotFoundException("Not found Ship Details with Id = " + shipId));
		
			    _shipDetails.setShipName(sdReq.getShipName());
			    _shipDetails.setShipModel(sdReq.getShipModel());
			    _shipDetails.setShipCapacity(sdReq.getShipCapacity());
			    _shipDetails.setShipReservationCapacity(sdReq.getShipReservationCapacity());
			    _shipDetails.setPer_kmRate(sdReq.getPer_kmRate());
			    
		    return new ResponseEntity<>(shipDetailsRepo.save(_shipDetails), HttpStatus.OK);
		  }
		  
		  
// =======================================================================================================================================
		  
		  //  Delete Operation by Id:-   Op:5
		  
		  //  http://localhost:8585/ship_details/delete/{shipId}
		  
		  @DeleteMapping("/delete/{shipId}")
		  public ResponseEntity<ApiResponse> deleteShipDetails(@PathVariable("shipId") String shipid) {
			  
			  Ship_Details shipDeatils = shipDetailsRepo.findById(shipid)
				        .orElseThrow(() -> new ResourceNotFoundException("Not found Ship Details with Id = " + shipid));
			  
			  shipDetailsRepo.deleteById(shipid);
		    
		    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  return new ResponseEntity<ApiResponse>(new ApiResponse("Ship details deleted Successfully", true), HttpStatus.OK);
		  }
		  
// =======================================================================================================================================
// =======================================================================================================================================
		  
		  
}
