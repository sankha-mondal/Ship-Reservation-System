import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent {

  constructor(private router: Router,
              private titleService: Title) { 
                this.titleService.setTitle("Admin-Home");
  }

  admin_get_booking(): void {
    this.router.navigate(["admin/booking"], {skipLocationChange:true});
  }

  admin_get_passenger(): void {
    this.router.navigate(["admin/passenger"], {skipLocationChange:true});
  }

  admin_get_route_details(): void {
    this.router.navigate(["admin/routedesc"], {skipLocationChange:true});
  }

  admin_get_ship_schedule(): void {
    this.router.navigate(["admin/shipschedule"], {skipLocationChange:true});
  }

  admin_get_ship_details(): void {
    this.router.navigate(["admin/shipdetails"], {skipLocationChange:true});
  }

  admin_get_head_count(): void {
    this.router.navigate(["admin/headcount"], {skipLocationChange:true});
  }

  admin_goto_email_page(): void {
    this.router.navigate(["admin/email"], {skipLocationChange:true});
  }

}
