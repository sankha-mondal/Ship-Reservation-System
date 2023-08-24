import {Component, inject} from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Booking } from 'src/app/Interfaces/booking';
import { ShipSchedule } from 'src/app/Interfaces/ship-schedule';
import { BookingService } from 'src/app/Services/booking.service';
import { HeadCountService } from 'src/app/Services/head-count.service';

import {MatButtonModule} from '@angular/material/button';
import { Title } from '@angular/platform-browser';
import { EmailService } from 'src/app/Services/email.service';
import { ShipScheduleService } from 'src/app/Services/ship-schedule.service';

@Component({
  selector: 'app-booking-passenger',
  templateUrl: './booking-passenger.component.html',
  styleUrls: ['./booking-passenger.component.css']
})
export class BookingPassengerComponent {


  booking_ByEmail: Array<Booking> = [];
  headCount_byBookId: any;
  get_Date_Booking_Email: any;   // for Email Operation

  hcName: string = "";
  bookingId: string = "";
  noOfHeadCount: number = 0;
  add_headCount: boolean = false;

  ssId: any;
  seatAvailability: any;

  deleteMsg: string = "";
  storeMsg: string = "";
  searchText: string = "";

  fakeArray = new Array(0);
  hcLength: number = 0;

  recipient: string = "";
  subject: string = "";
  msgBody: string = "";
  attachment: string = "";
  sendMsg: string = "";
  styProperty_email = {'display':'none'};

  pageNo: number = 1;
  count: number = 0;
  tableSize: number = 5;
  tableSizes: any = [5, 10, 15, 20];

  constructor(private bs: BookingService,
              private sss: ShipScheduleService,
              private hcs: HeadCountService,
              private es: EmailService,
              private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Passenger-Bookings");
        }

  ngOnInit(): void {
      this.get_my_Bookings();
  }

// ================================ : Get Booking by One Email : =========================================  

get_my_Bookings(): void {
    let pEmail = sessionStorage.getItem("email_session");
    this.bs.get_Booking_ByEmail(pEmail).subscribe(
      result=> {
        this.booking_ByEmail = result; 
        console.log(result);
      }, error=> console.log(error)
    )
  }

//================================== :Delete Operation of Booking : Op:3 =======================================

  delete_Booking(bookingId: string, noOfHeadCount: number): void {
    if(confirm("Are you sure..!?")) {
    this.bs.delete_Booking(bookingId).subscribe(result=> {
      this.deleteMsg=result;
      alert(this.deleteMsg);
      this.get_my_Bookings();
    },error=>console.log(error),()=> {
      
    })
    }
  }

//  ============================= : Store Head-Counts : =========================================================

  add_HeadCount_By_BookingId(bookingId: string, noOfHeadCount: number): void {
    if(noOfHeadCount <= 4) {    //  Head-Count should not be more than 4 : costom condition
      this.bookingId = bookingId;
      this.noOfHeadCount = noOfHeadCount;
      this.fakeArray = new Array(noOfHeadCount);
      this.get_HeadCount_By_BookingId(this.bookingId); // for Add button enable/disable Operation
      console.log("Head Count Booking Id: "+this.bookingId);
    }
  }

  store_HeadCount(hcRef_store: NgForm): void {
    let headCount = hcRef_store.value;
    console.log("Head Count Booking Id: "+this.bookingId);
    console.log(headCount);
    
    if(this.hcLength < this.noOfHeadCount) {
        this.hcs.store_HeadCount(this.bookingId, headCount).subscribe(result=> {
        alert("Head-Counter added Successfully...")
        this.add_headCount = true;
        },error=>console.log(error),()=> {
        this.router.navigate(["passenger/booking"]);
      })
      hcRef_store.reset();
    }
    else {
      // this.openSnackBar_alert("Seat has been already Occupied...!!!")
      alert("Seats are already Occupied...!!!")
    }   
  }

// ========================= : Get Head-Counts of Particular One Booking Id : ================================

  get_HeadCount_By_BookingId(bookingId: string): void {   
    this.hcs.get_HeadCount_By_BookingId(bookingId).subscribe(result=> {
      this.headCount_byBookId = result;
      this.hcLength = Object.keys(this.headCount_byBookId).length;
      console.log("Length of headCount_byBookId: "+this.hcLength);
      console.log(this.headCount_byBookId);
    }, error=> console.log(error))
  }

// ==================== : Retrieve Date for Booking-Receipt : =====================

  get_Date_Booking_Receipt(bookingId: string): void {
    let receipt_data_url = "http://localhost:8585/book/passenger/receipt?bookingId="+bookingId;
    let url =  receipt_data_url;
    receipt_data_url = "";
    window.open(url, "_blank"); 
  }

// ================== : Retrieve Date for Head-Count-Receipt : =====================

  get_Data_HC_Receipt(bookingId: string): void {
    let receipt_hc_url = "http://localhost:8585/book/hc/receipt?bookingId="+bookingId;
    let url =  receipt_hc_url;
    receipt_hc_url = "";
    window.open(url, "_blank");
  }

//  ==========================================================================================================
  // ======================  : Table Size Controls : ===================

  on_table_data_change(event: any) {
    this.pageNo = event;
    this.get_my_Bookings();   // use for real time data load
  }

  //  =====================  : Page Number Controls : ===================

  on_table_size_change(event: any) {
    this.tableSize = event.target.value;
    this.pageNo = 1;
    // this.get_All_User();   // use for real time data load
  }
//  ==========================================================================================================
     
  goto_ticket_page(): void {
    this.router.navigate(["passenger/ship_tickets"], {skipLocationChange:true});
  }

// ========================================================================================================

  goto_payment_page(bookingId: string, paymentAmount: string): void {
    sessionStorage.setItem("bookingId_session",bookingId);
    sessionStorage.setItem("payment_session",paymentAmount);
    this.router.navigate(["passenger/payment"], {skipLocationChange: true});
  }

//  ================ : Update Operation of Ship_Schedule SeatAvailability : ====================================

  update_SeatAvailability(noOfHeadCount: number): void {
    this.seatAvailability = sessionStorage.getItem("seatAvailability_session");
    let seatRef = {
      seatAvailability : (this.seatAvailability + noOfHeadCount),
    }
    this.sss.update_SeatAvailability(this.ssId, seatRef).subscribe()  // ssId taken from goto_booking()
  }

// ===================================== : Email Service : =============================================

  get_Email(bookingId: string): void {
    this.bs.get_Date_Booking_for_Email(bookingId).subscribe(
      result=> {
        this.get_Date_Booking_Email = result;
        console.log(this.get_Date_Booking_Email);

        //  ========= :Geting Email Operation for Passenger: ========
        let email = {
          recipient : sessionStorage.getItem("email_session"),
          subject : "SRS ||  Booking Details",
          // msgBody : "Hey,\n        This is your Booking Details.\n        Have a nice day.\n\nThanks & Regards,\nAdmin\nadmin@ad.com"
          msgBody : this.get_Date_Booking_Email    //  taking JSON booking Details into msgBody
        }
        this.styProperty_email = {'display':''};
        this.es.get_Email(email).subscribe(
          res=>{
            this.styProperty_email = {'display':'none'};
            this.sendMsg = res;
            alert(this.sendMsg);
           }, error=> {
            console.log(error);
            this.styProperty_email = {'display':'none'};
            alert("Email not Sent...!\nTurn on your Internet...")
          }
        )
      }, error=> console.log(error)
    )
  }


// ========================================================================================================
// ========================================================================================================




}
