import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { BookingService } from 'src/app/Services/booking.service';
import { ShipScheduleService } from 'src/app/Services/ship-schedule.service';

@Component({
  selector: 'app-ship-tickets',
  templateUrl: './ship-tickets.component.html',
  styleUrls: ['./ship-tickets.component.css']
})
export class ShipTicketsComponent {

  ship_details_schedule: any;

  shipName: string = "";
  shipModel: string = "";
  shipCapacity: number = 0;
  shipResCapacity: number = 0;
  perKmRate: number = 0;

  journeyDate: string = "";
  journeyTime: string = "";
  seatAvailability: number = 0;

  pName: string = "";
  noOfHeadCount: number = 1;
  paymentAmount: number = 1;
  bookingDate: string = "";

  ssId: string = "";

  styProperty = {'display':'none'};

  constructor(private sss: ShipScheduleService,
              private bs: BookingService,
              private router: Router,
              private titleService: Title) { 
                  this.titleService.setTitle("Ship Tickets");
        }


//  ============================================================================================================

  goto_booking_passenger(): void {
    this.router.navigate(["passenger/booking"], {skipLocationChange:true});
  }

//  ============================================================================================================

  find_ships_name_sehedule(source_destination: NgForm): void {
    let source = source_destination.value.sourcePoint;
    let destination = source_destination.value.destinationPoint;

    console.log("Source: "+source);
    console.log("Destination: "+destination);
    this.sss.get_ship_details_schedule(source, destination).subscribe(
      result=> {
        this.ship_details_schedule = result;
        console.log(this.ship_details_schedule);
      }, error=> console.log(error)
    )
  }


//  ============================================================================================================
  goto_booking(ssId: string, perKmRate: number, seatAvailability: number): void {
    this.ssId = ssId;
    this.perKmRate = perKmRate;
    this.seatAvailability = seatAvailability;
 }


//  ============================================================================================================
total_payment(noOfHeadCount: number): void { 
  if(noOfHeadCount <= 4) {    //  Head-Count should not be more than 4 : costom condition
    this.styProperty = {'display':''};
    this.paymentAmount = (noOfHeadCount*this.perKmRate);
  }
}

//  ============================================================================================================

passenger_todo_booking(bookingRef: NgForm): void {
    let pEmail = sessionStorage.getItem("email_session");
    console.log("Email: "+pEmail)
    console.log("Ship Schedule ID: "+this.ssId)
    let booking = bookingRef.value;
    console.log(booking);
    
    if(confirm("Are you sure...?")) {
      if(pEmail != null) {
        this.bs.passenger_todo_booking(pEmail, this.ssId, booking).subscribe(
          result=> {
            alert("Booking Successfully Done...");
            // this.router.navigate(["passenger/booking"], {skipLocationChange:true});
          }, error=> console.log(error)
        )
      }
      else {
        alert("Do Login First...!!")
      }
    }
    // bookingRef.reset();
}


//  ================ : Update Operation of Ship_Schedule SeatAvailability : ====================================

update_SeatAvailability(noOfHeadCount: number): void {
    let seatRef = {
      seatAvailability : (this.seatAvailability - noOfHeadCount),
    }
    this.sss.update_SeatAvailability(this.ssId, seatRef).subscribe()  // ssId taken from goto_booking()
}


//  ============================================================================================================

goto_payment_page(ssId: string, perKmRate: string, seatAvailability: string): void {
  sessionStorage.setItem("ssId_session", ssId);
  sessionStorage.setItem("perKmRate_session", perKmRate);
  sessionStorage.setItem("seatAvailability_session", seatAvailability);
  this.router.navigate(["passenger/payment"], {skipLocationChange:true});
}
//  ============================================================================================================



}
