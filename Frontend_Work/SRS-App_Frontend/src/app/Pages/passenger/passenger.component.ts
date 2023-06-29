import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Passenger } from 'src/app/Interfaces/passenger';
import { PassengerService } from 'src/app/Services/passenger.service';

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.css']
})
export class PassengerComponent implements OnInit {

  passenger:Array<Passenger>=[];   // user array memory created..

  deleteMsg: string = "";
  updateMsg: string = "";
  storeMsg: string = "";

  pEmail: string = "";
  pName: string = "";
  pPhone: string = "";
  pPassword: string = "";
  pRole: string = "";
  pAddress: string = "";
  url: string = "";

  searchText: string = "";

  constructor(private ps: PassengerService, 
              private router: Router, 
              private titleService: Title) { 
                this.titleService.setTitle("All Passengers");
        }

  ngOnInit(): void {
    this.get_All_Passenger();
  }

  // ================================== :Retrieve Operation: ======================================
  get_All_Passenger(): void {
      this.ps.getAll_Passenger().subscribe(result=> {
        this.passenger=result;
        console.log("All data comming for profile compo...")
        console.log(result);   // details for all users
      })    
  }

  //==================================== :Delete Operation: =======================================

  delete_Passenger(pEmail: string): void {
      if(confirm("Are you sure..!?")) {
      this.ps.delete_Passenger(pEmail).subscribe(result=> {
        this.deleteMsg=result;
        alert(this.deleteMsg);
      },error=>console.log(error),()=> {
        this.get_All_Passenger();
      })
      }
  }

// ================================== :Update Operation: ========================================
  get_record_for_update(passenger: Passenger): void {
    this.pEmail = passenger.pEmail;
    this.pName = passenger.pName;
    this.pPhone = passenger.pPhone;
    this.pPassword = passenger.pPassword;
    this.pRole = passenger.pRole;
    this.pAddress = passenger.pAddress;
    this.url = passenger.url;
  }

  update_Passenger() {
    let passenger = { 
                pEmail : this.pEmail,
                pName : this.pName,
                pPhone : this.pPhone,
                pPassword : this.pPassword,
                pRole : this.pRole,
                pAddress : this.pAddress,
                url : this.url,
              }
    this.ps.update_Passenger(this.pEmail, passenger).subscribe(result=> {
      this.updateMsg=result;
      // alert(this.updateMsg);
      alert("Passenger Profile update Successfully...")
    },error=> console.log(error),()=> {
      this.get_All_Passenger();
    })
  }


//  ============================================================================================================

admin_home_page(): void {
  this.router.navigate(["admin/admin"], {skipLocationChange:true});
}

//  ============================================================================================================


}
