import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { ShipDetails } from 'src/app/Interfaces/ship-details';
import { ShipDetailsService } from 'src/app/Services/ship-details.service';

@Component({
  selector: 'app-ship-details',
  templateUrl: './ship-details.component.html',
  styleUrls: ['./ship-details.component.css']
})
export class ShipDetailsComponent implements OnInit {

  ship_details: any;

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";

  shipId: string = "";
  shipName: string = "";
  shipModel: string = "";
  shipCapacity: Number = 0;
  shipReservationCapacity: Number = 0;
  per_kmRate: Number = 0;

  searchText: string = "";


  constructor(private sds: ShipDetailsService,
              private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Ship Details");
        }


    ngOnInit(): void {
      this.getAll_ShipDetails();
    }
  
// ================================== :Retrieve Operation: ======================================
  getAll_ShipDetails(): void {
      this.sds.getAll_ShipDetails().subscribe(result=> {
        this.ship_details=result;
        console.log("ship_details data comming...")
      })    
  } 
  
// =================================  : Store Ship Details : ======================================              

  store_ShipDetails(shipRef_store:NgForm): void {
    let ship = shipRef_store.value;
    console.log(ship);
    this.sds.store_ShipDetails(ship).subscribe(result=> {
      this.storeMsg=result;
      alert(this.storeMsg);
      this.getAll_ShipDetails();
    },error=>console.log(error),()=> {
      this.router.navigate(["routedesc"]);
    })
    shipRef_store.reset();
  }

//==================================== :Delete Operation: =======================================

delete_ShipDetails(shipId: string): void {
  if(confirm("Are you sure..!?")) {
   this.sds.delete_ShipDetails(shipId).subscribe(result=> {
     this.deleteMsg=result;
     alert(this.deleteMsg);
   },error=>console.log(error),()=> {
     this.getAll_ShipDetails();
   })
  }
 }

 // ================================== :Update Operation: ========================================
 get_record_for_update(ship_details: ShipDetails): void {
  this.shipId = ship_details.shipId;
  this.shipName = ship_details.shipName;
  this.shipModel = ship_details.shipModel;
  this.shipCapacity = ship_details.shipCapacity
  this.shipReservationCapacity = ship_details.shipReservationCapacity;
  this.per_kmRate = ship_details.per_kmRate;
}

update_routeDetails() {
  let ship_details = { 
              shipId : this.shipId,
              shipName : this.shipName,
              shipModel : this.shipModel,
              shipCapacity: this.shipCapacity,
              shipReservationCapacity: this.shipReservationCapacity,
              per_kmRate: this.per_kmRate,
            }
  this.sds.update_shipDetails(this.shipId, ship_details).subscribe(result=> {
    this.updateMsg=result;
    alert(this.updateMsg);
  },error=> console.log(error),()=> {
    this.getAll_ShipDetails();
  })
}


//  ============================================================================================================

admin_home_page(): void {
  this.router.navigate(["admin/admin"], {skipLocationChange:true});
}

//  ============================================================================================================



}
