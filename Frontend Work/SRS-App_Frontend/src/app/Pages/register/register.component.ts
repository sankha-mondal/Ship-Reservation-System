import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { PassengerService } from 'src/app/Services/passenger.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  storeMsg: string = ""
  pName: string = "";
  pEmail: string = "";
  pPhone: string = "";
  pPassword: string = "";
  new_password: string = "";
  confirm_password: string = "";
  pAddress: string = "";
  pRole: string = "";
  url: string = "";

  constructor(private ps: PassengerService,
              private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Register Page");
        }

// =======================  : Registration of Passengers : ======================================

  storePassenger(userRef_store:NgForm): void {
    let new_pass = userRef_store.value.new_password;
    console.log("New Password: "+new_pass);
    let confirm_pass = userRef_store.value.confirm_password;
    console.log("Confirm Password: "+confirm_pass);
    console.log(userRef_store.value)

      if(new_pass == confirm_pass) {
          let passenger = {
              pEmail : this.pEmail, 
              pName : this.pName,
              pPhone : this.pPhone,
              pPassword : confirm_pass,
              pRole : this.pRole,
              pAddress : this.pAddress, 
              url : this.url
          }
          this.ps.storePassenger(passenger).subscribe(result=> {
            this.storeMsg=result;
            console.log(passenger);
            alert(this.storeMsg);
          },error=>console.log(error),()=> {
            // this.router.navigate(["login"], {skipLocationChange:true});
          })
          userRef_store.reset();
      }
      else {
        alert("Your Password and Confirm Password must be same...")
      }
    }

    
}
