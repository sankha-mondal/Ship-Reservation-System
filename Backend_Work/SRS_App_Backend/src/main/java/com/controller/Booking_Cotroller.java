package com.controller;

import java.io.IOException;
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

import com.entity.Booking;
import com.entity.Passenger;
import com.entity.Ship_Schedule;
import com.exception.ResourceNotFoundException;
import com.helper_classes.Res_Data_Booking_Receipt;
import com.helper_classes.Res_SD_SS_on_Sour_Dest;
import com.helper_classes.bookingInfo_pEmail;
import com.helper_classes.jDate_ShipDet_email;
import com.helper_classes.jDate_email;
import com.payload.ApiResponse;
import com.repository.Booking_Repository;
import com.repository.Passenger_Repository;
import com.repository.ShipSchedule_Repository;
import com.service.Booking_Service;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book")       //  http://localhost:8585/book
public class Booking_Cotroller {

	@Autowired
	Booking_Repository bookingRepo;
	
	@Autowired
	Booking_Service bookingService;
	
	@Autowired
	Passenger_Repository passRepo;
	
	@Autowired
	ShipSchedule_Repository shipSchRepo;
	
	
//====================================================== : CRUD : =============================================================

	  //  Retrieve Operation:-  Op:1A
	
	  //  http://localhost:8585/book/getAll
  
	  @GetMapping("/getAll")
	  public ResponseEntity<List<Booking>> getAllBooking() {
		  
	    List<Booking> booking = new ArrayList<Booking>();
	    	bookingRepo.findAll().forEach(booking::add);
		
		    if (booking.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		 return new ResponseEntity<>(booking, HttpStatus.OK);
	  }
	
		
	
//======================================================================================================================================
	
	
	  //  Retrieve by Passenger-Email:-  Op:1B

	  //  http://localhost:8585/book/getByPassEmail/{pEmail}

	  @GetMapping("/getByPassEmail/{pEmail}")
	  public ResponseEntity<List<Booking>> getAllBookingByPassEmail(@PathVariable(value = "pEmail") String pEmail) {
		  
	    Passenger passenger  = passRepo.findById(pEmail)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Passenger with Email = " + pEmail));
	
		    List<Booking> booking = new ArrayList<Booking>();
		    booking.addAll(passenger.getBooking());   // calling getBooking()
	    
	    return new ResponseEntity<>(booking, HttpStatus.OK);
	  }


	  
//======================================================================================================================================
	  
	//  Retrieve by Journey-date :-  Op:1C

	//  http://localhost:8585/book/getByJDate/{jDate}
	  
	  @GetMapping(value = "getByJDate/{jDate}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<jDate_email> findSSbyjDate(@PathVariable("jDate") String jDate) {
		  return bookingService.getAll_BPD(jDate);
	  }


//======================================================================================================================================
	  
	  //  Retrieve by Booking-Id:-  Op:1D
	  
	  //  http://localhost:8585/book/getbooking/{bookingId}

	  @GetMapping("/getbooking/{bookingId}")
	  public ResponseEntity<Booking> getBooksByBookingId(@PathVariable(value = "bookingId") int bookingId) {
		  
	    Booking booking = bookingRepo.findById(bookingId)
	        .orElseThrow(() -> new ResourceNotFoundException("Not found Booking with Id = " + bookingId));
	
	    return new ResponseEntity<>(booking, HttpStatus.OK);
	  }
	  
	  
	  
//======================================================================================================================================
		  
	  //  Insert Operation with passenger-Email & Ship_Schedule-Id:-    Op:2	  ==  Now Working  == 
	  
	  //  http://localhost:8585/book/store/{pEmail}/{ship_sch_id}     ==  Now Working  == 
	  
	  @PostMapping("/store/{pEmail}/{ship_sch_id}")
	  public ResponseEntity<Booking> createBooking( @PathVariable(value = "pEmail") String pEmail,
							@PathVariable(value = "ship_sch_id") int ship_sch_id,
							@RequestBody Booking bookingReq) {
		  
		  Booking booking = passRepo.findById(pEmail).map(passenger -> {
			  passenger.getBooking().add(bookingReq);
			  
				  Ship_Schedule ship_Schedule = shipSchRepo.findById(ship_sch_id)
						   .orElseThrow(() -> new ResourceNotFoundException("Not found Ship_Schedule with Id = " + ship_sch_id ));
				  
				  bookingReq.setShip_Schedule(ship_Schedule);
		  		
		      return bookingRepo.save(bookingReq);
		  }).orElseThrow(() -> new ResourceNotFoundException("Not found Passenger with id = " + pEmail));
		 
	
	    return new ResponseEntity<>(booking, HttpStatus.CREATED);
	  }
	  
	  
//======================================================================================================================================
	  
	  //  Update Operation by Booking-id:-   Op:3
	  
	  //  http://localhost:8585/book/update/{bookingId}
	  
	  @PutMapping("/update/{bookingId}")
	  public ResponseEntity<Booking> updateBooking(@PathVariable("bookingId") int bookingId, 
			  				@RequestBody Booking bookingReq) {
		  
	    Booking _booking = bookingRepo.findById(bookingId)
	        .orElseThrow(() -> new ResourceNotFoundException("BookId " + bookingId + "not found"));
	  
	    _booking.setpName(bookingReq.getpName());
	    _booking.setPaymentAmount(bookingReq.getPaymentAmount());
	    _booking.setNoOfHeadCount(bookingReq.getNoOfHeadCount());
	    _booking.setBookingDate(bookingReq.getBookingDate());
	    
	
	    return new ResponseEntity<>(bookingRepo.save(_booking), HttpStatus.OK);
	  }
	  
	  
//======================================================================================================================================
		  
	  //  Delete Operation by Booking-Id:-   Op:4
	  
	  //  http://localhost:8585/book/delete/{bookingId}
	  
	  @DeleteMapping("/delete/{bookingId}")
	  public ResponseEntity<ApiResponse> deleteBooking(@PathVariable("bookingId") int bookingId) {
		  
		  Booking booking = bookingRepo.findById(bookingId)
			        .orElseThrow(() -> new ResourceNotFoundException("Not found Booking with Id = " + bookingId));
		  
		  bookingRepo.deleteById(bookingId);
	    
	    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("Booking details deleted Successfully", true), HttpStatus.OK);
	  }
	  
	  
//======================================================================================================================================	  

