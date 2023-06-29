import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutUsComponent } from './about-us/about-us.component';
import { AdminPageComponent } from './Admin-Only/admin-page/admin-page.component';
import { HomeComponent } from './home/home.component';
import { BookingPassengerComponent } from './Pages/booking-passenger/booking-passenger.component';
import { BookingComponent } from './Pages/booking/booking.component';
import { EmailComponent } from './Pages/email/email.component';
import { HeadCountComponent } from './Pages/head-count/head-count.component';
import { LoginComponent } from './Pages/login/login.component';
import { PassengerComponent } from './Pages/passenger/passenger.component';
import { PaymentPassengerComponent } from './Pages/payment-passenger/payment-passenger.component';
import { ProfileComponent } from './Pages/profile/profile.component';
import { RegisterComponent } from './Pages/register/register.component';
import { RouteDescriptionComponent } from './Pages/route-description/route-description.component';
import { ShipDetailsComponent } from './Pages/ship-details/ship-details.component';
import { ShipScheduleComponent } from './Pages/ship-schedule/ship-schedule.component';
import { ShipTicketsComponent } from './Pages/ship-tickets/ship-tickets.component';
import { NavbarComponent } from './Parts/navbar/navbar.component';

const routes: Routes = [
  { path:'', component:HomeComponent},
  // { path:'nav', component:NavbarComponent},
  { path:'about_us', component: AboutUsComponent},
  { path:'passenger/ship_tickets', component:ShipTicketsComponent},
  { path:'passenger/payment', component:PaymentPassengerComponent},
  { path:'login', component:LoginComponent},
  { path:'register', component:RegisterComponent},
  { path:'passenger/booking', component:BookingPassengerComponent},
  { path:'profile', component:ProfileComponent},
  { path:'admin/admin', component:AdminPageComponent},
  { path:'admin/shipdetails', component:ShipDetailsComponent},
  { path:'admin/shipschedule', component:ShipScheduleComponent},
  { path:'admin/routedesc', component:RouteDescriptionComponent},
  { path:'admin/passenger', component: PassengerComponent},
  { path:'admin/booking', component:BookingComponent},
  { path:'admin/headcount', component: HeadCountComponent},
  { path:'admin/email', component: EmailComponent},
  { path:'passenger/email', component: EmailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
