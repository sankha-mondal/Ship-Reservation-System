import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Booking } from '../Interfaces/booking';
import { BookingInfoPEmail } from '../Interfaces/booking-info-p-email';
import { JDateEmail } from '../Interfaces/j-date-email';
import { JDateShipDetEmail } from '../Interfaces/j-date-ship-det-email';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(public http: HttpClient) { }  // DI for HttpClient

  private apiUrlbs = "http://localhost:8585/book";

// ====================================================================================================

  getAll_Booking() : Observable<Booking[]> {
    return this.http.get<Booking[]>(this.apiUrlbs+"/getAll");
  }


  get_Booking_ByEmail(pEmail: any) : Observable<Booking[]> {
    return this.http.get<Booking[]>(this.apiUrlbs+"/getByPassEmail/"+pEmail);
  } 


  get_PassEmail_on_JourneyDate(journeyDate: string):Observable<JDateEmail[]> {
    return this.http.get<JDateEmail[]>(this.apiUrlbs+"/getByJDate/"+journeyDate);
  }


  get_Full_Booking_Deatil_Of_OnePass(pEmail: string): Observable<BookingInfoPEmail[]> {
    return this.http.get<BookingInfoPEmail[]>(this.apiUrlbs+"/get_Full_Booking_Deatil_Of_OnePass/"+pEmail);
  }

  
  get_Pass_Details_on_jDate_shipName(jDate: string, sName: string): Observable<JDateShipDetEmail[]> {
      return this.http.get<JDateShipDetEmail[]>(this.apiUrlbs+"/get_Full_Booking_Deatil_Of_jDate_shipName/"+jDate+"/"+sName);
  }


  // http://localhost:8585/book/delete/{bookingId}

  delete_Booking(bookingId: string):Observable<string> {
    return this.http.delete(this.apiUrlbs+"/delete/"+bookingId,{responseType:"text"});
  }

  // http://localhost:8585/book/update/{bookingId}

  update_Booking(bookingId: any, bookingDetails:any):Observable<string> {
    return this.http.put(this.apiUrlbs+"/update/"+bookingId, bookingDetails,{responseType:"text"})
  }

  passenger_todo_booking(pEmail: any, ssId:string, bookingRef: any): Observable<string> {
    return this.http.post(this.apiUrlbs+"/store/"+pEmail+"/"+ssId, bookingRef, {responseType:'text'});
  }

  get_Date_Booking_for_Email(bookingId: string): Observable<string> {
    return this.http.get(this.apiUrlbs+"/get_Date_Booking_for_Email/"+bookingId, {responseType:"text"});
  }
 
}
