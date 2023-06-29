import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Passenger } from '../Interfaces/passenger';

@Injectable({
  providedIn: 'root'
})
export class PassengerService {

  constructor(public http: HttpClient) { }  // DI for HttpClient

  private apiUrlps = "http://localhost:8585/passenger";

// =================================================================================================================  

  getAll_Passenger() : Observable<Passenger[]> {
    return this.http.get<Passenger[]>(this.apiUrlps+"/getAll");
  }

  // http://localhost:8585/passenger/store

  //  By default all HttpClient method return type is json then we have to write responseType as Text.
  storePassenger(passenger: any) :Observable<string> {
    return this.http.post(this.apiUrlps+"/store",passenger,{responseType:'text'});
  }

  // http://localhost:8585/passenger/delete/{pEmail}

  delete_Passenger(pEmail: string):Observable<string> {
    return this.http.delete(this.apiUrlps+"/delete/"+pEmail,{responseType:"text"});
  }

  // http://localhost:8585/passenger/update/{uEmail}

  update_Passenger(uEmail: any, passenger:any):Observable<string> {
    return this.http.put(this.apiUrlps+"/update/"+uEmail, passenger,{responseType:"text"})
  }

  // http://localhost:8585/passenger/findPassengerByEmail_Pass/{pEmail}/{pPassword}

  login(passenger: Passenger) :Observable<string> {
    return this.http.post(this.apiUrlps+"/findPassengerByEmail_Password",passenger,{responseType:'text'});
    // return this.http.get("http://localhost:8585/passenger/findPassengerByEmail_Pass/"+uEmail+"/"+uPassword,{responseType:"text"})
  } 

}
