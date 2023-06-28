package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.entity.Head_Count;
import com.exception.ResourceNotFoundException;
import com.payload.ApiResponse;
import com.repository.Booking_Repository;
import com.repository.Head_Count_Repository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/head_count")		//  http://localhost:8585/head_count
public class Head_Count_Controller {

	@Autowired
	Head_Count_Repository headCountRepo;
	
	@Autowired
	Booking_Repository bookingRepo;
	
	
//*************************************************** : CRUD Operation : *****************************************************************	  
//=======================================================================================================================================

	 //  Retrieve Operation:-  Op:1A
	  
 	 //	 http://localhost:8585/head_count/getAll
 
	 @GetMapping("/getAll")
	  public ResponseEntity<List<Head_Count>> getAll_HeadCount() {
		  
	    List<Head_Count> headCount = new ArrayList<Head_Count>();
	    	headCountRepo.findAll().forEach(headCount::add);
		
		    if (headCount.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		 return new ResponseEntity<>(headCount, HttpStatus.OK);
	  }
	 
	 
		
//======================================================================================================================================
		
		
		  //  Retrieve by Booking-Id:-  Op:1B

		  //  http://localhost:8585/head_count/get_HeadCount_By_BookingId/{bookingId}

		  @GetMapping("get_HeadCount_By_BookingId/{bookingId}")
		  public ResponseEntity<List<Head_Count>> getHeadCountByBookingId(@PathVariable(value = "bookingId") int bookingId) {
			  
		    Booking booking  = bookingRepo.findById(bookingId)
		        .orElseThrow(() -> new ResourceNotFoundException("Not found Booking with Id = " + bookingId));
		
		    	List<Head_Count> headCount = new ArrayList<Head_Count>();
			    headCount.addAll(booking.getHead_Count());   // calling getBooking()
		    
		    return new ResponseEntity<>(headCount, HttpStatus.OK);
		  }


		  
//======================================================================================================================================

	
		  //  Insert Operation with Booking-Id:-    Op:4	==  Not Working  == 
		  
		  //  http://localhost:8585/head_count/store/{bookingId}     ==  Not Working  == 
		  
		  @PostMapping("/store/{bookingId}")
		  public ResponseEntity<Head_Count> create_HeadCount( @PathVariable(value = "bookingId") int bookingId,
														      @RequestBody Head_Count headCount_Req) {
			  
			  System.out.println("Booking id:************* "+bookingId);
			  
			  Head_Count headCount = bookingRepo.findById(bookingId).map(booking -> {
				  booking.getHead_Count().add(headCount_Req);
				  
			      		return headCountRepo.save(headCount_Req);
			    }).orElseThrow(() -> new ResourceNotFoundException("Not found Booking with id = " + bookingId));
		
		    return new ResponseEntity<>(headCount, HttpStatus.CREATED);
		  }
	
		  
//======================================================================================================================================
	
		  //  Update Operation by Head_Count Id:-   Op:3
		  
		  //  http://localhost:8585/head_count/update/{headCountId}
		  
		  @PutMapping("/update/{headCountId}")
		  public ResponseEntity<Head_Count> update_HeadCount(@PathVariable("headCountId") int headCountId, 
				  									         @RequestBody Head_Count headCountReq) {
			  
		    Head_Count _headCount = headCountRepo.findById(headCountId)
		        .orElseThrow(() -> new ResourceNotFoundException("Head Count Id " + headCountId + "not found"));
		  
		    _headCount.setHcName(headCountReq.getHcName());
		    _headCount.setHcAge(headCountReq.getHcAge());
		    _headCount.setHcGender(headCountReq.getHcGender());
		    
		
		    return new ResponseEntity<>(headCountRepo.save(_headCount), HttpStatus.OK);
		  }
		  
		  
//======================================================================================================================================

		  //  Delete Operation by Head_Count Id:-   Op:4
		  
		  //  http://localhost:8585/head_count/delete/{headCountId}
		  
		  @DeleteMapping("/delete/{headCountId}")
		  public ResponseEntity<ApiResponse> deleteHeadCount(@PathVariable("headCountId") int headCountId) {
			  
			  Head_Count head_count = headCountRepo.findById(headCountId)
				        .orElseThrow(() -> new ResourceNotFoundException("Not found Head-Count with Id = " + headCountId));
			  
			  headCountRepo.deleteById(headCountId);
		    
		    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			  return new ResponseEntity<ApiResponse>(new ApiResponse("Head-Count details of Id "+headCountId +" deleted Successfully", true), HttpStatus.OK);
		  }
		  
		  
//======================================================================================================================================	  		  
//======================================================================================================================================	  

	
}
