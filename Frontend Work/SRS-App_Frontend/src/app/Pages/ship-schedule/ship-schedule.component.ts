import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ShipSchedule } from 'src/app/Interfaces/ship-schedule';
import { ShipScheduleService } from 'src/app/Services/ship-schedule.service';

@Component({
  selector: 'app-ship-schedule',
  templateUrl: './ship-schedule.component.html',
  styleUrls: ['./ship-schedule.component.css']
})
export class ShipScheduleComponent implements OnInit {

  ship_schedule: any;

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";

  ss_id: number = 0;
  journeyDate: any;
  journeyTime: any;
  seatAvailability: number = 0;

  searchText: string = "";

  constructor(private sss: ShipScheduleService,
              private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Ship Schedule");
        }

  ngOnInit(): void {
    this.getAll_ShipSchedul();
  }            

  // ================================== :Retrieve Operation: ======================================
  getAll_ShipSchedul(): void {
    this.sss.getAll_ShipSchedul().subscribe(result=> {
      this.ship_schedule=result;
      console.log("data comming...")
    })    
  }

 // ================================== :Store Operation: ======================================
 store_ShipSchedul(shipSchRef_store: NgForm): void {
  let shipScheduleReq = shipSchRef_store.value;
  let route_Id = shipSchRef_store.value.routeId;
  let shipDetails_Id = shipSchRef_store.value.shipDetId;
  console.log(shipScheduleReq);
  console.log(route_Id);
  console.log(shipDetails_Id);
  this.sss.store_ShipSchedul(route_Id, shipDetails_Id, shipScheduleReq).subscribe(
    result=> {
      console.log("Ship Schedule data Stored..")
      this.storeMsg = result;
      alert(this.storeMsg);
    }, error => console.log(error), 
    ()=> {
      this.getAll_ShipSchedul();
    })
 }

  //==================================== :Delete Operation: =======================================

  delete_ShipSchedule(ss_Id: number): void {
        if(confirm("Are you sure..!?")) {
        this.sss.delete_ShipSchedule(ss_Id).subscribe(result=> {
          this.deleteMsg=result;
          alert(this.deleteMsg);
        },error=>console.log(error),()=> {
          this.getAll_ShipSchedul();
        })
      }
    }

// ================================== :Update Operation: ========================================
  get_record_for_update(ship_schedule: ShipSchedule): void {
    this.ss_id = ship_schedule.ss_id;
    this.journeyDate = ship_schedule.journeyDate;
    this.journeyTime = ship_schedule.journeyTime;
    this.seatAvailability= ship_schedule.seatAvailability;
  }

  update_routeDetails() {
    let ship_schedule = { 
                ss_id : this.ss_id,
                journeyDate : this.journeyDate,
                journeyTime : this.journeyTime,
                seatAvailability: this.seatAvailability,
              }
    this.sss.update_routeDetails(this.ss_id, ship_schedule).subscribe(result=> {
      this.updateMsg=result;
      alert(this.updateMsg);
    },error=> console.log(error),()=> {
      this.getAll_ShipSchedul();
    })
  }



//  ============================================================================================================

admin_home_page(): void {
  this.router.navigate(["admin/admin"], {skipLocationChange:true});
}

//  ============================================================================================================


}
