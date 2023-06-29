import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  
  root = '/';
  f_login: boolean = false;
  f_logout: boolean = true;
  f_reg: boolean = false;
  f_reg_const: boolean = true;
  // email: any;
  flag_admin: boolean = true;
  flag_passenger: boolean = true;

  hr: any = 0;
  min: any = 29;
  sec: any = 60;

  constructor(private router: Router) { }

  ngOnInit(): void {
    // this.email = sessionStorage.getItem("email_session");
    // sessionStorage.setItem("count_time", "start");
    this.login_disable_2nd();
    sessionStorage.setItem("count_time", "start");     
    this.countdown_sec();
    this.countdown_min();
    this.countdown_hr();
  }

  // =====================  Logout Operation  ========================

  logout() {
    this.f_login = false;
    this.f_reg = false;
    this.f_logout = true;
    this.flag_admin = true;
    this.flag_passenger = true;
    sessionStorage.removeItem("email_session");
    sessionStorage.removeItem("password_session");
    sessionStorage.removeItem("profile_name_session");
    sessionStorage.removeItem("ssId_session");
    sessionStorage.removeItem("perKmRate_session");
    sessionStorage.removeItem("seatAvailability_session");
    sessionStorage.removeItem("fi_sec");
    sessionStorage.removeItem("fi_min");
    sessionStorage.removeItem("fi_hr");
    sessionStorage.removeItem("count_time");
    this.refresh_page();
    this.router.navigate([""]);
  }

   // ==================  Login btn Operations  =======================

  login_disable_1st() {
      this.f_login = true;
      this.f_reg = true;
      this.f_logout = false;
      // this.toggle();
  }

  login_disable_2nd() {      //  works at the time on page refreshing
    // sessionStorage.setItem("email_session", "dummy@dummy.com");
    let email = sessionStorage.getItem("email_session");
    if(email != null) {
      this.f_login = true;
      this.f_reg = true;
      this.f_logout = false;
      this.toggle();
    } 
  }

  logout_disable() {
    this.f_login = false;
    this.f_reg = false;
    this.f_logout = true;
  }

  toggle() {
    let email = sessionStorage.getItem("email_session");
    console.log(email);
    if(email == "admin@ad.com") {
        this.flag_admin = false;
        this.flag_passenger = true;
    }
    else {
        this.flag_passenger = false;
        this.flag_admin = true;
    } 
  }


//  ============================================================================================================

    admin_home_page(): void {
      this.router.navigate(["admin/admin"], {skipLocationChange:true});
    }

    passenger_home_page(): void {
      this.router.navigate(["passenger/ship_tickets"], {skipLocationChange:true});
    }

    goto_login_page(): void {
      this.router.navigate(["login"], {skipLocationChange:true});
    }

    goto_register_page(): void {
      this.router.navigate(["register"], {skipLocationChange:true});
    }

    goto_profile_page(): void {
      this.router.navigate(["profile"], {skipLocationChange:true});
    }

    goto_aboutUs_page(): void {
      this.router.navigate(["about_us"], {skipLocationChange:true});
    }

// =========================== : Automatically Logout Operation: =========================================

countdown_sec() {
  setInterval(() => {
    if (this.sec > 1) {
      this.sec--;
      this.sec = this.sec<10 ? '0'+this.sec : this.sec
      sessionStorage.setItem("fi_sec", this.sec);
    } else {
      this.sec = 60;
    }
  }, 1000);
}

countdown_min() {
  setInterval(() => {
    if (this.min >= 0) {
      this.min--;
      this.min = this.min<10 ? '0'+this.min : this.min;
      sessionStorage.setItem("fi_min", this.min);
      if(this.min == 0) {
          alert("You are Out from this Session...!!")
          this.logout();
      }
    }
    // else {
    //   this.min = 60;
    // }
  }, 60000);
}

countdown_hr() {
  setInterval(() => {
    if (this.hr >= 0) {
      this.hr--;
      this.hr = this.hr<10 ? '0'+this.hr : this.hr;
      sessionStorage.setItem("fi_hr", this.hr);
    }
    //  else {
    //   this.hr = 60;
    // }
  }, 3600000);
}

//  ============================================================================================================

  refresh_page(): void {
    window.location.reload();
  }

//  ============================================================================================================
//  ============================================================================================================
  

}

