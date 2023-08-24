import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Data, Router } from '@angular/router';
import { Passenger } from 'src/app/Interfaces/passenger';
import { BookingService } from 'src/app/Services/booking.service';
import { PassengerService } from 'src/app/Services/passenger.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  passenger:Array<Passenger>=[];   // user array memory created..
  booking: any;

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

  bookingId: string = "";
  paymentAmount: number = 0;
  bookingDate: any;

  flag_cred: number =0;
  flag_login: number =0;
  
  new_password: string = "";
  confirm_password: string = "";

  searchText: any;
  checkPswd: string = "";
  styProperty1 = {'display':''};
  styProperty2 = {'display':''};

  constructor(private ps: PassengerService, 
              private bs: BookingService,
              private routen: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Profile page");
        }

  ngOnInit(): void {
    this.get_All_Passenger();
    // this.getAll_Booking();
  }

  // ================================== :Retrieve Passenger Operation: ======================================
  get_All_Passenger(): void {
    this.ps.getAll_Passenger().subscribe(result=> {
      this.passenger=result;
      
      console.log("All data comming for profile compo...")
      console.log(result);   // details for all users
      // this.get_record_for_Update_Delete();
    })    
  }

  // ================================ :Update Passenger Profile Operation: ====================================
  get_record_for_Update_Delete(passwordRef_update: NgForm) {
    let email = sessionStorage.getItem("email_session");
    console.log(passwordRef_update.value);
    let old_password = passwordRef_update.value.checkPswd;
    console.log("Old Password: "+old_password)  

    for(let i=0; i<this.passenger.length; i++) {
      
      //   console.log(this.users);
      if(this.passenger[i].pEmail == email && this.passenger[i].pPassword == old_password) {
        console.log("Email getting for particular person "+email);
        console.log("Password getting for particular person "+old_password);
        this.pEmail = email;
        this.pName = this.passenger[i].pName;
        this.pPhone = this.passenger[i].pPhone;
        this.pPassword = this.passenger[i].pPassword;
        this.new_password = this.passenger[i].pPassword;  // for checking old to new password
        this.pRole = this.passenger[i].pRole;
        this.pAddress = this.passenger[i].pAddress;
        this.url = this.passenger[i].url;
        this.flag_cred = 1
        this.flag_login = 1;
        this.styProperty1 = {'display':'none'};
      } 
    } 
    if(this.flag_login == 0) {
      if(email == null) {
         alert("Do Login First...")
      }
      else {
        alert("Put Your Correct password..!!")
      }
    }
    sessionStorage.setItem("profile_name_session", this.pName);
    // let email = sessionStorage.getItem("email_session");
      console.log("Get Booking for one email "+email);
      if(email == "admin@ad.com") {
        this.styProperty2 = {'display':'none'};
      }
      else {
        this.get_Booking_ByEmail();
      }  
  }


  update_Passenger_In_Db(userRef_update: NgForm) {

    let new_pass = userRef_update.value.new_password;
    console.log("New Password: "+new_pass);
    let confirm_pass = userRef_update.value.confirm_password;
    console.log("Confirm Password: "+confirm_pass);

    if(this.pPassword != new_pass) {
        if(new_pass == confirm_pass) {
          let passenger = {
                    pEmail : this.pEmail, 
                    pName : this.pName,
                    pPhone : this.pPhone,
                    pPassword : this.confirm_password,
                    pRole : this.pRole,
                    pAddress : this.pAddress, 
                    url : this.url
          }
            this.ps.update_Passenger(this.pEmail, passenger).subscribe(result=> {
                this.updateMsg=result;
                sessionStorage.removeItem("password_session");
                sessionStorage.setItem("password_session", this.new_password);
                console.log("Update Successfully...");
                alert("Password Updated Successfully...\nHave a nice day...");
                    if(this.pEmail == "admin@ad.com") {
                      this.routen.navigate(["admin/admin"], {skipLocationChange:true});
                    }
                    else {
                      this.routen.navigate(["passenger/ship_tickets"], {skipLocationChange:true});
                    }
              }, error=> console.log(error)
            )
        }
        else {
          alert("Your New Password and Confirm Password must be same...")
        }
    }
    else {
      alert("Your New Password and Old Password are same...\nTry with another to Update your Password...")
    }
  }

  //==================================== :Delete Operation: =======================================
  delete_Passenger(): void {
    let passenger = {
      pEmail : this.pEmail
    }
    if(confirm("Are you sure..!?")) {
     this.ps.delete_Passenger(this.pEmail).subscribe(result=> {
       this.deleteMsg=result;
       console.log("Profile deleted..");
       alert("Your Profile removed Successfully..");
       window.location.reload();
     },error=>console.log(error),()=> {
      sessionStorage.removeItem("email_session");
      sessionStorage.removeItem("password_session");
      this.routen.navigate([""]);
     })
    }
   }


// ================================== :Retrieve Booking byEmail Operation: ====================================
  get_Booking_ByEmail(): void {
      let passenger = {
        pEmail : this.pEmail
      }
          this.bs.get_Booking_ByEmail(this.pEmail).subscribe(result=> {
          this.booking = result; 
          console.log("All booking data comming for Bookings compo...")
          console.log(result);   // details for all users
          // this.searchText = sessionStorage.getItem("profile_name_session");
        })
    }

// ================================== : Delete Booking for One Profile : ====================================

  delete_Booking(bookingId: string): void {
      if(confirm("Are you sure..!?")) {
      this.bs.delete_Booking(bookingId).subscribe(result=> {
        this.deleteMsg=result;
        alert(this.deleteMsg);
      },error=>console.log(error),()=> {
       // this.get_Booking_ByEmail();
      })
    }
  }

  // =====================================================================================================
  // =====================================================================================================



}


 

