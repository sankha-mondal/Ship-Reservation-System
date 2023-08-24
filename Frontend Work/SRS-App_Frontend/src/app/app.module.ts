import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './Parts/navbar/navbar.component';
import { ShipTicketsComponent } from './Pages/ship-tickets/ship-tickets.component';
import { LoginComponent } from './Pages/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './Pages/register/register.component';
import { ProfileComponent } from './Pages/profile/profile.component';
import { ShipDetailsComponent } from './Pages/ship-details/ship-details.component';
import { ShipScheduleComponent } from './Pages/ship-schedule/ship-schedule.component';
import { RouteDescriptionComponent } from './Pages/route-description/route-description.component';
import { PassengerComponent } from './Pages/passenger/passenger.component';
import { BookingComponent } from './Pages/booking/booking.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { AdminPageComponent } from './Admin-Only/admin-page/admin-page.component';
import { HeadCountComponent } from './Pages/head-count/head-count.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FooterComponent } from './Parts/footer/footer.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { BookingPassengerComponent } from './Pages/booking-passenger/booking-passenger.component';
import { PaymentPassengerComponent } from './Pages/payment-passenger/payment-passenger.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { EmailComponent } from './Pages/email/email.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    ShipTicketsComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    ShipDetailsComponent,
    ShipScheduleComponent,
    RouteDescriptionComponent,
    PassengerComponent,
    BookingComponent,
    AdminPageComponent,
    HeadCountComponent,
    FooterComponent,
    AboutUsComponent,
    BookingPassengerComponent,
    PaymentPassengerComponent,
    EmailComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule, ReactiveFormsModule, HttpClientModule,
    Ng2SearchPipeModule, NgxPaginationModule, BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
