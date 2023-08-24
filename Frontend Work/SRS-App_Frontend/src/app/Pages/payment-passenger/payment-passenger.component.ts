import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { BookingService } from 'src/app/Services/booking.service';
import { HeadCountService } from 'src/app/Services/head-count.service';
import { ShipScheduleService } from 'src/app/Services/ship-schedule.service';

@Component({
  selector: 'app-payment-passenger',
  templateUrl: './payment-passenger.component.html',
  styleUrls: ['./payment-passenger.component.css']
})
export class PaymentPassengerComponent implements OnInit {

  pName: any;
  noOfHeadCount: any;
  bookingDate: any;
  seatAvailability: any;

  paymentAmount: number = 0;
  perKmRate: any;
  ssId: any;

  view_booking: boolean = true;

  fName: string = "";
  lName: string = "";
  card: string = "";
  cvv: string = "";
  doe: string = "";

  styProperty1 = {'display':''};
  styProperty2 = {'display':''};

  constructor(private bs: BookingService,
              private sss: ShipScheduleService,
              private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Payment Page");
        }

  ngOnInit(): void {
    
  }

// =================================================================================

total_payment(noOfHeadCount: number): void { 
    if(noOfHeadCount <= 4) {    //  Head-Count should not be more than 4 : costom condition
      // this.styProperty = {'display':''};
      this.perKmRate = sessionStorage.getItem("perKmRate_session");
      this.paymentAmount = (noOfHeadCount*this.perKmRate);
      console.log("Payment Amount: "+this.paymentAmount);
      this.styProperty1 = {'display':'none'};
    }
  }

//  ================ : Update Operation of Ship_Schedule SeatAvailability : ====================================

  update_SeatAvailability(noOfHeadCount: number): void {
    this.seatAvailability = sessionStorage.getItem("seatAvailability_session");
    let seatRef = {
      seatAvailability : (this.seatAvailability - noOfHeadCount),
    }
    this.sss.update_SeatAvailability(this.ssId, seatRef).subscribe()  // ssId taken from goto_booking()
  }

// ============================= : Submit Payment : ================================

submit_payment(paymentRef_info: NgForm, bookingRef_store: NgForm): void {
  let paymentRef = paymentRef_info.value;
  let card = paymentRef.card;
  let cvv = paymentRef.cvv;
  let doe = paymentRef.doe;

  let pEmail = sessionStorage.getItem("email_session");
  this.ssId = sessionStorage.getItem("ssId_session");
  this.noOfHeadCount = bookingRef_store.value.noOfHeadCount;

  if(card=="1234123412341234" && cvv=="454" && doe=="05/28") {
    console.log(paymentRef);

        if(pEmail != null) {
          let booking = {
              pName : bookingRef_store.value.pName,
              paymentAmount : this.paymentAmount,
              noOfHeadCount : bookingRef_store.value.noOfHeadCount,
              bookingDate : bookingRef_store.value.bookingDate,
          }
          this.view_booking = false;
          this.update_SeatAvailability(this.noOfHeadCount);
          console.log(booking);
          this.bs.passenger_todo_booking(pEmail, this.ssId, booking).subscribe(
            result=> {
              alert("Payment Successfully Done...");
              alert("Ticket Booked Successfully...");
              paymentRef_info.reset();
              bookingRef_store.reset();
              this.styProperty2 = {'display':'none'};
            }, error=> console.log(error), 
            // ()=> {this.router.navigate(["passenger/booking"], {skipLocationChange:true}); }
          )
        }
        else {
          alert("Do Login First...!!")
        }
  }
  else {
    alert("Invalid Bank Details...");
  }
 
}

//  ==========================================================================================================
     
  goto_ticket_page(): void {
    this.router.navigate(["passenger/ship_tickets"], {skipLocationChange:true});
  }

// ========================================================================================================

  view_bookings(): void {
    this.view_booking = true;
    this.router.navigate(["passenger/booking"], {skipLocationChange:true});
  }

// ========================================================================================================
}
