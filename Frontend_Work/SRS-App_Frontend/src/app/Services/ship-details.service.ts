import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ShipDetails } from '../Interfaces/ship-details';

@Injectable({
  providedIn: 'root'
})
export class ShipDetailsService {

  constructor(public http: HttpClient) { }  // DI for HttpClient

  private apiUrlsds = "http://localhost:8585/ship_details";

// =================================================================================================================  

  getAll_ShipDetails() : Observable<ShipDetails[]> {
    return this.http.get<ShipDetails[]>(this.apiUrlsds+"/getAll");
  }


  // http://localhost:8585/ship_details/store

  //  By default all HttpClient method return type is json then we have to write responseType as Text.
  store_ShipDetails(shipDetails:ShipDetails) :Observable<string> {
    return this.http.post(this.apiUrlsds+"/store",shipDetails,{responseType:'text'});
  }

  // http://localhost:8585/ship_details/delete/{shipId}

  delete_ShipDetails(shipId: string):Observable<string> {
    return this.http.delete(this.apiUrlsds+"/delete/"+shipId,{responseType:"text"});
  }

  // http://localhost:8585/ship_details/update/{shipId}

  update_shipDetails(shipId: any, shipDetails:any):Observable<string> {
    return this.http.put(this.apiUrlsds+"/update/"+shipId, shipDetails,{responseType:"text"})
  }


}
