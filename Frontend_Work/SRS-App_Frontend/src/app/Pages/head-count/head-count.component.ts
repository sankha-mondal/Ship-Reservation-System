import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { HeadCount } from 'src/app/Interfaces/head-count';
import { HeadCountService } from 'src/app/Services/head-count.service';

@Component({
  selector: 'app-head-count',
  templateUrl: './head-count.component.html',
  styleUrls: ['./head-count.component.css']
})
export class HeadCountComponent implements OnInit {

  headCount: Array<HeadCount> = [];
  headCount_byBookId: any;

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";

  hcId: number = 0;
  hcName: string = "";
  hcAge: number = 0;
  hcGender: string = "";
  bookId_hc: string = "";

  pageNo: number = 1;
  count: number = 0;
  tableSize: number = 5;
  tableSizes: any = [5, 10, 15, 20];

  searchText_headCount: string = "";

  constructor(private hcs: HeadCountService,
              private router: Router, 
              private titleService:Title) { 
                this.titleService.setTitle("Head-Counts");
        }

    ngOnInit(): void {
      // this.get_All_Passenger();
       this.getAll_HeadCount();
     }

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

// =============================== :Retrieve Booking byEmail Operation: Op:10 ====================================
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

  // ======================  : Table Size Controls : ===================

  on_table_data_change(event: any) {
    this.pageNo = event;
    this.getAll_HeadCount();   // use for real time data load
  }

  //  =====================  : Page Number Controls : ===================

  on_table_size_change(event: any) {
    this.tableSize = event.target.value;
    this.pageNo = 1;
    // this.get_All_User();   // use for real time data load
  }

//  ============================================================================================================


//  ============================================================================================================

    admin_home_page(): void {
      this.router.navigate(["admin/admin"], {skipLocationChange:true});
    }

    goto_booking_page(): void {
      this.router.navigate(["admin/booking"], {skipLocationChange:true});
    }

//  ============================================================================================================


}











