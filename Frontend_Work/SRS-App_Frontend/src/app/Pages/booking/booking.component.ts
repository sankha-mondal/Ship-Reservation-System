import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Booking } from 'src/app/Interfaces/booking';
import { HeadCount } from 'src/app/Interfaces/head-count';
import { BookingService } from 'src/app/Services/booking.service';
import { HeadCountService } from 'src/app/Services/head-count.service';
import { PassengerService } from 'src/app/Services/passenger.service';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit{

  // booking:Array<Booking>=[];   // user array memory created..
  booking: any;
  passenger: any;
  booking_byEmail: any;
  pass_email_date: any;
  OnePassInfo_Of_Booking: any;
  Pass_Details_on_jDate_shipName: any;

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";

  bookingId: string = "";
  pName: string = "";
  paymentAmount: number = 0;
  noOfHeadCount: number = 0;
  bookingDate: any;

  pEmail: any;
  jDate: any;
  date: any;
  boDate: any;
  mail1: any;
  mail2: any;

  jDate1: any;
  sName1: any;

  searchText: string = "";
  searchText_all: string = "";
  searchText_email: string = "";

  routeId: string = "";
  journeyDate: string = "";
  journeyTime: string = "";

  shipId: string = "";
  shipName: string = "";
  shipModel: string = "";

  pageNo: number = 1;
  count: number = 0;
  tableSize: number = 5;
  tableSizes: any = [5, 10, 15, 20];

  constructor(private bs: BookingService,
              private ps: PassengerService,
              private hcs: HeadCountService,
              private router: Router,private titleService:Title) { 
                this.titleService.setTitle("SRS-Bookings");
              }
  ngOnInit(): void {
   // this.get_All_Passenger();
    this.getAll_Booking();
    this.getAll_HeadCount();
  }

// ==================================== :Retrieve All_Booking Operation: Op:1 ======================================
    getAll_Booking(): void {
      this.bs.getAll_Booking().subscribe(result=> {
        this.booking=result;
        console.log("All booking data comming for Bookings compo...")
        console.log(result);   // details for all users
        // this.get_record_for_Booking_byEmail();
        this.get_All_Passenger();
      })    
    }

// ==================================== :Retrieve All_Passenger Operation: ======================================
    get_All_Passenger(): void {
      this.ps.getAll_Passenger().subscribe(result=> {
        this.passenger=result;
        console.log("All data comming for Bookings compo...")
        console.log(result);   // details for all users
      })    
    }

// ==============================  : Booking Retrive by Passenger-Email : Op:2 ====================================
  get_record_for_Booking_byEmail(emailRef_pass:NgForm): void {

    console.log(emailRef_pass.value)
    // let email = sessionStorage.getItem("email_session");
    let email1 = emailRef_pass;
    for(let i=0; i<this.passenger.length; i++) {
      
      //   console.log(this.users);
      if(this.passenger[i].pEmail == email1) {
        console.log("email getting for particular person "+email1);
        this.pEmail = email1;
      }
    }   
    this.bs.get_Booking_ByEmail(this.mail1).subscribe(result=> {
      console.log(result);
      this.booking_byEmail = result;
    }, error=> console.log(error))
  }


//================================== :Delete Operation of Booking : Op:3 =======================================

    delete_Booking(bookingId: string): void {
      if(confirm("Are you sure..!?")) {
      this.bs.delete_Booking(bookingId).subscribe(result=> {
        this.deleteMsg=result;
        alert(this.deleteMsg);
      },error=>console.log(error),()=> {
        this.getAll_Booking();
      })
      }
    }

// ================================== :Update Operation of Booking : Op: 4 ========================================
    get_record_for_Booking_update(booking: Booking): void {
      this.bookingId = booking.bookingId;
      this.pName = booking.pName;
      this.paymentAmount = booking.paymentAmount;
      this.noOfHeadCount = booking.noOfHeadCount;
      this.bookingDate = booking.bookingDate;
    }

    update_Booking() {
      let booking = { 
                  bookingId : this.bookingId,
                  pName : this.pName,
                  paymentAmount : this.paymentAmount,
                  noOfHeadCount : this.noOfHeadCount,
                  bookingDate: this.bookingDate,
                }
      this.bs.update_Booking(this.bookingId, booking).subscribe(result=> {
        this.updateMsg=result;
        alert("Booking of id "+this.bookingId+" Updated Successfully...")
      },error=> console.log(error),()=> {
        this.getAll_Booking();
      })
    }


  // ====================== :Retrieve Passenger-Email for particular Date: Op:5 ========================================
  get_PassEmail_on_JourneyDate(dateRef_email:NgForm): void {
    console.log(dateRef_email.value)
    let date = dateRef_email;

    this.bs.get_PassEmail_on_JourneyDate(this.jDate).subscribe(result=> {
      this.pass_email_date=result;
      console.log("All Passenger comming on Particular Date...")
      console.log(result);   // details for all users
    })
  }

  // =============================  : Retrieve Full Booking Info of One Passenger: Op:6  ========================================
  get_Full_Booking_Deatil_Of_OnePass(emailFullRef_booking:NgForm): void {
      console.log(emailFullRef_booking.value)
      let mail = emailFullRef_booking;

      this.bs.get_Full_Booking_Deatil_Of_OnePass(this.mail2).subscribe(result=> {
        this.OnePassInfo_Of_Booking = result;
        console.log("All Passenger comming on Particular Date...")
        console.log(result);   // details for all users
      })
  }

  // =============== :Retrieve Passenger-Email on particular Ship-Details on particular Journey-Date: Op:11 =======================

    get_Pass_Details_on_jDate_shipName(jdate_sName_Ref:NgForm): void {
      console.log(jdate_sName_Ref.value)  // [Object Object]
      let jDate = jdate_sName_Ref.value.jDate1;   // [Object -> Value]
      let sName = jdate_sName_Ref.value.sName1;   // [Object -> Value]

      console.log(jDate);
      console.log(sName);
       
      this.bs.get_Pass_Details_on_jDate_shipName(jDate, sName).subscribe(result=> {
        console.log(result);
        this.Pass_Details_on_jDate_shipName = result;
      }, error=> console.log(error))
    }

//  ============================================================================================================

  //  ==========================================================================================================
  // ======================  : Table Size Controls : ===================

      on_table_data_change(event: any) {
        this.pageNo = event;
        this.getAll_Booking();   // use for real time data load
      }

      //  =====================  : Page Number Controls : ===================

      on_table_size_change(event: any) {
        this.tableSize = event.target.value;
        this.pageNo = 1;
        // this.get_All_User();   // use for real time data load
      }
  //  ==========================================================================================================



//  ============================================================================================================

  admin_home_page(): void {
    this.router.navigate(["admin/admin"], {skipLocationChange:true});
  }

  goto_headcount_page(): void {
    this.router.navigate(["admin/headcount"], {skipLocationChange:true});
  }

//  ============================================================================================================


// ========================================================================================================================= -->
// ========================================================================================================================= -->
// ============================================  : Head Count of Booking : ================================================== -->
// ========================================================================================================================= -->
// ========================================================================================================================= -->

    headCount: Array<HeadCount> = [];
    headCount_byBookId: any;

    hcId: number = 0;
    hcName: string = "";
    hcAge: number = 0;
    hcGender: string = "";
    bookId_hc: string = "";

    searchText_headCount: string = "";

// ============================= :Retrieve All Head-Count Operation: Op:7 ======================================

    getAll_HeadCount(): void {
      this.hcs.getAll_HeadCount().subscribe(result=> {
        this.headCount = result
      })
    }


//============================== :Delete Operation of Head-Count: Op:8 ==========================================

  delete_HeadCount(headCountId: number): void {
    if(confirm("Are you sure..!?")) {
    this.hcs.delete_HeadCount(headCountId).subscribe(result=> {
        this.deleteMsg=result;
        alert(this.deleteMsg);
    },error=>console.log(error),()=> {
      this.getAll_HeadCount();
    })
    }
  }


// ================================== :Update Operation of Head_Count: Op:9 ========================================
    get_record_for_HeadCount_update(headCount: HeadCount): void {
      this.hcId = headCount.hcId;
      this.hcName = headCount.hcName;
      this.hcAge = headCount.hcAge;
      this.hcGender = headCount.hcGender;
    }

    update_HeadCount() {
      let headCount = { 
                  hcId : this.hcId,
                  hcName : this.hcName,
                  hcAge : this.hcAge,
                  hcGender : this.hcGender
                }
      this.hcs.update_HeadCount(this.hcId, headCount).subscribe(result=> {
        this.updateMsg=result;
        alert(this.updateMsg)
      },error=> console.log(error),()=> {
        this.getAll_HeadCount();
      })
      
    }

// =============================== :Retrieve Head Count by Booking ID: Op:10 ====================================
get_record_for_HeadCount_byBookingId(bookingRef_Id:NgForm): void {
    console.log(bookingRef_Id.value)  // [Object Object]
    let bookId = bookingRef_Id.value.bookId_hc;   // [Object -> Value]
    console.log(bookId)
    
    this.hcs.get_HeadCount_By_BookingId(bookId).subscribe(result=> {
      console.log(result);
      this.headCount_byBookId = result;
    }, error=> {console.log(error); bookingRef_Id.reset(); this.headCount_byBookId = null;
      alert("Data Empty of Booking Id "+bookId);    
  })

}


//  ============================================================================================================
//  ============================================================================================================





}