	  //  Retrieve Booking Info for particular Email: Op:5
	  
	  //  http://localhost:8585/book/get_Full_Booking_Deatil_Of_OnePass/{pEmail}
	  
	  @GetMapping(value = "get_Full_Booking_Deatil_Of_OnePass/{pEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<bookingInfo_pEmail> findParticularInfoByEmail(@PathVariable("pEmail") String pEmail) {
		  return bookingService.getAll_BIPD(pEmail);
	  }
	  
//======================================================================================================================================

	  //  Retrieve Booking Info ON particular joining date and Ship Name : Op:6
	  
	  //  http://localhost:8585/book/get_Full_Booking_Deatil_Of_jDate_shipName/{jDate}/{Sname}
	  
	  @GetMapping(value = "get_Full_Booking_Deatil_Of_jDate_shipName/{jDate}/{sName}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<jDate_ShipDet_email> findParticularInfoByJdateSname(@PathVariable("jDate") String jDate,
			  														  @PathVariable("sName") String sName ) {
		  
		  return bookingService.getAll_JDSE(jDate, sName);
		  
	  }
	  
	 
//======================================================================================================================================

	   //  Download : Retrieve Message by Email & password Operation:-   Op: 7
		
	   //  http://localhost:8585/book/passenger/receipt?bookingId=${booking.id}
		
		@GetMapping("/passenger/receipt")
	    public void generateBook_PassengerReceipt(@RequestParam int bookingId, HttpServletResponse response) throws IOException, IOException {
	        // bookingService.generateBookingReceipt(bookingId,response);
			System.out.println("======= Receipt Data Controller =======");
			bookingService.get_Date_Booking_Receipt(bookingId, response);
	    }
	 	  
	  
//======================================================================================================================================

	    //  Download : Retrieve Message by Email & password Operation:-   Op: 8
		
	   //  http://localhost:8585/book/hc/receipt?bookingId=${booking.id}
		
		@GetMapping("/hc/receipt")
	    public void generateBook_HCReceipt(@RequestParam int bookingId, HttpServletResponse response) throws IOException, IOException {
	        // bookingService.generateBookingReceipt(bookingId,response);
			System.out.println("======= Receipt HC Controller =======");
			bookingService.get_Data_HC_Receipt(bookingId, response);
	    }
		
		
//======================================================================================================================================

	  //  Retrieve Booking Date for Email-Sending : Op:9  
	  
	  //  http://localhost:8585/book/get_Date_Booking_for_Email/{bookingId}
	  
	  @GetMapping(value = "get_Date_Booking_for_Email/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	  public List<Res_Data_Booking_Receipt> get_Date_Booking_for_Email(@PathVariable("bookingId") int bookingId) {
		  
		  return bookingService.get_Date_Booking_for_Email(bookingId);
		  
	  }
		  
			
//======================================================================================================================================
//======================================================================================================================================
		  
		  
}

