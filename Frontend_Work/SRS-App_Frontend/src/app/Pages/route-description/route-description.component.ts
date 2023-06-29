import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { RouteDetails } from 'src/app/Interfaces/route-details';
import { RouteDetailsService } from 'src/app/Services/route-details.service';

@Component({
  selector: 'app-route-description',
  templateUrl: './route-description.component.html',
  styleUrls: ['./route-description.component.css']
})
export class RouteDescriptionComponent implements OnInit {

  route_details: any;

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";

  routeId: string = "";
  sourcePoint: string = "";
  destinationPoint: string = "";
  distanceKm: Number = 0;

  searchText: string = "";

  constructor(private rds: RouteDetailsService,
              private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Route Details");
        }


    ngOnInit(): void {
      this.getAll_RouteDetails();
    }
  
// ================================== :Retrieve Operation: ======================================
  getAll_RouteDetails(): void {
      this.rds.getAll_RouteDetails().subscribe(result=> {
        this.route_details=result;
        console.log("data comming...")
      })    
  }


// =======================  : Store Route Details : ======================================              

  store_RouteDetails(routeRef_store:NgForm): void {
      let route = routeRef_store.value;
      console.log(route);
      this.rds.store_RouteDetails(route).subscribe(result=> {
        this.storeMsg=result;
        alert(this.storeMsg);
        this.getAll_RouteDetails();
      },error=>console.log(error),()=> {
        this.router.navigate(["routedesc"]);
      })
      routeRef_store.reset();
  }


//==================================== :Delete Operation: =======================================

deleteRoute_Details(routeId: string): void {
    if(confirm("Are you sure..!?")) {
     this.rds.delete_RouteDetails(routeId).subscribe(result=> {
       this.deleteMsg=result;
       alert(this.deleteMsg);
     },error=>console.log(error),()=> {
       this.getAll_RouteDetails();
     })
    }
   }


// ================================== :Update Operation: ========================================
  get_record_for_update(route_details: RouteDetails): void {
    this.routeId = route_details.routeId;
    this.sourcePoint = route_details.sourcePoint;
    this.destinationPoint = route_details.destinationPoint;
    this.distanceKm = route_details.distanceKm;
  }

  update_routeDetails() {
    let route_details = { 
                routeId : this.routeId,
                sourcePoint : this.sourcePoint,
                destinationPoint : this.destinationPoint,
                distanceKm: this.distanceKm,
              }
    this.rds.update_routeDetails(this.routeId, route_details).subscribe(result=> {
      this.updateMsg=result;
      alert(this.updateMsg);
    },error=> console.log(error),()=> {
      this.getAll_RouteDetails();
    })
  }


//  ============================================================================================================

admin_home_page(): void {
  this.router.navigate(["admin/admin"], {skipLocationChange:true});
}

//  ============================================================================================================



}
